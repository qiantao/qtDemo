package com.qt.ocr;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 网络图片文字识别
 * @author: QianTao
 * @date: 2020/11/24 11:43
 * @version: V1.0
 */
public class WebImge {
    public final static String basePath ="C:/Users/QT/Desktop/ocr/";
    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String webImage(String filename) {
        // 请求url
        Map<String,String> urlMap = new HashMap<>();
//        urlMap.put("标准版","https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic");
        urlMap.put("高精度版","https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic");
//        urlMap.put("高精度含位置版","https://aip.baidubce.com/rest/2.0/ocr/v1/accurate"); //不支持url传参
//        urlMap.put("标准含位置版","https://aip.baidubce.com/rest/2.0/ocr/v1/general");
//        urlMap.put("网络图片","https://aip.baidubce.com/rest/2.0/ocr/v1/webimage");

//        urlMap.put("办公文档识别","https://aip.baidubce.com/rest/2.0/ocr/v1/doc_analysis_office");
//        urlMap.put("网络图片文字识别（含位置版）","https://aip.baidubce.com/rest/2.0/ocr/v1/webimage_loc");

        try {

            // 本地文件路径
            String filePath = basePath+filename;
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            param = param+"&detect_direction=true";
//            String param = "url="+"http://hydeecloud-test.oss-cn-shanghai.aliyuncs.com/h3/images/goods/100120512/20201207/artwork/333-1607326182125.jpg";

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.8164afef968b103aa4d52d6e97e096a8.2592000.1623119670.282335-23116428";
            StringBuffer buf = new StringBuffer();

            for (String key : urlMap.keySet()) {
                String url = urlMap.get(key);
                String result = HttpUtil.post(url, accessToken, param);

                System.out.println(result);
                System.out.println("解析"+key+"开始");
//                List<OcrResultDetail> conditionValue = OcrMatchWareEnum.getConditionValue(analysisBaiDuResult(result));
//                System.out.println(JSONUtil.objectToString(conditionValue));

            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private static List<String> analysisBaiDuResult(String result){
//        BaiDuResult baiDuResult = (BaiDuResult) JsonUtil.stringToObject(result,BaiDuResult.class);
//        if(baiDuResult==null){
//            return new ArrayList<>();
//        }
//        List<WordResult> words_result = baiDuResult.getWords_result();
//        if(words_result==null){
//            return new ArrayList<>();
//        }
//        return analysisWordResult(words_result);
//    }
//
//    private static List<String> analysisWordResult(List<WordResult> wordResultList){
//        List<String> resultList = new ArrayList<>();
//        wordResultList.forEach(e->{
//            resultList.add(e.getWords().replace(" ",""));
//        });
//        return resultList;
//    }

    public static void main(String[] args) {
        List<String> fileNameList = new ArrayList<>();
//        fileNameList.add("aa.jpg");
        fileNameList.add("aa.png");
        for (String fileName : fileNameList) {
            WebImge.webImage(fileName);
//            StringBuffer buf = new StringBuffer();
//            buf.append("//腾讯通用版\n").append(TxOCR.OCRIMG_Basic(fileName)).append("\n");
//            buf.append("//腾讯通用版(高精度版)\n").append(TxOCR.OCRIMG_Accurate(fileName)).append("\n");
//            buf.append("//腾讯通用版(快速版)\n").append(TxOCR.OCRIMG_Fast(fileName)).append("\n");
//            Demo.StringToFile(basePath+"腾讯-"+fileName.substring(0,fileName.indexOf("."))+".txt",buf.toString());

        }
    }
}