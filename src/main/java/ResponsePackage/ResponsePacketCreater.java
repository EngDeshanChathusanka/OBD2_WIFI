package ResponsePackage;

import java.util.ArrayList;

/**
 * Created by iot on 10/10/16.
 */
public class ResponsePacketCreater {
    private String head;
    private String length;
    private String version;
    private String deviceId;
    private String commandType;
    private String[] parameters = new String[6];
    private String crcCheckSum;
    private String tail;

    public String getHead() {
        return head;
    }

    public String getLength() {
        return length;
    }

    public String getVersion() {
        return version;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getCommandType() {
        return commandType;
    }

    public String getCrcCheckSum() {
        return crcCheckSum;
    }

    public String getTail() {
        return tail;
    }

    public String getParameter(int id){
        return parameters[id];
    }

    public void setParameter(String parameter, int id){
        parameters[id] = parameter;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public void setCrcCheckSum(String crcCheckSum) {
        this.crcCheckSum = crcCheckSum;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }




}
