package Learn;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by XiaoR on 2016/11/15 0015.
 */
public class Test02 {
    public static void main(String[] args) throws UnknownHostException, MalformedURLException {

        //创建一个URL实例
        URL imooc = new URL("http://www.imooc.com");
        URL url  = new URL(imooc,"/index.html?username=tom#test");//问号表示参数，#号表示锚点
        System.out.println("协议："+url.getProtocol());
        System.out.println("主机"+url.getHost());

        //如果未指定端口号返回默认端口号
        //http使用80,但是getport返回-1
        System.out.println("端口"+url.getPort());

        System.out.println("文件路径+"+url.getPath());
        System.out.println("文件名"+url.getFile());
        System.out.println("相对路径"+url.getRef());
        System.out.println("查询字符串:"+url.getQuery());



    }
}
