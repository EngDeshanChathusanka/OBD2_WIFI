package AzureIotPackage.IOTHubClientPackage;


import com.microsoft.azure.iot.service.sdk.*;
import com.microsoft.azure.iothub.Message;
import com.microsoft.azure.iothub.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class DataSenderForIoTHub extends Thread {

    private String connString;
    private IotHubClientProtocol protocol = IotHubClientProtocol.HTTPS;
    private String deviceId;
    private String iotHubName;
    private String deviceKey;
    private DeviceClient client;
    private String msgString;

    public DataSenderForIoTHub(String iotHubName, String deviceId, String deviceKey){
        //properties of device identity connection String
        this.deviceId = deviceId;
        this.deviceKey = deviceKey;
        this.iotHubName = iotHubName;

        this.connString = "HostName="+this.iotHubName+".azure-devices.net;DeviceId="+this.deviceId+";SharedAccessKey="+this.deviceKey;
        System.out.println(connString);
        try {
            this.client = new DeviceClient(connString,protocol);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public String getIotHubName() {
        return iotHubName;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setIotHubName(String iotHubName) {

        this.iotHubName = iotHubName;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }


    public void setMsgString(String msgString) {
        this.msgString = msgString;
    }

    public String getDeviceId() {
        return deviceId;

    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    private static class EventCallback implements IotHubEventCallback
    {
        public void execute(IotHubStatusCode status, Object context) {
            System.out.println("IoT Hub responded to message with status: " + status.name());

            if (context != null) {
                synchronized (context) {
                    context.notify();
                }
            }
        }
    }

    public void run()  {
        try {
            client.open();
            Message message = new Message(msgString);

            Object lockobj = new Object();
            EventCallback callback = new EventCallback();
            client.sendEventAsync(message, callback, lockobj);

            synchronized (lockobj) {
                lockobj.wait();
            }
            client.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
