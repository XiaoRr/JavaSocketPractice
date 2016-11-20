package Learn;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by XiaoR on 2016/11/15 0015.
 */
public class Test01 {

    public static void main(String[] args) throws UnknownHostException {
        //获取本机的InetAddress实例
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("计算机名:"+address.getHostName());
        System.out.println("ip地址:"+address.getHostAddress());
        byte[] bytes = address.getAddress();//获取字节数组形式的ip地址
        System.out.println("字节数组形式的ip:"+ Arrays.toString(bytes));
        System.out.println(address);//直接输出InetAddress对象

        //根据机器名获取InetAddress实例
        //InetAddress address2 = InetAddress.getByName("DESKTOP-IAU6N84");

    }
}
