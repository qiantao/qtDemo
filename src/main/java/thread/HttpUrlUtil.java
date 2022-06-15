package thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/06/13 16:48
 * @version: V1.0
 */
public class HttpUrlUtil {

    private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public synchronized static Date parse(String dateStr) {

        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        http(1);
    }

    public static void http(int i){
        String result = "";
        BufferedReader in = null;
//        String url = "http://localhost:8020/add?oid="+i+"&stock="+i+"&integral="+i;
//        String url = "http://localhost:8090/public/order/test?payType="+i;
        String url = "http://search.anccnet.com/searchResult2.aspx?keyword=6924168200161";
        try {
            String urlNameString = url ;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language",
                    "zh-CN,zh;q=0.9");
            connection.setRequestProperty("Cookie","ASP.NET_SessionId=l1bk4o555iwr23burpoiobek");
            connection.setRequestProperty("Host","search.anccnet.com");
            connection.setRequestProperty("Proxy-Connection","keep-alive");
            connection.setRequestProperty("Referer","http://search.anccnet.com/searchResult2.aspx?keyword=6924168200161");
            connection.setRequestProperty("Upgrade-Insecure-Requests","1");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
            // 建立实际的连接
            connection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"ISO-8859-1"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
//            System.out.println(i +"--" +result);
//            System.out.println(i + "--结束时间 " +System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println(i + "发送GET请求出现异常！" + e);
//            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
