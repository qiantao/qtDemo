package thread;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/06/13 16:46
 * @version: V1.0
 */
public class DemoThread {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        System.out.println("开始时间:"+System.currentTimeMillis());
        String time = "开始时间:"+System.currentTimeMillis();
        FileOutputStream fis = null;
        try {

            fis = new FileOutputStream(new File("D:/1.txt"));
            fis.write(time.getBytes());
        }catch (Exception e){

        }finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 500; i++) {
            int num = (i%3)+1;
            String a = i+"";
            service.execute(()->{
                HttpUrlUtil.http(num);
            });
        }
        service.shutdown();
    }

}
