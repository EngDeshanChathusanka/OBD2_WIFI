package ParameterMakerPackage;

import AzureIotPackage.IOTHubClientPackage.DataSenderForIoTHub;
import ParameterMakerPackage.GPSDataPackage.GPSDataMaker;
import ParameterMakerPackage.OperationPackage.DateTimeOperater;
import ParameterMakerPackage.StatDataPackage.StatDataMaker;
import com.google.gson.Gson;
import kafkaFlinkESPackage.DataSenderToKafkaTopics;
import kafkaFlinkESPackage.Producer;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Deshan on 10/16/16.
 */
public class ParameterMakerFromAlarmsDataPackage extends Thread {
    static Logger log = Logger.getLogger(ParameterMakerFromAlarmsDataPackage.class.getName());
    private String alarmsDataPackageStr;
    private VehicleDetailsStoreMan vehicleDetailsStoreMan;
    private DataSenderForIoTHub dataSenderForIoTHub;
    private String deviceId;

    public ParameterMakerFromAlarmsDataPackage(VehicleDetailsStoreMan vehicleDetailsStoreMan, String alarmsDataPackageStr, DataSenderForIoTHub dataSenderForIoTHub, String deviceId){
        this.vehicleDetailsStoreMan = vehicleDetailsStoreMan;
        this.alarmsDataPackageStr = alarmsDataPackageStr;
        this.dataSenderForIoTHub = dataSenderForIoTHub;
        this.deviceId=deviceId;
    }

    public  void run(){
        //set stat_data
        StatDataMaker.setStatData(vehicleDetailsStoreMan, alarmsDataPackageStr.substring(62, 130),"AlarmDataPacket",deviceId);

        //set gps data
        GPSDataMaker.setGPSdata(vehicleDetailsStoreMan, alarmsDataPackageStr.substring(130, 170));

        //set current date and time
        vehicleDetailsStoreMan.setId(DateTimeOperater.GetUTCdatetimeAsString());

        //send data to iot hub
        dataSenderForIoTHub.setMsgString(vehicleDetailsStoreMan.serialize());
        Thread dataSenderForIotHubThread = new Thread(dataSenderForIoTHub);
        dataSenderForIotHubThread.start();

        //send to kafka topic
        try {
            DataSenderToKafkaTopics.sendData(vehicleDetailsStoreMan);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String vehicleDetailsStoreManString = gson.toJson(vehicleDetailsStoreMan).toString();
        log.info("Data from Alarm Data packet :"+ vehicleDetailsStoreManString);

    }


}
