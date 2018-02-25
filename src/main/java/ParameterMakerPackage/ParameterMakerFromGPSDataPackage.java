package ParameterMakerPackage;

import AzureIotPackage.IOTHubClientPackage.DataSenderForIoTHub;
import ParameterMakerPackage.GPSDataPackage.GPSDataMaker;
import ParameterMakerPackage.OperationPackage.DateTimeOperater;
import ParameterMakerPackage.StatDataPackage.StatDataMaker;
import com.google.gson.Gson;
import kafkaFlinkESPackage.DataSenderToKafkaTopics;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Deshan on 10/16/16.
 */
public class ParameterMakerFromGPSDataPackage extends Thread{
    static Logger log = Logger.getLogger(ParameterMakerFromGPSDataPackage.class.getName());
    private String gpsDataPackageStr;
    private VehicleDetailsStoreMan vehicleDetailsStoreMan;
    private DataSenderForIoTHub dataSenderForIoTHub;
    private String deviceId;

    public ParameterMakerFromGPSDataPackage(VehicleDetailsStoreMan vehicleDetailsStoreMan, String gpsDataPackageStr, DataSenderForIoTHub dataSenderForIoTHub,String deviceId){
        this.vehicleDetailsStoreMan = vehicleDetailsStoreMan;
        this.gpsDataPackageStr = gpsDataPackageStr;
        this.dataSenderForIoTHub = dataSenderForIoTHub;
        this.deviceId=deviceId;
    }

    public  void run(){
        //set stat_data
        StatDataMaker.setStatData(vehicleDetailsStoreMan, gpsDataPackageStr.substring(56, 124),"GpsDataPacket",deviceId);

        //set gps data
        GPSDataMaker.setGPSdata(vehicleDetailsStoreMan, gpsDataPackageStr.substring(124, 164));

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
        log.info("Data from GPS Data packet :"+ vehicleDetailsStoreManString);

    }


}
