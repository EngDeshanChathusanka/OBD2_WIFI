package ParameterMakerPackage.GPSDataPackage;

import ParameterMakerPackage.OperationPackage.HexUtils;
import ParameterMakerPackage.VehicleDetailsStoreMan;
import org.apache.log4j.Logger;

/**
 * Created by Deshan on 10/21/16.
 */
public class GPSDataMaker {
    static Logger log = Logger.getLogger(GPSDataMaker.class.getName());
    public static void setGPSdata(VehicleDetailsStoreMan vehicleDetailsStoreMan, String gpsDataStr){
        //set gps data
        vehicleDetailsStoreMan.setLatitude(getLatitude(gpsDataStr.substring(14,22)));
        log.info("latitude raw data:" + gpsDataStr.substring(14, 22));
        vehicleDetailsStoreMan.setLongitude(getLongitude(gpsDataStr.substring(22, 30)));
        log.info("longitude raw data:"+gpsDataStr.substring(22,30));
        vehicleDetailsStoreMan.setSpeed(getSpeed(gpsDataStr.substring(30,34)));
        log.info("speed raw data:"+gpsDataStr.substring(30,34));
        vehicleDetailsStoreMan.setDirection(getDirection(gpsDataStr.substring(34,38)));
        log.info("Direction raw data:"+gpsDataStr.substring(34,38));
        log.info("flag raw data:"+gpsDataStr.substring(38,40));
        vehicleDetailsStoreMan.setLatitudeDirection(getLatitudeDirection(gpsDataStr.substring(38,40)));
        vehicleDetailsStoreMan.setLongitudeDirection(getLongitudeDirection(gpsDataStr.substring(38,40)));
        vehicleDetailsStoreMan.setNoOfSatellites(getNoOfSatellites(gpsDataStr.substring(38, 40)));
    }

    public static String getLatitude(String rawData){
        String latitude = Double.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16)/3600000.0);
        return latitude;
    }

    public static String getLongitude(String rawData){
        String longitude = Double.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16)/3600000.0);
        return longitude;
    }

    public static String getSpeed(String rawData){
        String speed = Double.toString((Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16)*36)/1000.0);
        return speed;
    }

    public static String getDirection(String rawData){
        String direction = Double.toString(Integer.parseInt(HexUtils.getReverseOrderedHexString(rawData),16)/10.0);
        return direction;
    }

    public static String getLatitudeDirection(String rawData){
        String binaryString=Integer.toBinaryString(Integer.parseInt(rawData,16));
        binaryString= HexUtils.getZeroAddedString(binaryString, 8);
        if(Character.toString(binaryString.charAt(6)).equals("1"))
            return "North Latitude";
        else
            return "South Latitude";
    }

    public static String getLongitudeDirection(String rawData){
        String binaryString=Integer.toBinaryString(Integer.parseInt(rawData, 16));
        binaryString= HexUtils.getZeroAddedString(binaryString, 8);
        if(Character.toString(binaryString.charAt(7)).equals("1"))
            return "East Longitude";
        else
            return "West Longitude";
    }

    public static String getNoOfSatellites(String rawData){

        String binaryString=Integer.toBinaryString(Integer.parseInt(rawData,16));
        binaryString= HexUtils.getZeroAddedString(binaryString, 8);
        return Integer.toString(Integer.parseInt(binaryString.substring(0,4),2));

    }


}
