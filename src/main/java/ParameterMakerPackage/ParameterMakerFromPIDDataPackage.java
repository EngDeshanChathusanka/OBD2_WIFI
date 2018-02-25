package ParameterMakerPackage;

import AzureIotPackage.IOTHubClientPackage.DataSenderForIoTHub;
import ParameterMakerPackage.OperationPackage.DateTimeOperater;
import ParameterMakerPackage.PIDDataPackage.NoOfBytesInPIDFinder;
import ParameterMakerPackage.PIDDataPackage.PIDInformationMaker;
import com.google.gson.Gson;
import kafkaFlinkESPackage.DataSenderToKafkaTopics;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Deshan on 10/13/16.
 */
public class ParameterMakerFromPIDDataPackage extends Thread {
    private String pidDataPackageStr;
    private VehicleDetailsStoreMan vehicleDetailsStoreMan;
    private int noOfPID;
    private int pidDataStartPointId;

    private PIDInformationMaker pidInformationMaker;

    private String pidStr;
    private String pidDataStr;
    private int noOfBytesOfPID;

    private DataSenderForIoTHub dataSenderForIoTHub;

    public ParameterMakerFromPIDDataPackage(String pidDataPackageStr, VehicleDetailsStoreMan vehicleDetailsStoreMan,DataSenderForIoTHub dataSenderForIoTHub){
        this.vehicleDetailsStoreMan = vehicleDetailsStoreMan;
        this.pidDataPackageStr = pidDataPackageStr;
        this.dataSenderForIoTHub = dataSenderForIoTHub;
    }

    public void run(){
        this.noOfPID = Integer.parseInt(pidDataPackageStr.substring(126,128),16);
        pidDataStartPointId = 128+(noOfPID*2)*2+4;
        pidInformationMaker = new PIDInformationMaker(vehicleDetailsStoreMan);

        for(int i=128;i<128+(noOfPID*2)*2;i+=4){
            pidStr = pidDataPackageStr.substring(i,i+2);
            noOfBytesOfPID = NoOfBytesInPIDFinder.getNoOfBytes(pidStr);
            pidDataStr = pidDataPackageStr.substring(pidDataStartPointId,pidDataStartPointId+noOfBytesOfPID*2);

            System.out.println(pidStr+":"+noOfBytesOfPID+":"+pidDataStr);
            pidInformationMaker.setPidInformation(pidStr,pidDataStr);

            pidDataStartPointId +=noOfBytesOfPID*2;

        }

        //set current time
        vehicleDetailsStoreMan.setId(DateTimeOperater.GetUTCdatetimeAsString());

        //send data to iot hub
        dataSenderForIoTHub.setMsgString(vehicleDetailsStoreMan.serialize().toString());
        Thread dataSenderForIoTHubThread = new Thread(dataSenderForIoTHub);
        dataSenderForIoTHubThread.start();

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
        System.out.println("Data from PID Data packet :"+ vehicleDetailsStoreManString);
    }

}
