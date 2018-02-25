package kafkaFlinkESPackage;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch2.ElasticsearchSink;
import org.apache.flink.streaming.connectors.elasticsearch2.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch2.RequestIndexer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.net.InetSocketAddress;
import java.util.*;

/**
 * Created by Deshan on 11/17/16.
 */
public class StreamAnalyticsClient {
    public static int currentTripMileage_temp=0;
    public static int currentTripMileage_current=0;
    public static int currentTripMileage_difference=0;
    public static boolean aBoolean=true;

    public static void streamAnalytics() throws Exception {
        // create execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "flink_consumer");

        DataStream<Tuple3<String,String,Integer>> currentTripMileageStream = env
                .addSource(new FlinkKafkaConsumer09<>("currentTripMileage_m", new SimpleStringSchema(), properties)).flatMap(new Splitter());

        DataStream<Tuple3<String,String,Integer>> engineRPMStream = env
                .addSource(new FlinkKafkaConsumer09<>("engineRPM", new SimpleStringSchema(), properties)).flatMap(new Splitter());

        DataStream<Tuple3<String,String,Integer>> latitudeStream = env
                .addSource(new FlinkKafkaConsumer09<>("latitude", new SimpleStringSchema(), properties)).flatMap(new Splitter());

        DataStream<Tuple3<String,String,Integer>> longitudeStream = env
                .addSource(new FlinkKafkaConsumer09<>("longitude", new SimpleStringSchema(), properties)).flatMap(new Splitter());

        /*
        KeyedStream<Tuple3<String, String, Integer>, Tuple> keyedStream=stream.keyBy(0);
        final DataStream<Tuple3<String, String, Integer>> speedDifferenceStream=stream;
        */

        currentTripMileageStream.filter(new FilterFunction<Tuple3<String, String, Integer>>() {
            @Override
            public boolean filter(Tuple3<String, String, Integer> stringIntegerTuple3) throws Exception {
                currentTripMileage_current = stringIntegerTuple3.f2;
                if (currentTripMileage_current == 0 | currentTripMileage_current >= currentTripMileage_temp) {
                    currentTripMileage_difference = currentTripMileage_current - currentTripMileage_temp;
                    aBoolean = true;
                    currentTripMileage_temp = currentTripMileage_current;
                } else {
                    aBoolean = false;
                }

                return aBoolean;
            }
        });
        /*
        speedDifferenceStream.map(new MapFunction<Tuple3<String, String, Integer>, Object>() {
            @Override
            public Tuple3<String, String, Integer> map(Tuple3<String, String, Integer> stringIntegerTuple3) throws Exception {
                return new Tuple3<String, String, Integer>(stringIntegerTuple3.f0,stringIntegerTuple3.f1,speedDifference);
            }
        });
        */

        writeElastic(currentTripMileageStream,"currentTripMileage_m");
        writeElastic(engineRPMStream,"engineRPM");
        writeElastic(currentTripMileageStream,"latitude");
        writeElastic(engineRPMStream,"longitude");

        env.execute();
    }

    public  static class Splitter implements FlatMapFunction<String, Tuple3<String, String, Integer>> {
        @Override
        public void flatMap(String sentence, Collector<Tuple3<String, String, Integer>> out) throws Exception {
            String[] s=sentence.split(",");
            out.collect(new Tuple3<String, String, Integer>(s[0],s[1], Integer.parseInt(s[2])));

        }
    }

    public static void writeElastic(DataStream<Tuple3<String, String, Integer>> input, final String type) {

        Map<String, String> config = new HashMap<>();

        // This instructs the sink to emit after every element, otherwise they would be buffered
        config.put("bulk.flush.max.actions", "1");
        config.put("cluster.name", "my-cluster");

        try {
            // Add elasticsearch hosts on startup
            List<InetSocketAddress> transports = new ArrayList<>();
            transports.add(new InetSocketAddress("127.0.0.1", 9300));

            ElasticsearchSinkFunction<Tuple3<String, String, Integer>> indexLog = new ElasticsearchSinkFunction<Tuple3<String, String, Integer>>() {
                @Override
                public void process(Tuple3<String, String, Integer> stringIntegerTuple3, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                    requestIndexer.add(createIndexRequest(stringIntegerTuple3));
                }

                public IndexRequest createIndexRequest(Tuple3<String, String, Integer> element) {
                    Map<String, String> esJson = new HashMap<>();
                    esJson.put("Id", element.f0);
                    esJson.put("time", element.f1);
                    esJson.put("data", element.f2.toString());

                    return Requests
                            .indexRequest()
                            .index(element.f0+element.f1.split(" ")[0])
                            .type(type)
                            .source(esJson);
                }

            };

            ElasticsearchSink esSink = new ElasticsearchSink(config, transports, indexLog);
            input.addSink(esSink);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
