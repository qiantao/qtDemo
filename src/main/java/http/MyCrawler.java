package http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

public class MyCrawler {

    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++){
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }


    /**
     * 抓取过程
     *
     * @param seeds
     * @return
     */
    public void crawling(String[] seeds) {

        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);

        //定义过滤器，提取以 http://www.baidu.com 开头的链接
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith("http://www.baidu.com"))
                    return true;
                else
                    return false;
            }
        };

        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty()  && Links.getVisitedUrlNum() <= 1000) {

            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null){
                continue;
            }

            //根据URL得到page;
            Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

            //对page进行处理： 访问DOM的某个标签
            Elements es = PageParserTool.select(page,"a");
            if(!es.isEmpty()){
                System.out.println("下面将打印所有a标签： ");
                System.out.println(es);
            }

            //将保存文件
            FileTool.saveToLocal(page);

            //将已经访问过的链接放入已访问的链接中；
            Links.addVisitedUrlSet(visitUrl);

            //得到超链接
            Set<String> links = PageParserTool.getLinks(page,"img");
            for (String link : links) {
                Links.addUnvisitedUrlQueue(link);
                System.out.println("新增爬取路径: " + link);
            }
        }
    }


    //main 方法入口
    public static void main(String[] args) {
//        MyCrawler crawler = new MyCrawler();
//        crawler.crawling(new String[]{"http://www.baidu.com"});

//        String u = "http://search.anccnet.com/searchResult2.aspx?keyword=6924168200161";
////        String u = "http://www.baidu.com";
//        //根据URL得到page;
//        Page page = RequestAndResponseTool.sendRequstAndGetResponse(u);
//
//        //对page进行处理： 访问DOM的某个标签
//        Elements es = PageParserTool.select(page,"a");
//        if(!es.isEmpty()){
//            System.out.println("下面将打印所有a标签： ");
//            System.out.println(es);
//        }else{
//            System.out.println("1111");
//        }
//
//        //将保存文件
//        FileTool.saveToLocal(page);


        String url = "http://search.anccnet.com/searchResult2.aspx?keyword=6924168200161";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("Referer","http://search.anccnet.com/searchResult2.aspx?keyword=6924168200161");
//        httpHeaders.set("Cookie","ASP.NET_SessionId=l1bk4o555iwr23burpoiobek");
//        httpHeaders.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        httpHeaders.set("Accept-Encoding", "gzip, deflate");
//        httpHeaders.set("Accept-Language",
//                "zh-CN,zh;q=0.9");
        httpHeaders.set("Cookie", " ASP.NET_SessionId=0uerjbfhbgdox245usfsak45");
//        httpHeaders.set("Host","search.anccnet.com");
//        httpHeaders.set("Proxy-Connection","keep-alive");
        httpHeaders.set("Referer", "http://search.anccnet.com/searchResult2.aspx?keyword=6924168200161");
//        httpHeaders.set("Upgrade-Insecure-Requests","1");
//        httpHeaders.set("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
        HttpEntity<Map<String, Object>> reMapHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, reMapHttpEntity, String.class);
//        String mutiData = restTemplate.getForObject(url, String.class, reMapHttpEntity);
        Document document = Jsoup.parse(exchange.getBody());
        document.getElementById("results");

        document.getElementsByClass("p-supplier");
        document.getElementsByClass("p-info");

    }


}