package downLineDataPacketMakerPackage;

import ParameterMakerPackage.OperationPackage.HexUtils;
import ResponsePackage.CRC_Creater;
import ResponsePackage.ResponsePacketCreater;
import ResponsePackage.UTCTimeCreater;
import TcpReceiver.ByteStreamThread;
import org.apache.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Deshan on 11/2/16.
 */
public class LoginResponsePackageMaker {
    //set logger
    static Logger log = Logger.getLogger(ByteStreamThread.class.getName());

    static String loginResponseWithoutCRCStr = "";
    static String loginResponseStr;
    static String serverAddress;
    static String[] serverAddressArray;
    public static String getLoginResponseStr(String deviceId){
        ResponsePacketCreater loginResponsePacket = new ResponsePacketCreater();
        try {
            serverAddressArray = InetAddress.getByName("ncinga-iot.southeastasia.cloudapp.azure.com").getHostAddress().toString().split("\\.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        serverAddress = HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[0])), 2) +
                HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[1])), 2) +
                HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[2])), 2) +
                HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[3])), 2);

        loginResponsePacket.setHead("4040");
        loginResponsePacket.setLength("2900");
        loginResponsePacket.setVersion("04");
        loginResponsePacket.setDeviceId(deviceId);
        loginResponsePacket.setCommandType("9001");
        loginResponsePacket.setParameter(serverAddress, 1);
        loginResponsePacket.setParameter("581B", 2);
        loginResponsePacket.setParameter(HexUtils.getZeroAddedString(UTCTimeCreater.getUTCTime(), 8), 3);
        loginResponsePacket.setTail("0D0A");

        loginResponseWithoutCRCStr = loginResponsePacket.getHead() +
                loginResponsePacket.getLength() +
                loginResponsePacket.getVersion() +
                loginResponsePacket.getDeviceId() +
                loginResponsePacket.getCommandType() +
                loginResponsePacket.getParameter(1) +
                loginResponsePacket.getParameter(2) +
                loginResponsePacket.getParameter(3);

        CRC_Creater crc_creater = new CRC_Creater();
        loginResponsePacket.setCrcCheckSum(crc_creater.getCRC(loginResponseWithoutCRCStr));
        loginResponseStr = loginResponseWithoutCRCStr +
                loginResponsePacket.getCrcCheckSum() +
                loginResponsePacket.getTail();
        log.info("login response package:"+loginResponseStr);

        return loginResponseStr;
    }
}
