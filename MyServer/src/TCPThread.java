/**
 * Created by XiaoR on 2016/11/13 0013.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPThread extends Thread {
    private Socket client;
    static private File root;  //根路径
    private File file;  //当前路径

    public TCPThread(Socket c, File file) {
        this.client = c;
        root = file;
        this.file = file;
    }

    public void showAllFile(PrintWriter out, File file) {
        out.println("当前地址:  " + file.getAbsolutePath());
        String[] filelist = file.list();
        for (int j = 0; j < filelist.length; j++) {
            File readfile = new File(file + "\\" + filelist[j]);
            if (!readfile.isDirectory()) {
                out.printf("%-10s%-16s%-16s\n", "<file>", readfile.getName(), readfile.length()+"b");
            } else {
                out.printf("%-10s%-16s%-16s\n", "<dir>", readfile.getName(), "...");
            }
        }
        out.flush();
    }

    public static File dirJump(PrintWriter out,File file,String s){
        if(s.equals("..")){
            if(file.equals(root)){
                out.println("已在根目录");
                return file;
            }
            out.println("已跳转至"+file.getParentFile());
            return file.getParentFile();
        }

        File tmpfile = new File(file + "\\" + s);
        if(!tmpfile.isDirectory()) {
            out.println("非法路径");
            return file;
        }
        out.println("已跳转至"+tmpfile);
        return tmpfile;
    }

    public static void getFile(PrintWriter out,File file,String s){
        File tmpFile = new File(file + "\\" + s);
        if(tmpFile.isFile()){
            out.println("OK");  //返回许可
            out.println(tmpFile.getAbsolutePath());     //返回文件完整路径
            out.println(s);     //返回文件名
            out.println(tmpFile.length());  //返回文件大小
        }else{
            out.println(tmpFile+" 不存在");
        }
    }
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream())
        ) {
            System.out.printf("%s:%s>连接成功\n", client.getInetAddress().getHostAddress(),client.getPort());
            toEnd:
            while (true) {
                String str = in.readLine();
                String[] strarray = str.split(" ");
                switch (strarray[0]) {
                    //显示文件列表
                    case "ls":
                        showAllFile(out, file);
                        break;

                    //目录跳转
                    case "cd":
                        if (strarray.length != 2) {
                            out.println("参数数量应为1");
                            break;
                        }
                        file = dirJump(out,file,strarray[1]);
                        break;

                    //获取文件
                    case "get":
                        if (strarray.length != 2) {
                            out.println("参数数量应为1");
                            break;
                        }
                        getFile(out,file,strarray[1]);
                        break;

                    //关闭服务器
                    case "bye":
                        out.println("bye");
                        out.flush();
                        System.out.printf("%s:%s>已退出", client.getInetAddress().getHostAddress(),client.getPort());
                        break toEnd;

                    //返回父级目录
                    case "cd..":
                        file = dirJump(out,file,"..");
                        break;

                    default:
                        out.println("未知参数["+strarray[0]+"]");
                }
                out.println("");
                out.flush();
            }
        } catch (IOException ex) {
            System.out.printf("%s:%s>连接断开\n", client.getInetAddress().getHostAddress(),client.getPort());
        } finally{
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}