import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by XiaoR on 2016/11/16 0016.
 */
public class UDPClient {
    public static DatagramSocket dataSocket;
    public static final int PORT = 2020;
    public static byte[] receiveByte;
    public static DatagramPacket dataPacket;
    private final int size;

    public String defaultReceivePath ;
    private String filename;
    private String path;
    ObjectInputStream clientInputStream = null;
    public UDPClient(String defaultReceivePath, String filename, String path,int size) {
        this.defaultReceivePath = defaultReceivePath;
        this.path = path;
        this.filename = filename;
        this.size = size;
    }

    public boolean getFile() {
        try {
            /**
             * 向服务器端发送数据
             */
            //1.定义服务器的地址,端口号，数据
            InetAddress address = InetAddress.getByName("localhost");
            int port = 2020;
            byte[] data =(path).getBytes();
            //2.创建数据报
            DatagramPacket packet = new DatagramPacket(data,data.length,address,port);

            //3.创建一个DatagramSocket对象
            DatagramSocket socket = new DatagramSocket();

            //4.向服务器端发送数据报
            socket.send(packet);
            /**
             * 接收服务器端响应的数据
             */
            receiveByte = new byte[1024];

            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(defaultReceivePath + "\\" + filename)));

            int times = size/ 1024 ;// 循环接收的次数
            System.out.println(filename+" 传输中");
            System.out.println("[-----------------------------]");
            int tmp = size/32;
            for (int c = 0; c < times; c++) {
                dataPacket = new DatagramPacket(receiveByte, receiveByte.length);
                socket.receive(dataPacket);
                fileOut.write(receiveByte, 0, dataPacket.getLength());
                if(c%tmp==0) System.out.print(">");
            }
            if(times==0) System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            dataPacket = new DatagramPacket(receiveByte, receiveByte.length);
            socket.receive(dataPacket);
            fileOut.write(receiveByte, 0, size % 1024);     //剩余部分的接受
            fileOut.flush();
            fileOut.close();

            System.out.println("\n接收完毕,文件大小(b)：" + size);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
        }
    }
}
