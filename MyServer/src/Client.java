/**
 * Created by XiaoR on 2016/11/13 0013.
 * 客户端从这里启动
 */

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    static Socket server;
    private static final String SAVEPATH = "C:\\clienttest";
    public static void main(String[] args) throws Exception {

        server = new Socket("localhost", 2021);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                server.getInputStream()));
             PrintWriter out = new PrintWriter(server.getOutputStream());
             BufferedReader wt = new BufferedReader(new InputStreamReader(System.in))
        ) {

            while (true) {
                String str = wt.readLine();
                out.println(str);
                out.flush();
                outLoop:
                while (true) {
                    String tmp = in.readLine();
                    switch (tmp){
                        case "":
                            break outLoop;

                        case "OK":
                            //准备请求文件
                            String path = in.readLine();    //获取udp文件路径
                            String name = in.readLine();    //获取udp文件名
                            String size = in.readLine();    //获取文件大小
                            UDPClient udpClient = new UDPClient(SAVEPATH,name,path,Integer.parseInt(size));     //创建一个udp客户端
                            if(!udpClient.getFile()){
                                System.out.println("接受数据失败");
                            }
                            break;

                        case "bye":
                            System.out.println("已退出");
                            return;
                        default:
                            System.out.println(tmp);
                    }
                }

            }
        }catch (IOException e){
            System.out.println("服务器端已断开");
        }
        finally {
            server.close();
        }
    }
}