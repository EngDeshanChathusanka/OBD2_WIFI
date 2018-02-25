package TcpReceiver;


import AzureIotPackage.IOTHubClientPackage.DeviceIdentityCreater;
import ParameterMakerPackage.VehicleDetailsStoreMan;
import org.apache.log4j.Logger;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class ServerSocketCreater implements Runnable{
        static Logger log = Logger.getLogger(ServerSocketCreater.class.getName());
        public static HashMap<String,String> vehicleTripIdRecorder=new HashMap<String,String>();

    //set server port
        private int serverPort;
        private int dataLength;
        private boolean isRunning = true;


        //constructors
        public ServerSocketCreater(int serverPort) {
            this.serverPort = serverPort;
        }

        public void run() {
            Thread readerThread;
            DeviceIdentityCreater deviceIdentityCreater = new DeviceIdentityCreater();

            log.info("Connecting to port " + serverPort + " ...");

            try {
                ServerSocket serverSocket = new ServerSocket(serverPort);
                int idleTime = 5 * 1000;
                serverSocket.setSoTimeout(idleTime);
                while(isRunning) {
                    try {
                        log.info("TCPServer Waiting for client on port " + serverPort + " ...");
                        Socket connectSocket = serverSocket.accept();

                        log.info(" The client " + connectSocket.getRemoteSocketAddress() + ":" + connectSocket.getPort() + " is connected to "
                                + serverPort);

                        log.info("Waiting for data...");

                            VehicleDetailsStoreMan vehicleDetailsStoreMan = new VehicleDetailsStoreMan();
                            ByteStreamThread byteStreamThread=new ByteStreamThread(connectSocket,this,1000,vehicleDetailsStoreMan,deviceIdentityCreater);
                            readerThread = new Thread(byteStreamThread);
                            readerThread.start();
                        /*
                            StringStreamThread stringStreamThread = new StringStreamThread(connectSocket, this);
                            readerThread = new Thread(stringStreamThread, "Thread STRING receiver - " + connectSocket.getInetAddress());
                            readerThread.start();
                            */


                    } catch (SocketTimeoutException e) {
                        log.info("Time out !");
                        // break;
                    }
                }
                serverSocket.close();
            } catch (Exception e) {
                log.info("Connection error " + e.getMessage());
            }
        }

}
