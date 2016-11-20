import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by XiaoR on 2016/11/15 0015.
 * 基于udp的文件传输
 */
public class UDPThread extends Thread {
    public static DatagramSocket dataSocket;
    public static DatagramPacket dataPacket;
    private File path;
    private InetAddress address;
    private int port;
    public UDPThread(DatagramSocket dataSocket, File path, InetAddress address, int port) {
        this.dataSocket = dataSocket;
        this.path = path;
        this.address = address;
        this.port = port;
    }

    public void run() {

        /**
         * 向客户端响应数据
         */
        DataInputStream fis;
        try {
            fis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
            byte[] sendDataByte = new byte[1024];
            int read = 0;
            while (fis.read(sendDataByte) > 0) {
                dataPacket = new DatagramPacket(sendDataByte, sendDataByte.length,address, port);
                dataSocket.send(dataPacket);
                TimeUnit.MICROSECONDS.sleep(1);// 限制传输速度
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
