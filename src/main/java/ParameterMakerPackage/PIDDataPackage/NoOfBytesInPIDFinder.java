package ParameterMakerPackage.PIDDataPackage;

/**
 * Created by Deshan on 10/13/16.
 */
public class NoOfBytesInPIDFinder {
    private static int noOfBytes;

    public static int getNoOfBytes(String pidStr){
        char[] pidStrChar = pidStr.toCharArray();
        pidStrChar[1] = Character.toLowerCase(pidStrChar[1]);
        pidStr = String.valueOf(pidStrChar);
        switch (pidStr) {
            case "00" :
                noOfBytes = 4;
                break;
            case "01" :
                noOfBytes = 4;
                break;
            case "02" :
                noOfBytes = 2;
                break;
            case "03" :
                noOfBytes = 2;
                break;
            case "04" :
                noOfBytes = 1;
                break;
            case "05" :
                noOfBytes = 1;
                break;
            case "06" :
                noOfBytes = 1;
                break;
            case "07" :
                noOfBytes = 1;
                break;
            case "08" :
                noOfBytes = 1;
                break;
            case "09" :
                noOfBytes = 1;
                break;
            case "0a" :
                noOfBytes = 2;
                break;
            case "0b" :
                noOfBytes = 1;
                break;
            case "0c" :
                noOfBytes = 2;
                break;
            case "0d" :
                noOfBytes = 1;
                break;
            case "0e" :
                noOfBytes = 1;
                break;
            case "0f" :
                noOfBytes = 1;
                break;
            case "10" :
                noOfBytes = 2;
                break;
            case "11" :
                noOfBytes = 1;
                break;
            case "12" :
                noOfBytes = 1;
                break;
            case "13" :
                noOfBytes = 1;
                break;
            case "14" :
                noOfBytes = 2;
                break;
            case "15" :
                noOfBytes = 2;
                break;
            case "16" :
                noOfBytes = 2;
                break;
            case "17" :
                noOfBytes = 2;
                break;
            case "18" :
                noOfBytes = 2;
                break;
            case "19" :
                noOfBytes = 2;
                break;
            case "1a" :
                noOfBytes = 2;
                break;
            case "1b" :
                noOfBytes = 2;
                break;
            case "1c" :
                noOfBytes = 1;
                break;
            case "1d" :
                noOfBytes = 1;
                break;
            case "1e" :
                noOfBytes = 1;
                break;
            case "1f" :
                noOfBytes = 2;
                break;
            case "20" :
                noOfBytes = 4;
                break;
            case "21" :
                noOfBytes = 2;
                break;
            case "22" :
                noOfBytes = 2;
                break;
            case "23" :
                noOfBytes = 2;
                break;
            case "24" :
                noOfBytes = 4;
                break;
            case "25" :
                noOfBytes = 4;
                break;
            case "26" :
                noOfBytes = 4;
                break;
            case "27" :
                noOfBytes = 4;
                break;
            case "28" :
                noOfBytes = 4;
                break;
            case "29" :
                noOfBytes = 4;
                break;
            case "2a" :
                noOfBytes = 4;
                break;
            case "2b" :
                noOfBytes = 4;
                break;
            case "2c" :
                noOfBytes = 1;
                break;
            case "2d" :
                noOfBytes = 1;
                break;
            case "2e" :
                noOfBytes = 1;
                break;
            case "2f" :
                noOfBytes = 1;
                break;
            case "30" :
                noOfBytes = 1;
                break;
            case "31" :
                noOfBytes = 2;
                break;
            case "32" :
                noOfBytes = 2;
                break;
            case "33" :
                noOfBytes = 1;
                break;
            case "34" :
                noOfBytes = 4;
                break;
            case "35" :
                noOfBytes = 4;
                break;
            case "36" :
                noOfBytes = 4;
                break;
            case "37" :
                noOfBytes = 4;
                break;
            case "38" :
                noOfBytes = 4;
                break;
            case "39" :
                noOfBytes = 4;
                break;
            case "3a" :
                noOfBytes = 4;
                break;
            case "3b" :
                noOfBytes = 4;
                break;
            case "3c" :
                noOfBytes = 2;
                break;
            case "3d" :
                noOfBytes = 2;
                break;
            case "3e" :
                noOfBytes = 2;
                break;
            case "3f" :
                noOfBytes = 2;
                break;
            case "40" :
                noOfBytes = 4;
                break;
            case "41" :
                noOfBytes = 4;
                break;
            case "42" :
                noOfBytes = 2;
                break;
            case "43" :
                noOfBytes = 1;
                break;
            case "44" :
                noOfBytes = 2;
                break;
            case "45" :
                noOfBytes = 1;
                break;
            case "46" :
                noOfBytes = 1;
                break;
            case "47" :
                noOfBytes = 1;
                break;
            case "48" :
                noOfBytes = 1;
                break;
            case "49" :
                noOfBytes = 1;
                break;
            case "4a" :
                noOfBytes = 1;
                break;
            case "4b" :
                noOfBytes = 1;
                break;
            case "4c" :
                noOfBytes = 1;
                break;
            case "4d" :
                noOfBytes = 2;
                break;
            case "4e" :
                noOfBytes = 2;
                break;
            case "4f" :
                noOfBytes = 4;
                break;
            case "50" :
                noOfBytes = 2;
                break;
            case "51" :
                noOfBytes = 1;
                break;
            case "52" :
                noOfBytes = 1;
                break;
            case "53" :
                noOfBytes = 2;
                break;
            case "54" :
                noOfBytes = 2;
                break;
            case "55" :
                noOfBytes = 2;
                break;
            case "56" :
                noOfBytes = 2;
                break;
            case "57" :
                noOfBytes = 2;
                break;
            case "58" :
                noOfBytes = 2;
                break;
            case "59" :
                noOfBytes = 2;
                break;
            case "5a" :
                noOfBytes = 1;
                break;
            default:
                noOfBytes = 1;
                break;

        }
        return noOfBytes;
    }
}
