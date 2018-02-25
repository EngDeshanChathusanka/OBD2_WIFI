package kafkaFlinkESPackage;

import ParameterMakerPackage.StatDataPackage.VehicleStateDataMaker;
import ParameterMakerPackage.VehicleDetailsStoreMan;

import java.util.concurrent.ExecutionException;

/**
 * Created by Deshan on 11/21/16.
 */
public class DataSenderToKafkaTopics {
    public static void sendData(VehicleDetailsStoreMan vehicleDetailsStoreMan) throws ExecutionException, InterruptedException {
        Producer.dataSender("totalMileage_m","totalMileage_m"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getTotalMileage());
        Producer.dataSender("currentTripMileage_m","currentTripMileage_m"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getCurrentTripMileage());
        Producer.dataSender("totalFuelConsumption_L","totalFuelConsumption_L"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getTotalFuelConsumption());
        Producer.dataSender("currentTripFuelConsumption_L","currentTripFuelConsumption_L"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getCurrentTripFuelConsumption());
        Producer.dataSender("vehicleState","vehicleState"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getVehicleState());
        Producer.dataSender("vehicleReservedData","vehicleReservedData"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getVehicleReservedData());
        Producer.dataSender("engineDiagnoseProtocol","engineDiagnoseProtocol"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getEngineDiagnoseProtocol());
        Producer.dataSender("latitude","latitude"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getLatitude());
        Producer.dataSender("longitude","longitude"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getLongitude());
        Producer.dataSender("speed_kmh_1","speed_kmh_1"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getSpeed());
        Producer.dataSender("direction","direction"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getDirection());
        Producer.dataSender("LatitudeDirection","LatitudeDirection"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getLatitudeDirection());
        Producer.dataSender("LongitudeDirection","LongitudeDirection"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getLongitudeDirection());
        Producer.dataSender("noOfSatellites","noOfSatellites"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getNoOfSatellites());
        Producer.dataSender("engineCoolentTemperature_c","engineCoolentTemperature_c"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getEngineCoolentTemperature());
        Producer.dataSender("intakeManifoldAbsoluteTemperature","intakeManifoldAbsoluteTemperature"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getIntakeManifoldAbsoluteTemperature());
        Producer.dataSender("engineRPM","engineRPM"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getEngineRPM());
        Producer.dataSender("intakeAirTemparature_kPa","intakeAirTemparature_kPa"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getIntakeAirTemparature());
        Producer.dataSender("calculatedLoadValue_percentage","calculatedLoadValue_percentage"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getCalculatedLoadValue_percentage());
        Producer.dataSender("fuelRailPressure_kPa","fuelRailPressure_kPa"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getFuelRailPressure());
        Producer.dataSender("absoluteThrottlePosition_percentage","absoluteThrottlePosition_percentage"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getAbsoluteThrottlePosition());
        Producer.dataSender("obdRequirement","obdRequirement"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getObdRequirement());
        Producer.dataSender("timeSinceEngineStart_s","timeSinceEngineStart_s"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getTimeSinceEngineStart());
        Producer.dataSender("distanceTraveledWhileMILisActivated_km","distanceTraveledWhileMILisActivated_km"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getDistanceTraveledWhileMILisActivated());
        Producer.dataSender("fuelLevel_percentage","fuelLevel_percentage"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getFuelLevel());
        Producer.dataSender("noOfWarmUpsSinceDTCsCleared_number","noOfWarmUpsSinceDTCsCleared_number"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getNoOfWarmUpsSinceDTCsCleared());
        Producer.dataSender("distanceTraveledSinceDTCsCleared_km","distanceTraveledSinceDTCsCleared_km"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getDistanceTraveledSinceDTCsCleared());
        Producer.dataSender("barometricPressure_kPa","barometricPressure_kPa"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getBarometricPressure());
        Producer.dataSender("controlModuleVoltage_V","controlModuleVoltage_V"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getControlModuleVoltage());
        Producer.dataSender("absoluteLoadValue_percentage","absoluteLoadValue_percentage"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getAbsoluteLoadValue());
        Producer.dataSender("ambientAirTemperature_C","ambientAirTemperature_C"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getAmbientAirTemperature());
        Producer.dataSender("commandedThrottleActuatorControl_percentage","commandedThrottleActuatorControl_percentage"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getCommandedThrottleActuatorControl());
        Producer.dataSender("engineRunTimeWhileMILOn_min","engineRunTimeWhileMILOn_min"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getEngineRunTimeWhileMILON());
        Producer.dataSender("engineRunTimeSinceDTCsCleared_min","engineRunTimeSinceDTCsCleared_min"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getEngineRunTimeSinceDTCsCleared());
        Producer.dataSender("alcoholFuelPercentage_percentage","alcoholFuelPercentage_percentage"+vehicleDetailsStoreMan.getId(),vehicleDetailsStoreMan.getDeviceId()+","+vehicleDetailsStoreMan.getId()+","+vehicleDetailsStoreMan.getAlcoholFuelPercentage());

    }
}
