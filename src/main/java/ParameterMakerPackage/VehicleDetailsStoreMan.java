package ParameterMakerPackage;

import com.google.gson.Gson;

public class VehicleDetailsStoreMan {

    //trip id String
    private String tripId="";



    //stat data
    private String totalMileage_m = "0";
    private String currentTripMileage_m = "0";
    private String totalFuelConsumption_L = null;
    private String currentTripFuelConsumption_L = null;
    private String vehicleState=null;
    private String vehicleReservedData=null;
    private String engineDiagnoseProtocol=null;


    //gps data
    private String latitude = null;
    private String longitude = null;
    private String speed_kmh_1 = null;
    private String direction = null;
    private String LatitudeDirection=null;
    private String LongitudeDirection=null;
    private String noOfSatellites=null;


    //pid parameters
    private String engineCoolentTemperature_c = null;
    private String intakeManifoldAbsoluteTemperature = null;
    private String engineRPM = null;
    private String intakeAirTemparature_kPa = null;
    private String mafAirFlowRat_gs_1 = null;
    private String calculatedLoadValue_percentage=null;
    private String fuelRailPressure_kPa=null;
    private String absoluteThrottlePosition_percentage=null;
    private String obdRequirement=null;
    private String timeSinceEngineStart_s=null;
    private String distanceTraveledWhileMILisActivated_km=null;
    private String fuelLevel_percentage=null;
    private String noOfWarmUpsSinceDTCsCleared_number=null;
    private String distanceTraveledSinceDTCsCleared_km=null;
    private String barometricPressure_kPa=null;
    private String controlModuleVoltage_V=null;
    private String absoluteLoadValue_percentage=null;
    private String ambientAirTemperature_C=null;
    private String commandedThrottleActuatorControl_percentage=null;
    private String engineRunTimeWhileMILOn_min=null;
    private String engineRunTimeSinceDTCsCleared_min=null;
    private String alcoholFuelPercentage_percentage=null;



    //date time
    private String id = null;

    //device id
    private String deviceId=null;



    //getters and setters

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getCalculatedLoadValue_percentage() {
        return calculatedLoadValue_percentage;
    }

    public void setCalculatedLoadValue_percentage(String calculatedLoadValue_percentage) {
        this.calculatedLoadValue_percentage = calculatedLoadValue_percentage;
    }

    public String getFuelLevel() {
        return fuelLevel_percentage;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel_percentage = fuelLevel_percentage;
    }

    public String getFuelRailPressure() {
        return fuelRailPressure_kPa;
    }

    public void setFuelRailPressure(String fuelRailPressure) {
        this.fuelRailPressure_kPa = fuelRailPressure;
    }

    public String getAbsoluteThrottlePosition() {
        return absoluteThrottlePosition_percentage;
    }

    public void setAbsoluteThrottlePosition(String absoluteThrottlePosition) {
        this.absoluteThrottlePosition_percentage = absoluteThrottlePosition;
    }

    public String getObdRequirement() {
        return obdRequirement;
    }

    public void setObdRequirement(String obdRequirement) {
        this.obdRequirement = obdRequirement;
    }

    public String getTimeSinceEngineStart() {
        return timeSinceEngineStart_s;
    }

    public void setTimeSinceEngineStart(String timeSinceEngineStart) {
        this.timeSinceEngineStart_s = timeSinceEngineStart;
    }

    public String getDistanceTraveledWhileMILisActivated() {
        return distanceTraveledWhileMILisActivated_km;
    }

    public void setDistanceTraveledWhileMILisActivated(String distanceTraveledWhileMILisActivated) {
        this.distanceTraveledWhileMILisActivated_km = distanceTraveledWhileMILisActivated;
    }

    public String getNoOfWarmUpsSinceDTCsCleared() {
        return noOfWarmUpsSinceDTCsCleared_number;
    }

    public void setNoOfWarmUpsSinceDTCsCleared(String noOfWarmUpsSinceDTCsCleared) {
        this.noOfWarmUpsSinceDTCsCleared_number = noOfWarmUpsSinceDTCsCleared;
    }

    public String getDistanceTraveledSinceDTCsCleared() {
        return distanceTraveledSinceDTCsCleared_km;
    }

    public void setDistanceTraveledSinceDTCsCleared(String distanceTraveledSinceDTCsCleared) {
        this.distanceTraveledSinceDTCsCleared_km = distanceTraveledSinceDTCsCleared;
    }

    public String getBarometricPressure() {
        return barometricPressure_kPa;
    }

    public void setBarometricPressure(String barometricPressure) {
        this.barometricPressure_kPa = barometricPressure;
    }

    public String getControlModuleVoltage() {
        return controlModuleVoltage_V;
    }

    public void setControlModuleVoltage(String controlModuleVoltage) {
        this.controlModuleVoltage_V = controlModuleVoltage;
    }

    public String getAbsoluteLoadValue() {
        return absoluteLoadValue_percentage;
    }

    public void setAbsoluteLoadValue(String absoluteLoadValue) {
        this.absoluteLoadValue_percentage = absoluteLoadValue;
    }

    public String getAmbientAirTemperature() {
        return ambientAirTemperature_C;
    }

    public void setAmbientAirTemperature(String ambientAirTemperature) {
        this.ambientAirTemperature_C = ambientAirTemperature;
    }

    public String getCommandedThrottleActuatorControl() {
        return commandedThrottleActuatorControl_percentage;
    }

    public void setCommandedThrottleActuatorControl(String commandedThrottleActuatorControl) {
        this.commandedThrottleActuatorControl_percentage = commandedThrottleActuatorControl;
    }

    public String getEngineRunTimeWhileMILON() {
        return engineRunTimeWhileMILOn_min;
    }

    public void setEngineRunTimeWhileMILON(String engineRunTimeWhileMILON) {
        this.engineRunTimeWhileMILOn_min = engineRunTimeWhileMILON;
    }

    public String getEngineRunTimeSinceDTCsCleared() {
        return engineRunTimeSinceDTCsCleared_min;
    }

    public void setEngineRunTimeSinceDTCsCleared(String engineRunTimeSinceDTCsCleared) {
        this.engineRunTimeSinceDTCsCleared_min = engineRunTimeSinceDTCsCleared;
    }

    public String getAlcoholFuelPercentage() {
        return alcoholFuelPercentage_percentage;
    }

    public void setAlcoholFuelPercentage(String alcoholFuelPercentage) {
        this.alcoholFuelPercentage_percentage = alcoholFuelPercentage;
    }


    public String getEngineDiagnoseProtocol() {
        return engineDiagnoseProtocol;
    }

    public void setEngineDiagnoseProtocol(String engineDiagnoseProtocol) {
        this.engineDiagnoseProtocol = engineDiagnoseProtocol;
    }

    public String getLatitudeDirection() {
        return LatitudeDirection;
    }

    public void setLatitudeDirection(String latitudeDirection) {
        LatitudeDirection = latitudeDirection;
    }

    public String getLongitudeDirection() {
        return LongitudeDirection;
    }

    public void setLongitudeDirection(String longitudeDirection) {
        LongitudeDirection = longitudeDirection;
    }

    public String getNoOfSatellites() {
        return noOfSatellites;
    }

    public void setNoOfSatellites(String noOfSatellites) {
        this.noOfSatellites = noOfSatellites;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setVehicleReservedData(String vehicleReservedData) {
        this.vehicleReservedData = vehicleReservedData;
    }

    public String getVehicleReservedData() {
        return vehicleReservedData;
    }



    public String getVehicleState() {
        return vehicleState;
    }



    public void setVehicleState(String vehicleState) {
        this.vehicleState = vehicleState;
    }


    public void setId(String id) {
        this.id = id;
    }



    public String getId() {
        return id;
    }

    public String getEngineCoolentTemperature() {
        return engineCoolentTemperature_c;
    }

    public void setEngineCoolentTemperature(String engineCoolentTemperature) {
        this.engineCoolentTemperature_c = engineCoolentTemperature;
    }

    public void setIntakeManifoldAbsoluteTemperature(String intakeManifoldAbsoluteTemperature) {
        this.intakeManifoldAbsoluteTemperature = intakeManifoldAbsoluteTemperature;
    }

    public void setEngineRPM(String engineRPM) {
        this.engineRPM = engineRPM;
    }

    public void setIntakeAirTemparature(String intakeAirTemparature) {
        this.intakeAirTemparature_kPa = intakeAirTemparature;
    }

    public void setMafAirFlowRat(String mafAirFlowRat) {
        this.mafAirFlowRat_gs_1 = mafAirFlowRat;
    }

    public String getIntakeManifoldAbsoluteTemperature() {
        return intakeManifoldAbsoluteTemperature;
    }

    public String getEngineRPM() {
        return engineRPM;
    }

    public String getIntakeAirTemparature() {
        return intakeAirTemparature_kPa;
    }

    public String getMafAirFlowRat() {
        return mafAirFlowRat_gs_1;
    }



    public void setTotalMileage(String totalMileage) {
        this.totalMileage_m = totalMileage;
    }

    public void setCurrentTripMileage(String currentTripMileage) {
        this.currentTripMileage_m = currentTripMileage;
    }

    public void setTotalFuelConsumption(String totalFuelConsumption) {
        this.totalFuelConsumption_L = totalFuelConsumption;
    }

    public void setCurrentTripFuelConsumption(String currentTripFuelConsumption) {
        this.currentTripFuelConsumption_L = currentTripFuelConsumption;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setSpeed(String speed) {
        this.speed_kmh_1 = speed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTotalMileage() {
        return totalMileage_m;
    }

    public String getCurrentTripMileage() {
        return currentTripMileage_m;
    }

    public String getTotalFuelConsumption() {
        return totalFuelConsumption_L;
    }

    public String getCurrentTripFuelConsumption() {
        return currentTripFuelConsumption_L;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getSpeed() {
        return speed_kmh_1;
    }

    public String getDirection() {
        return direction;
    }

    //convert data into String
    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
