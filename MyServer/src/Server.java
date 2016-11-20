/**
 * Created by XiaoR on 2016/11/16 0016.
 * 服务器从这里启动
 */
public class Server {
    public static final int UDP_PORT = 2020;
    public static final int TCP_PORT = 2021;
    public static final String SERVER_PATH = "C:\\servertest";

    public static void main(String[] args) {
        new TCPServer(SERVER_PATH,TCP_PORT).start();
        new UDPServer(SERVER_PATH,UDP_PORT).start();
    }
}
