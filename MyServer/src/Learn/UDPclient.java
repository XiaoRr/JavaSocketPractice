package Learn;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by XiaoR on 2016/11/15 0015.
 */
public class UDPclient {
    public static void main(String[] args) throws IOException {
        /**
         * 向服务器端发送数据
         */
        //1.定义服务器的地址,端口号，数据
        InetAddress address = InetAddress.getByName("localhost");
        int port = 8800;
        byte[] data ="username:admin password:123".getBytes();
        //2.创建数据报
        DatagramPacket packet = new DatagramPacket(data,data.length,address,port);

        //3.创建一个DatagramSocket对象
        DatagramSocket socket = new DatagramSocket();

        //4.向服务器端发送数据报
        socket.send(packet);

        /**
         * 接收服务器端响应的数据
         */

        //1.创建数据报，用于接受服务器端响应的数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2,data2.length);
        //2.接受服务器端响应的数据
        socket.receive(packet2);
        //3.读取数据
        String reply = new String (data2,0,packet2.getLength());
        System.out.println("我是客户端，服务器说："+reply);
        //4.关闭资源
        socket.close();

    }
}
