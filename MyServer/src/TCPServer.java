import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by XiaoR on 2016/11/16 0016.
 * tcp服务器，支持多线程
 */
public class TCPServer extends Thread {
    private int port;
    private String path;

    public TCPServer(String path, int port) {
        this.port = port;
        this.path = path;
    }

    public void run() {
        try {
            ServerSocket server = new ServerSocket(port);

            /**
             * 校验目录
             */
            //System.out.println("指出服务器根目录");
            File file = new File(path);
            if (file.isDirectory()) {
                System.out.println("目录 " + path + " 确认成功");
            } else {
                System.out.println("目录 " + path + " 不存在");
                return;
            }
            System.out.println("Tcp服务器端已启动");
            /**
             * 每个链接产生一个线程
             */
            while (true) {
                new TCPThread(server.accept(), file).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
