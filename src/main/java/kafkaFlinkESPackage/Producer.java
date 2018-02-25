package kafkaFlinkESPackage;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Created by Deshan on 11/4/16.
 */
public class Producer {
    static String topic_name,key,value;
    public static void  dataSender(String topic, String keyStr, String valueStr) throws ExecutionException, InterruptedException {
        topic_name=topic;
        key=keyStr;
        value=valueStr;
        boolean sync = false;

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> producer = new KafkaProducer<String,String>(props);



        ProducerRecord<String,String> producerRecord = new ProducerRecord<String,String>(topic, key, value);
        if (sync) {
            producer.send(producerRecord).get();
        } else {
            producer.send(producerRecord);
        }
        producer.close();
    }
}
