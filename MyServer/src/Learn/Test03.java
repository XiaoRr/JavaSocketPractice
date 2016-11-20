package Learn;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by XiaoR on 2016/11/15 0015.
 */
public class Test03 {
    public static void main(String[] args) {
        URL url = null;
        BufferedReader br = null;
        try {
            url = new URL("http://www.imooc.com");
            br = new BufferedReader(
                    new InputStreamReader(url.openStream(),"utf-8"));

            String data = br.readLine();
            while(data!=null){
                System.out.println(data);
                data = br.readLine();
            }
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
