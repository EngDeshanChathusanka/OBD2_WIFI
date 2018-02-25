package ParameterMakerPackage;

import AzureIotPackage.IOTHubClientPackage.DataSenderForIoTHub;
import ParameterMakerPackage.OperationPackage.DateTimeOperater;
import ParameterMakerPackage.PIDDataPackage.PIDDataMaker;
import ParameterMakerPackage.StatDataPackage.StatDataMaker;
import com.google.gson.Gson;
import kafkaFlinkESPackage.DataSenderToKafkaTopics;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutionException;

/**
 * Created by Deshan on 11/2/16.
 */
public class ParameterMakerFromSnapshotAndFreezePackage extends Thread {
    //set logger
    static Logger log = Logger.getLogger(ParameterMakerFromLoginPackage.class.getName());
    private String snapshotAndFreezePackageStr;
    private VehicleDetailsStoreMan vehicleDetailsStoreMan;
    private DataSenderForIoTHub dataSenderForIoTHub;

    int noOfPid;
    int pidCodesStartPointId;
    int pidDataStartPointId;
    String deviceId;

    public ParameterMakerFromSnapshotAndFreezePackage(VehicleDetailsStoreMan vehicleDetailsStoreMan, String snapshotAndFreezePackageStr, DataSenderForIoTHub dataSenderForIoTHub,String deviceId){
        this.vehicleDetailsStoreMan = vehicleDetailsStoreMan;
        this.snapshotAndFreezePackageStr = snapshotAndFreezePackageStr;
        this.dataSenderForIoTHub = dataSenderForIoTHub;
        this.deviceId=deviceId;
    }

    public  void run(){
        //set stat_data
        StatDataMaker.setStatData(vehicleDetailsStoreMan, snapshotAndFreezePackageStr.substring(54, 122),"snapshotAndFreezePacket",deviceId);

        //set pid data
        noOfPid=Integer.parseInt(snapshotAndFreezePackageStr.substring(124,126),16);
        pidCodesStartPointId=126;
        pidDataStartPointId=126+(noOfPid*2)*2;
        PIDDataMaker pidDataMaker=new PIDDataMaker(snapshotAndFreezePackageStr,noOfPid,pidCodesStartPointId,pidDataStartPointId,vehicleDetailsStoreMan,dataSenderForIoTHub);
        pidDataMaker.setPidData();

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
        log.info("Data from snapshotAndFreeze packet :" + vehicleDetailsStoreManString);

    }

}
