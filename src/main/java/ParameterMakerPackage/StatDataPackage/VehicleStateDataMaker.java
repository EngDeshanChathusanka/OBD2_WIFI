package ParameterMakerPackage.StatDataPackage;

import ParameterMakerPackage.OperationPackage.HexUtils;

/**
 * Created by Deshan on 10/19/16.
 */
public class VehicleStateDataMaker {
    private static final String[] vehicleStateData={
            "Exhaust Emission",
            "Idle engine",
            "Hard deceleration",
            "Hard acceleration",
            "High engine coolant temperature",
            "Speeding",
            "Towing",
            "Low voltage",
            "",
            "Crash",
            "Emergency",
            "Fatigue driving",
            "Sharp turn",
            "Quick lane change",
            "Power on",
            "High RPM",
            "MIL",
            "OBD communication error",
            "Power-off",
            "No GPS device",
            "Privacy status",
            "Ignition on",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "No card presented",
            "￼Unlock"
    };


    public static String getVehicleState(String rawData){
        String vehicleStateBinaryStr= HexUtils.getBinaryString(rawData);
        String vehicleState="";
        for(int i=0;i<vehicleStateBinaryStr.length();++i){
            if(Character.toString(vehicleStateBinaryStr.charAt(i)).equals("1")){
                vehicleState +=":"+vehicleStateData[i];
            }
        }
        return vehicleState;
    }

}
