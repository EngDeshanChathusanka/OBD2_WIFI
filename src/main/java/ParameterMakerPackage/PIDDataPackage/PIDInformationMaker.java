package ParameterMakerPackage.PIDDataPackage;

import ParameterMakerPackage.OperationPackage.HexUtils;
import ParameterMakerPackage.VehicleDetailsStoreMan;
import org.apache.commons.codec.binary.Hex;

/**
 * Created by Deshan on 10/13/16.
 */
public class PIDInformationMaker {

    String[] obdRequirmentArray={
            "",
            "OBD II (California ARB)",
            "OBD (Federal EPA)",
            "OBD and OBD II",
            "OBD I",
            "Not OBD compliant",
            "EOBD",
            "EOBD and OBD II",
            "EOBD and OBD",
            "EOBD, OBD and OBD II",
            "JOBD",
            "JOBD and OBD II",
            "JOBD and EOBD",
            "JOBD, EOBD, and OBD II",
            "Heavy Duty Vehicles (EURO IV) B1",
            "Heavy Duty Vehicles (EURO V) B2",
            "Heavy Duty Vehicles (EURO EEC)",
            "Engine Manufacturer Diagnostics (EMD)"
    };

    private VehicleDetailsStoreMan vehicleDetailsStoreMan;
    public PIDInformationMaker(VehicleDetailsStoreMan vehicleDetailsStoreMan){
        this.vehicleDetailsStoreMan = vehicleDetailsStoreMan;
    }

    public void setPidInformation(String pidStr, String pidDataStr){
        char[] pidStrChar = pidStr.toCharArray();
        pidStrChar[1] = Character.toLowerCase(pidStrChar[1]);
        pidStr = String.valueOf(pidStrChar);
        switch (pidStr) {
            case "04" :
                vehicleDetailsStoreMan.setCalculatedLoadValue_percentage(getCalculatedLoadValue(pidDataStr));
                break;
            case "05" :
                vehicleDetailsStoreMan.setEngineCoolentTemperature(getEngineCoolentTemperature(pidDataStr));
                break;
            case "0a" :
                vehicleDetailsStoreMan.setFuelRailPressure(getFuelRailPressure(pidDataStr));
                break;
            case "0b" :
                vehicleDetailsStoreMan.setIntakeManifoldAbsoluteTemperature(getIntakeManifoldAbsoluteTemperature(pidDataStr));
                break;
            case "0c" :
                vehicleDetailsStoreMan.setEngineRPM(getEngineRPM(pidDataStr));
                break;
            case "0d" :
                vehicleDetailsStoreMan.setSpeed(getVehicleSpeed(pidDataStr));
                break;
            case "0f" :
                vehicleDetailsStoreMan.setIntakeAirTemparature(getIntakeAirTemparature(pidDataStr));
                break;
            case "10" :
                vehicleDetailsStoreMan.setMafAirFlowRat(getMAFAirFlowRate(pidDataStr));
                break;
            case "11" :
                vehicleDetailsStoreMan.setAbsoluteThrottlePosition(getAbsoluteThrottlePosition(pidDataStr));
                break;
            case "1c" :
                vehicleDetailsStoreMan.setObdRequirement(getOBDRequirment(pidDataStr));
                break;
            case "1f" :
                vehicleDetailsStoreMan.setTimeSinceEngineStart(getTimeSinceEngineStart(pidDataStr));
                break;
            case "21" :
                vehicleDetailsStoreMan.setDistanceTraveledWhileMILisActivated(getDistanceTraveledWhileMILisActivated(pidDataStr));
                break;
            case "2f" :
                vehicleDetailsStoreMan.setFuelLevel(getFuelLevelInput(pidDataStr));
                break;
            case "30" :
                vehicleDetailsStoreMan.setNoOfWarmUpsSinceDTCsCleared(getNoOfWarmUpsSinceDTCsCleared(pidDataStr));
                break;
            case "31" :
                vehicleDetailsStoreMan.setDistanceTraveledSinceDTCsCleared(getDistanceTraveledSinceDTCsCleared(pidDataStr));
                break;
            case "33" :
                vehicleDetailsStoreMan.setBarometricPressure(getBarometricPressure(pidDataStr));
                break;
            case "42" :
                vehicleDetailsStoreMan.setControlModuleVoltage(getControlModuleVoltage(pidDataStr));
                break;
            case "43" :
                vehicleDetailsStoreMan.setAbsoluteLoadValue(getAbsoluteLoadValue(pidDataStr));
                break;
            case "46" :
                vehicleDetailsStoreMan.setAmbientAirTemperature(getAmbientAirTemperature(pidDataStr));
                break;
            case "4c" :
                vehicleDetailsStoreMan.setCommandedThrottleActuatorControl(getCommandedThrottleActuatorControl(pidDataStr));
                break;
            case "4d" :
                vehicleDetailsStoreMan.setEngineRunTimeWhileMILON(getEngineRunTimeWhileMILON(pidDataStr));
                break;
            case "4e" :
                vehicleDetailsStoreMan.setEngineRunTimeSinceDTCsCleared(getEngineRunTimeSinceDTCsCleared(pidDataStr));
                break;
            case "52" :
                vehicleDetailsStoreMan.setAlcoholFuelPercentage(getAlcoholFuelPercentage(pidDataStr));
                break;
            default:break;

        }
    }

    private String getEngineCoolentTemperature(String pidDataStr) {
        String engineCoolentTemperature = Integer.toString(Integer.parseInt(pidDataStr,16));
        return engineCoolentTemperature;

    }

    private String getIntakeManifoldAbsoluteTemperature(String pidDataStr){
        String intakeManifoldAbsoluteTemperature = Integer.toString(Integer.parseInt(pidDataStr,16));
        return  intakeManifoldAbsoluteTemperature;

    }

    private String getEngineRPM(String pidDataStr){
        String engineRPM = Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16));
        return engineRPM;
    }

    private String getVehicleSpeed(String pidDataStr){
        String speed = Integer.toString(Integer.parseInt(pidDataStr,16));
        return speed;
    }

    private String getIntakeAirTemparature(String pidDataStr){
        String intakeAirTemparature = Integer.toString(Integer.parseInt(pidDataStr,16));
        return intakeAirTemparature;
    }

    private String getMAFAirFlowRate(String pidDataStr){
        String mafAirFlowRate = Double.toString(Integer.parseInt(pidDataStr,16)*0.01);
        return mafAirFlowRate;
    }

    private String getCalculatedLoadValue(String pidDataStr){
        String calculatedLoadValue=Integer.toString(Integer.parseInt(pidDataStr,16));
        return calculatedLoadValue;
    }

    private String getFuelRailPressure(String pidDataStr){
        String fuelRailPressure=Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16));
        return fuelRailPressure;
    }

    private String getAbsoluteThrottlePosition(String pidDataStr){
        String absoluteThrottlePosition=Integer.toString(Integer.parseInt(pidDataStr,16));
        return absoluteThrottlePosition;
    }

    private String getOBDRequirment(String pidDataStr){
        String obdRequirment=obdRequirmentArray[Integer.parseInt(pidDataStr,16)];
        return obdRequirment;
    }

    private String getTimeSinceEngineStart(String pidDataStr){
        String timeSinceEngineStart=Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16));
        return timeSinceEngineStart;
    }

    private String getDistanceTraveledWhileMILisActivated(String pidDataStr){
        String distanceTraveledWhileMILisActivated=Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16));
        return distanceTraveledWhileMILisActivated;
    }

    private String getFuelLevelInput(String pidDataStr){
        String fuelLevelInput=Integer.toString(Integer.parseInt(pidDataStr,16));
        return fuelLevelInput;
    }

    private String getNoOfWarmUpsSinceDTCsCleared(String pidDataStr){
        String noOfWarmUpsSinceDTCsCleared=Integer.toString(Integer.parseInt(pidDataStr,16));
        return noOfWarmUpsSinceDTCsCleared;
    }

    private String getDistanceTraveledSinceDTCsCleared(String pidDataStr){
        String distanceTraveledSinceDTCsCleared=Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16));
        return distanceTraveledSinceDTCsCleared;
    }

    private String getBarometricPressure(String pidDataStr){
        String barometricPressure=Integer.toString(Integer.parseInt(pidDataStr,16));
        return barometricPressure;
    }

    private String getControlModuleVoltage(String pidDataStr){
        String controlModuleVoltage=Double.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16)/1000.0);
        return  controlModuleVoltage;
    }

    private String getAbsoluteLoadValue(String pidDataStr){
        String absoluteLoadValue=Integer.toString(Integer.parseInt(pidDataStr,16));
        return absoluteLoadValue;
    }

    private String getAmbientAirTemperature(String pidDataStr){
        String ambientAirTemperature=Integer.toString(Integer.parseInt(pidDataStr,16));
        return ambientAirTemperature;
    }

    private String getCommandedThrottleActuatorControl(String pidDataStr){
        String commandedThrottleActuatorControl=Integer.toString(Integer.parseInt(pidDataStr,16));
        return commandedThrottleActuatorControl;
    }

    private String getEngineRunTimeWhileMILON(String pidDataStr){
        String engineRunTimeWhileMILOn=Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16));
        return engineRunTimeWhileMILOn;
    }

    private String getEngineRunTimeSinceDTCsCleared(String pidDataStr){
        String engineRunTimeSinceDTCsCleared=Integer.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(pidDataStr),16));
        return engineRunTimeSinceDTCsCleared;
    }

    private String getAlcoholFuelPercentage(String pidDataStr){
        String alcoholFuelPercentage=Integer.toString(Integer.parseInt(pidDataStr,16));
        return alcoholFuelPercentage;
    }


}
