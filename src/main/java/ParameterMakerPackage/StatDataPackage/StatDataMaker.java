package ParameterMakerPackage.StatDataPackage;

import ParameterMakerPackage.OperationPackage.HexUtils;
import ParameterMakerPackage.VehicleDetailsStoreMan;
import TcpReceiver.ServerSocketCreater;
import org.apache.log4j.Logger;

import java.util.UUID;

/**
 * Created by Deshan on 10/21/16.
 */
public class StatDataMaker {
    static Logger log = Logger.getLogger(StatDataMaker.class.getName());
    static String tripId;
    public static void setStatData(VehicleDetailsStoreMan vehicleDetailsStoreMan, String statDataStr,String packetType, String deviceId){
        //set stat_data
        vehicleDetailsStoreMan.setTotalMileage(getTotalMileage(statDataStr.substring(16, 24),vehicleDetailsStoreMan));
        log.info("total milage raw data:" + statDataStr.substring(16, 24));


        try {
            if (vehicleDetailsStoreMan.getVehicleState().contains("Ignition on") & vehicleDetailsStoreMan.getCurrentTripMileage().equals("0") & !getCurrentTripMileage(statDataStr.substring(24, 32)).equals("0")) {
                tripId=UUID.randomUUID().toString();
                ServerSocketCreater.vehicleTripIdRecorder.put(deviceId,tripId);
            }
        }catch (Exception e){
            log.info("uuid set up error");
        }
        vehicleDetailsStoreMan.setCurrentTripMileage(getCurrentTripMileage(statDataStr.substring(24, 32)));
        log.info("current trip milage raw data:" + statDataStr.substring(24, 32));
        if(ServerSocketCreater.vehicleTripIdRecorder.containsKey(deviceId) & !vehicleDetailsStoreMan.getCurrentTripMileage().equals("0"))
            vehicleDetailsStoreMan.setTripId(ServerSocketCreater.vehicleTripIdRecorder.get(deviceId).toString());

        vehicleDetailsStoreMan.setTotalFuelConsumption(getTotalFuelConsumption(statDataStr.substring(32, 40)));
        log.info("total fuel consumption raw data:"+statDataStr.substring(32,40));
        vehicleDetailsStoreMan.setCurrentTripFuelConsumption(getCurrentTripFuelConsumption(statDataStr.substring(40,44)));
        log.info("total current consumption raw data:"+statDataStr.substring(40,44));
        vehicleDetailsStoreMan.setVehicleState(VehicleStateDataMaker.getVehicleState(statDataStr.substring(44,52)));
        log.info("vehicle state raw data:"+statDataStr.substring(44,52));
        vehicleDetailsStoreMan.setEngineDiagnoseProtocol(ReservedDataInStatDataMaker.getReservedData(statDataStr.substring(52,68)));
        log.info("engine diagnose protocol:"+statDataStr.substring(52,68));


    }

    public static String getTotalMileage(String rawData,VehicleDetailsStoreMan vehicleDetailsStoreMan){
        String totalMileage = Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16));
        return totalMileage;
    }

    public static String getCurrentTripMileage(String rawData){
        String currentTripMileage = Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16));
        return currentTripMileage;
    }
    public static String getTotalFuelConsumption(String rawData){
        String totalFuelConsumption = Double.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16)*0.01);
        return totalFuelConsumption;
    }

    public static String getCurrentTripFuelConsumption(String rawData){
        String currentTripFuelConsumption = Double.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16)*0.01);
        return currentTripFuelConsumption;
    }

}
