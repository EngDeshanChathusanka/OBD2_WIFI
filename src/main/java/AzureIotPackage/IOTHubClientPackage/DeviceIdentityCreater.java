package AzureIotPackage.IOTHubClientPackage;

import com.microsoft.azure.iot.service.exceptions.IotHubException;
import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iot.service.sdk.RegistryManager;

import java.io.IOException;
import java.net.URISyntaxException;


public class DeviceIdentityCreater {
    private static final String connectionString = "HostName=smartcarIoTHub.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=JypEnE1jCli6oQ73UfEvPUTSLOtIp6dndTY7DJ6i41E=";
    private String deviceId ;
    private String deviceKey ;

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void CreateDeviceIdentity(String deviceId) throws IOException, URISyntaxException, Exception
    {
        RegistryManager registryManager =RegistryManager.createFromConnectionString(connectionString);
        Device device= Device.createFromId(deviceId, null, null);
        try {
            device = registryManager.addDevice(device);
        } catch (IotHubException iote) {
            try {
                device = registryManager.getDevice(deviceId);
            } catch (IotHubException iotf) {
                iotf.printStackTrace();
            }
        }
        this.deviceId = device.getDeviceId();
        this.deviceKey = device.getSecondaryKey();

    }
}
