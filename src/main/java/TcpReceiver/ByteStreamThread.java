package TcpReceiver;

import AzureIotPackage.IOTHubClientPackage.DataSenderForIoTHub;
import AzureIotPackage.IOTHubClientPackage.DeviceIdentityCreater;
import ParameterMakerPackage.*;
import ParameterMakerPackage.OperationPackage.HexUtils;
import ResponsePackage.CRC_Creater;
import ResponsePackage.ResponsePacketCreater;
import ResponsePackage.UTCTimeCreater;
import com.microsoft.azure.iot.service.exceptions.IotHubTooManyDevicesException;
import downLineDataPacketMakerPackage.QueryRequestPackageMaker;
import org.apache.log4j.Logger;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Formatter;


public class ByteStreamThread extends Thread {
    static Logger log = Logger.getLogger(ByteStreamThread.class.getName());
    private Socket connectionSocket = null;
    private ServerSocketCreater serverSocketCreater;
    private int dataLength;
    private VehicleDetailsStoreMan vehicleDetailsStoreMan;
    private DeviceIdentityCreater deviceIdentityCreater;

    private InputStream inputStream = null;
    private DataInputStream dataInputStream = null;

    byte[] bytes;

    //constructor
    public ByteStreamThread(Socket connectionSocket, ServerSocketCreater serverSocketCreater, int dataLength, VehicleDetailsStoreMan vehicleDetailsStoreMan,DeviceIdentityCreater deviceIdentityCreater){
        this.connectionSocket = connectionSocket;
        this.serverSocketCreater = serverSocketCreater;
        this.dataLength = dataLength;
        this.vehicleDetailsStoreMan = vehicleDetailsStoreMan;
        this.deviceIdentityCreater = deviceIdentityCreater;
        bytes = new byte[dataLength];

        try {
            inputStream = connectionSocket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        log.info("start" + currentThread().getName());
        log.info("waiting for input");
        bytes = new byte[dataLength];
        this.readRawData(dataInputStream,bytes);

    }

    public void readRawData(DataInputStream dataInputStream,byte[] bytes){
        String loginResponseStr = "";
        String queryRequestStr = "";
        String loginResponseWithoutCRCStr = "";
        String commandType = "";
        String deviceId = "";
        String deviceKey = "";
        String serverAddress;
        String[] serverAddressArray;
        boolean isrunning = true;
        try {
            while (isrunning) {
                if (dataInputStream.read(bytes)>0){
                    Formatter formatter = new Formatter();
                    for (byte b : bytes) {
                        formatter.format("%02x", b);
                    }
                    commandType = formatter.toString().substring(50,54);
                    deviceId = formatter.toString().substring(10,50);
                    log.info("command type:" + commandType);
                    log.info("device id:" + deviceId);
                    log.info("received data package:"+formatter.toString());

                    //create device identity
                    deviceIdentityCreater.CreateDeviceIdentity(deviceId);
                    deviceKey = deviceIdentityCreater.getDeviceKey();

                    //create data sender connection
                    DataSenderForIoTHub dataSenderForIoTHub = new DataSenderForIoTHub("smartcarIoTHub",deviceId,deviceKey);

                    //set device id
                    vehicleDetailsStoreMan.setDeviceId(deviceId);



                    if (commandType.equals("1001")) {
                        serverAddressArray = InetAddress.getByName("ncinga-iot.southeastasia.cloudapp.azure.com").getHostAddress().toString().split("\\.");
                        serverAddress = HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[0])),2) +
                                HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[1])),2) +
                                HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[2])),2) +
                                HexUtils.getZeroAddedString(Integer.toHexString(Integer.parseInt(serverAddressArray[3])),2);
                        ResponsePacketCreater loginResponsePacket = new ResponsePacketCreater();
                        loginResponsePacket.setHead("4040");
                        loginResponsePacket.setLength("2900");
                        loginResponsePacket.setVersion("04");
                        loginResponsePacket.setDeviceId(deviceId);
                        loginResponsePacket.setCommandType("9001");
                        loginResponsePacket.setParameter(serverAddress, 1);
                        loginResponsePacket.setParameter("581B", 2);
                        loginResponsePacket.setParameter(HexUtils.getZeroAddedString(UTCTimeCreater.getUTCTime(),8), 3);
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

                        sendDataTodevice(connectionSocket.getOutputStream(), HexUtils.hexStringToByteArray(loginResponseStr));

                        //create query Request package
                        queryRequestStr = QueryRequestPackageMaker.getQueryRequestStr("01", "0120", deviceId);
                        System.out.println("query created");

                        //send query request package
                        sendDataTodevice(connectionSocket.getOutputStream(), HexUtils.hexStringToByteArray(queryRequestStr));
                        System.out.println("query sent");

                        ParameterMakerFromLoginPackage parameterMakerFromLoginPackage = new ParameterMakerFromLoginPackage(vehicleDetailsStoreMan,formatter.toString(),dataSenderForIoTHub,deviceId);
                        Thread loginReaderThread = new Thread(parameterMakerFromLoginPackage);
                        loginReaderThread.start();
                    }else if(commandType.equals("4001")){
                        ParameterMakerFromGPSDataPackage parameterMakerFromGPSDataPackage = new ParameterMakerFromGPSDataPackage(vehicleDetailsStoreMan,formatter.toString(),dataSenderForIoTHub,deviceId);
                        Thread pidThread = new Thread(parameterMakerFromGPSDataPackage);
                        pidThread.start();

                    }else if(commandType.equals("4002")){
                        ParameterMakerFromPIDDataPackage parameterMakerFromPIDDataPackage = new ParameterMakerFromPIDDataPackage(formatter.toString(),vehicleDetailsStoreMan,dataSenderForIoTHub);
                        Thread pidThread = new Thread(parameterMakerFromPIDDataPackage);
                        pidThread.start();

                    }else if(commandType.equals("4007")){
                        ParameterMakerFromAlarmsDataPackage parameterMakerFromAlarmsDataPackage = new ParameterMakerFromAlarmsDataPackage(vehicleDetailsStoreMan,formatter.toString(),dataSenderForIoTHub,deviceId);
                        Thread pidThread = new Thread(parameterMakerFromAlarmsDataPackage);
                        pidThread.start();

                    }else if(commandType.equals("1002")){
                        ParameterMakerFromCancellationPackage parameterMakerFromCancellationPackage = new ParameterMakerFromCancellationPackage(vehicleDetailsStoreMan,formatter.toString(),dataSenderForIoTHub,deviceId);
                        Thread pidThread = new Thread(parameterMakerFromCancellationPackage);
                        pidThread.start();

                    }else if(commandType.equals("A002")){
                        log.info("query ok");
                    }else if(commandType.equals("4005")){
                        ParameterMakerFromSnapshotAndFreezePackage parameterMakerFromSnapshotAndFreezePackage=new ParameterMakerFromSnapshotAndFreezePackage(vehicleDetailsStoreMan,formatter.toString(),dataSenderForIoTHub,deviceId);
                        Thread snapshotAndFreezeThread=new Thread(parameterMakerFromSnapshotAndFreezePackage);
                        snapshotAndFreezeThread.start();
                    }
                }

            }
        } catch (IOException e) {
            log.info(e.getMessage());
        } catch (URISyntaxException e) {
            log.info(e.getMessage());
        } catch(IotHubTooManyDevicesException e){
            log.info(e.getMessage());
        }catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void sendDataTodevice(OutputStream outputStream, byte[] bytes){
        DataOutputStream dout = new DataOutputStream(outputStream);
        try {
            dout.write(bytes);
        } catch (IOException e) {
            log.info(e.getMessage());
        }

    }

}
