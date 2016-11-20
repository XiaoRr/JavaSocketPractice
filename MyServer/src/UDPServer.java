import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by XiaoR on 2016/11/16 0016.
 * 接受数据的端口，支持多线程
 */
public class UDPServer extends Thread{
    private int port;
    private String path;

    public UDPServer(String path, int port) {
        this.port = port;
        this.path = path;
    }

    public void run(){
        //1.创建服务器端DatagramSocket，指定端口
        try (DatagramSocket socket = new DatagramSocket(port)){

            //2.创建数据报，用于接受客户端发送的数据
            byte[] data = new byte[1024];   //创建字节数组，指定接受的数据报的大小
            DatagramPacket packet = new DatagramPacket(data, data.length);
            System.out.println("Udp服务器端已启动");

            while (true) {
                //3.接受客户端发送的数据
                socket.receive(packet); //此方法在接收到数据报之前一直会阻塞

                //4.读取数据
                String filePath = new String(data, 0, packet.getLength());

                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                System.out.printf("%s:%s>请求获取%s\n", address, port,filePath);

                new UDPThread(socket, new File(filePath), address, port).start();  //启动传输文件的线程
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
