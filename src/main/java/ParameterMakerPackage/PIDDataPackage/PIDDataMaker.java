package ParameterMakerPackage.PIDDataPackage;

import AzureIotPackage.IOTHubClientPackage.DataSenderForIoTHub;
import ParameterMakerPackage.OperationPackage.DateTimeOperater;
import ParameterMakerPackage.ParameterMakerFromLoginPackage;
import ParameterMakerPackage.VehicleDetailsStoreMan;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 * Created by Deshan on 11/2/16.
 */
public class PIDDataMaker {
    //set logger
    static Logger log = Logger.getLogger(ParameterMakerFromLoginPackage.class.getName());

    private String pidDataPackageStr;
    private VehicleDetailsStoreMan vehicleDetailsStoreMan;
    private int noOfPidCodes;
    private int pidCodesStartPointId;
    private int pidDataStartPointId;

    private PIDInformationMaker pidInformationMaker;

    private String pidStr;
    private String pidDataStr;
    private int noOfBytesOfPID;

    private DataSenderForIoTHub dataSenderForIoTHub;

    public PIDDataMaker(String pidDataPackageStr, int noOfPidCodes, int pidCodesStartPointId, int pidDataStartPointId, VehicleDetailsStoreMan vehicleDetailsStoreMan, DataSenderForIoTHub dataSenderForIoTHub){
        this.pidDataPackageStr=pidDataPackageStr;
        this.noOfPidCodes=noOfPidCodes;
        this.pidCodesStartPointId=pidCodesStartPointId;
        this.pidDataStartPointId=pidDataStartPointId;
        this.vehicleDetailsStoreMan=vehicleDetailsStoreMan;
        this.dataSenderForIoTHub=dataSenderForIoTHub;
    }

    public void setPidData(){
        pidInformationMaker = new PIDInformationMaker(vehicleDetailsStoreMan);

        for(int i=pidCodesStartPointId;i<pidCodesStartPointId+(noOfPidCodes*2)*2;i+=4){
            pidStr = pidDataPackageStr.substring(i,i+2);
            noOfBytesOfPID = NoOfBytesInPIDFinder.getNoOfBytes(pidStr);
            pidDataStr = pidDataPackageStr.substring(pidDataStartPointId,pidDataStartPointId+noOfBytesOfPID*2);

            log.info(pidStr+":"+noOfBytesOfPID+":"+pidDataStr);
            pidInformationMaker.setPidInformation(pidStr,pidDataStr);

            pidDataStartPointId +=noOfBytesOfPID*2;

        }

        //set current time
        vehicleDetailsStoreMan.setId(DateTimeOperater.GetUTCdatetimeAsString());

        //send data to iot hub
        dataSenderForIoTHub.setMsgString(vehicleDetailsStoreMan.serialize().toString());
        Thread dataSenderForIoTHubThread = new Thread(dataSenderForIoTHub);
        dataSenderForIoTHubThread.start();

        Gson gson = new Gson();
        String vehicleDetailsStoreManString = gson.toJson(vehicleDetailsStoreMan).toString();
        log.info("Data from PID Data packet :"+ vehicleDetailsStoreManString);
    }

}
