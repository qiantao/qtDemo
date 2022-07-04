//package url;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.hydee.common.util.JsonUtil;
//import jdk.internal.org.objectweb.asm.TypeReference;
//import jdk.nashorn.internal.runtime.GlobalFunctions;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @ClassName:
// * @Description:
// * @author: QianTao
// * @date: 2019/06/14 9:50
// * @version: V1.0
// */
//public class URLDemo {
//    public static void main(String[] args) throws Exception {
////        String path = "https://s.taobao.com/search?q=&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20190614&ie=utf8";
////        String path = "https://s.taobao.com/search?q=%E7%9F%AD%E8%A3%A4&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20190614&ie=utf8";
////        1 6 6
////        2 3 3
////        3 0 6
//        String query = "小";
//        int pageIndex= 1;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String ie = "utf8";
//        String initiative_id = "staobaoz_"+sdf.format(new Date());
//        String stats_click="stats_click%3A1";
//        String js = "1";
//        String imgfile="";
//        String q ="%E7%9F%AE%E4%B8%AA%E5%AD%90%E8%BF%9E%E8%A1%A3%E8%A3%99";
//        int bcoffset = (7-pageIndex*3);
//        int ntoffset = (7-pageIndex*3);
//        int s = (pageIndex-1)*44;
//        String path = "https://s.taobao.com/search?" +
//                "ie=" +ie+
//                "&initiative_id=" +initiative_id+
//                "&stats_click=" +stats_click+
//                "&js=" +js+
//                "&imgfile=" +imgfile+
//                "&q=" +q+
////                "&suggest=0_2" +
////                "&_input_charset=utf-8" +
////                "&wq=a" +
////                "&suggest_query=a" +
////                "&source=suggest" +
//                "&bcoffset=" +bcoffset+
//                "&ntoffset=" +ntoffset +
//                "&p4ppushleft=1%2C48" +
//                "&s="+s;
////        String path="https://s.taobao.com/search?" +
////                "q=%E6%9D%A8%E8%B6%85%E8%B6%8A%E5%90%8C%E6%AC%BE%E8%BF%9E%E8%A1%A3%E8%A3%99" +
////                "&imgfile=" +
////                "&commend=all" +
////                "&ssid=s5-e" +
////                "&search_type=item" +
////                "&sourceId=tb.index" +
////                "&spm=a21bo.2017.201856-taobao-item.2" +
////                "&ie=utf8" +
////                "&initiative_id=tbindexz_20170306" +
////                "&hintq=1" +
////                "&bcoffset=" +bcoffset+
////                "&ntoffset=" +ntoffset +
////                "&p4ppushleft=1%2C48" +
////                "&s="+s;
//        String cookie = "cna=NjmPFZ7SLDoCAXTt0nbaJcpi; t=f5501fcb4370260b51985cbc79c86989; _m_h5_tk=bff83f0265d2cce966ee59684c99a10c_1560832551082; _m_h5_tk_enc=5c2263a1ace2cbf1e6cc7b7afff6084d; cookie2=176ac5dd25728da6b4d15866a775391e; _tb_token_=eef5133f76b9e; _cc_=URm48syIZQ%3D%3D; tg=0; thw=cn; enc=GXUBICvxN8WlsYrZpOzihkmnbzwdWq8aFWg8I9Ukb5TOdivyeuJaZd%2FIfp3E%2FyINsi%2BdbYVCyx%2BdbBukupyJOw%3D%3D; _uab_collina=156082512272524161723142; hng=CN%7Czh-CN%7CCNY%7C156; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; swfstore=101655; x5sec=7b227365617263686170703b32223a226238633638353832363663313030376162373765353666336166303535636134434d57636f656746454c76332b2f43786e4c4f477a774561445449794d5451314e5463784d6a59374d54673d227d; mt=ci=0_0; v=0; alitrackid=www.taobao.com; lastalitrackid=www.taobao.com; JSESSIONID=5E61D410BF44FEE2F65628D678536706; l=bBjFvQvVqjShFDIDBOCgZuIRhe7OSIRAguPRwRD9i_5CB6L6k6bOkfeNDFp6Vj5RsELB4k5AU9w9-etki; isg=BOfny7XVdvZXXfJ64rw22u14dhtxxLG7Bq66uLlUA3adqAdqwTxLniWqyuiTQJPG";
////        String charset = "UTF-8";
//        String charset = "GBK";
////        String charset = "GBK";
//        System.out.println(path);
//        getURLinfo(path,charset,query,cookie);
//    }
//    public static void getURLinfo(String path,String charset,String query,String cookie) throws Exception{
//        //读取目的网页URL地址，获取网页源码
//        URL url = new URL(path);
////        url.decodeURIComponent
//        HttpURLConnection httpUrl = (HttpURLConnection)url.openConnection();
//        httpUrl.setRequestMethod("GET");
////        GlobalFunctions.decodeURIComponent()
////        httpUrl.setRequestProperty("eagleeye-traceid","0b8062e115604937418943688e840f");
////        httpUrl.setRequestProperty("set-cookie","JSESSIONID=24EEC96938777EE94E9448C8A4DE2FEB; Path=/; HttpOnly");
////        String cookie = "cna=+VMjFZBcQkYCAXJcha5TZ+Sp; v=0; _tb_token_=7fe39583d1053; t=202370e7266acf4ff0641dbb7a9f5274; skt=ee41cec199f65772; cookie2=1071fc0425c794f70f5ccd62afe6adad; csg=67b5cdd7; uc3=vt3=F8dBy3ke0xrlV7eaU%2BA%3D&id2=UUpgQhWS2K2C%2BQ%3D%3D&nk2=saDRjw%2FilsZjAj5B&lg2=U%2BGCWk%2F75gdr5Q%3D%3D; existShop=MTU2MDQ4NTA1MQ%3D%3D; tracknick=%5Cu4E00%5Cu4EBA%5Cu4E00%5Cu53E3%5Cu592A%5Cu5751; lgc=%5Cu4E00%5Cu4EBA%5Cu4E00%5Cu53E3%5Cu592A%5Cu5751; _cc_=URm48syIZQ%3D%3D; dnk=%5Cu4E00%5Cu4EBA%5Cu4E00%5Cu53E3%5Cu592A%5Cu5751; tg=0; enc=ZRVExlswLol%2BctCpgu041IqyAY2lZpl2vl7l%2F9YmQ%2FST7A8Xe%2BbxfYR1FI%2FLlYEXvzqpLeZ77913tE7MquV97w%3D%3D; mt=ci=0_1; hng=CN%7Czh-CN%7CCNY%7C156; thw=cn; uc1=cookie14=UoTaGOxSyS8B4w%3D%3D&cookie15=UIHiLt3xD8xYTw%3D%3D; x=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; ctoken=Fp5WZd8Y71rNbiwls8e7bnue; _m_h5_tk=231b25a766e9eb0dd856538f0ce9b54c_1560750376685; _m_h5_tk_enc=d1ae03b561644032766c0f5f54acda93; l=bBahEbQlqO5FaTSBBOCg5uIRhe79KIRVguPRwRD9i_5CR1TsIe7Okbcfie96Vj5l_JTB49vt2xpteU_0Jn5..; isg=BGdnTiEj9nmD_nLxNSVMC5rI9pvxRDE7d4esvTnUwPYdKIXqQbigHvAqTmgTwBNG";
//
////        httpUrl.setRequestProperty("cookie",cookie);
//        InputStream is = httpUrl.getInputStream();
//        BufferedReader br = new BufferedReader(new InputStreamReader(is,charset));
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = br.readLine()) != null) {
//            //这里是对链接进行处理
////            line = line.replaceAll("</?a[^>]*>", "");
////            //这里是对样式进行处理
////            line = line.replaceAll("<(\\w+)[^>]*>", "<$1>");
//            line = line.replaceAll("[<]*[\\w]*[\\s]*[=\"'/{:,.}()-;?\\\\\\[\\]!@#$%^&|]*","");
//            sb.append(line);
//        }
//        is.close();
//        br.close();
//        //获得网页源码
//        String str = sb.toString();
//        if(str.contains("非 霸下验证页面")){
//            System.out.println("验证页面");
//            return;
//        }
//        System.out.println(str);
////        str = str.replaceAll("g_page_config =","-------");
//        String[] strs = str.split("g_page_config =");
//        if(strs.length<2) return;
//        str = strs[1];
//        strs = str.split("g_srp_loadCss()");
//        str = strs[0];
//        str = str.trim();
//        while (str.endsWith(";")){
//            str = str.substring(0,str.length()-1);
//        }
//        List<String> strList = new ArrayList<>();
//        System.out.println(str);
//        JSONObject obj = JSON.parseObject(str);
//        JSONObject mods = (JSONObject)obj.get("mods");
//        JSONObject itemlist = (JSONObject)mods.get("itemlist");
//        JSONObject data = (JSONObject)itemlist.get("data");
//        JSONArray auctions = data.getJSONArray("auctions");
//        //页码
//        JSONObject pager = (JSONObject)mods.get("pager");
//        JSONObject pagerData = (JSONObject)pager.get("data");
//        String totalPage = pagerData.get("totalPage").toString();
//        String pageSize = pagerData.get("pageSize").toString();
//        String currentPage = pagerData.get("currentPage").toString();
//        String totalCount = pagerData.get("totalCount").toString();
//
//        for (int i = 0; i< auctions.size();i++){
//            if(i>=44) break;
//            JSONObject a = auctions.getJSONObject(i);
//            String title = a.get("title").toString();
//            title = title.replace("<span class=H>","");
//            title = title.replace("</span>","");
//            int lineNo = i/4+1;
//            int col = i%4+1;
//            if(title.contains(query)){
//                System.out.println("第 "+currentPage+"页, 第 "+lineNo+"行,第" +col+"个");
//            }
//            strList.add(title);
//        }
//        System.out.println(JsonUtil.objectToString(strList));
//
//
//
//        System.out.println("共 "+totalPage +" 页 ,当前第 "+ currentPage + "页 , 共 "+totalCount+" 条,此页有 "+pageSize+" 条");
//    }
//}
