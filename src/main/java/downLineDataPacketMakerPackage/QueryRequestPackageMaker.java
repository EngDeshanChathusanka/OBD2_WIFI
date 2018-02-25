package downLineDataPacketMakerPackage;

import ResponsePackage.CRC_Creater;
import ResponsePackage.ResponsePacketCreater;
import TcpReceiver.ByteStreamThread;
import org.apache.log4j.Logger;

/**
 * Created by Deshan on 11/2/16.
 */
public class QueryRequestPackageMaker {
    //set logger
    static Logger log = Logger.getLogger(ByteStreamThread.class.getName());

    static String queryRequestWithoutCRCStr = "";
    static String queryRequestStr;

    public static String getQueryRequestStr(String noOfQueriedParameters, String parameterList, String deviceId){
        ResponsePacketCreater queryRequestPackage=new ResponsePacketCreater();
        queryRequestPackage.setHead("4040");
        queryRequestPackage.setLength("2400");
        queryRequestPackage.setVersion("04");
        queryRequestPackage.setDeviceId(deviceId);
        queryRequestPackage.setCommandType("2002");
        queryRequestPackage.setParameter("0f00", 1);
        queryRequestPackage.setParameter(noOfQueriedParameters,2);
        queryRequestPackage.setParameter(parameterList,3);
        queryRequestPackage.setTail("ODOA");

        queryRequestWithoutCRCStr = queryRequestPackage.getHead() +
                queryRequestPackage.getLength() +
                queryRequestPackage.getVersion() +
                queryRequestPackage.getDeviceId() +
                queryRequestPackage.getCommandType() +
                queryRequestPackage.getParameter(1) +
                queryRequestPackage.getParameter(2) +
                queryRequestPackage.getParameter(3);

        CRC_Creater crc_creater = new CRC_Creater();
        queryRequestPackage.setCrcCheckSum(crc_creater.getCRC(queryRequestWithoutCRCStr));
        queryRequestStr = queryRequestWithoutCRCStr +
                queryRequestPackage.getCrcCheckSum() +
                queryRequestPackage.getTail();
        log.info("query Request package:"+queryRequestStr);

        return queryRequestStr;

    }

}
