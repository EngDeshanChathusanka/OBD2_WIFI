package ParameterMakerPackage.OperationPackage;

/**
 * Created by Deshan on 11/2/16.
 */
public class HexUtils {
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String getBinaryString(String hexString){
        String binaryString=Integer.toBinaryString(Integer.parseInt(hexString,16));
        binaryString=getZeroAddedString(binaryString,32);
        return binaryString;
    }

    public static byte[] getByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static byte[] reverse(byte[] array) {
        if (array == null) {
            return null;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
        return array;
    }

    public static String getReverseOrderedHexString(String string){
        byte[] bytes = getByteArray(string);
        bytes = reverse(bytes);
        String preparedString = bytesToHex(bytes);
        return preparedString;

    }

    public static String getZeroAddedString(String s,int length){
        String newZeroAddedString = s;
        for(int j=0;j<length-s.length();j++){
            newZeroAddedString = "0"+newZeroAddedString;
        }
        return newZeroAddedString;
    }
}
