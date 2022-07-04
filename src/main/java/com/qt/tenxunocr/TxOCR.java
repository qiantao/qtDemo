package com.qt.tenxunocr;

import cn.hutool.core.convert.Convert;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.*;

import java.util.List;

;

// 导入对应产品模块的 client
// 导入要请求接口对应的 request response 类
//导入可选配置类

// 导入对应产品模块的 client
// 导入要请求接口对应的 request response 类
//导入可选配置类

/**
 * @ClassName:
 * @Description:
 * AppId: 1304331767
 * SecretId: AKID0lHWUD8qkb3SaYTiupJXgR0KN9elwxg9
 * SecretKey: NRF9oENGPIAoGVUEgusiLjLksjRtFLDz
 * @author: QianTao
 * @date: 2020/11/25 14:59
 * @version: V1.0
 */
public class TxOCR {
    public static final String SecretId = "AKID0lHWUD8qkb3SaYTiupJXgR0KN9elwxg9";
    public static final String SecretKey = "NRF9oENGPIAoGVUEgusiLjLksjRtFLDz";
    public static final String basePath = "C:/Users/QT/Desktop/ocr/";

    /**
     * 通用版
     * @return
     */
    public static String OCRIMG_Basic(String filePath){
        try{

            Credential cred = new Credential(SecretId, SecretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);

            String img64 = OCRUtil.base64Img(basePath+filePath);
            GeneralBasicOCRRequest req = new GeneralBasicOCRRequest();
            req.setImageBase64(img64);
            //默认中英文混合(zh)
            //zh\auto\jap\kor\ spa\fre\ger\por\ vie\may\rus\ita\ hol\swe\fin\dan\ nor\hun\tha\lat\ara
            //可选值分别表示： 中英文混合、自动识别、日语、韩语、 西班牙语、法语、德语、葡萄牙语、 越南语、马来语、俄语、意大利语、 荷兰语、瑞典语、芬兰语、丹麦语、 挪威语、匈牙利语、泰语、拉丁语系、 阿拉伯语。
            req.setLanguageType("zh");

            GeneralBasicOCRResponse resp = client.GeneralBasicOCR(req);

            return analysisTXResult(GeneralBasicOCRResponse.toJsonString(resp));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * 通用版(高速版)
     * @return
     */
    public static String OCRIMG_Fast(String filePath){
        try{

            Credential cred = new Credential("AKID0lHWUD8qkb3SaYTiupJXgR0KN9elwxg9", "NRF9oENGPIAoGVUEgusiLjLksjRtFLDz");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);

            String img64 = OCRUtil.base64Img(basePath+filePath);
            GeneralFastOCRRequest req = new GeneralFastOCRRequest();
            req.setImageBase64(img64);

            GeneralFastOCRResponse resp = client.GeneralFastOCR(req);

            return analysisTXResult(GeneralFastOCRResponse.toJsonString(resp));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "";
    }

    /**
     * 通用版(高精度版)
     * @param filePath
     * @return
     */
    public static String OCRIMG_Accurate(String filePath){
        try{

            Credential cred = new Credential("AKID0lHWUD8qkb3SaYTiupJXgR0KN9elwxg9", "NRF9oENGPIAoGVUEgusiLjLksjRtFLDz");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);
            String img64 = OCRUtil.base64Img(basePath+filePath);
            GeneralAccurateOCRRequest req = new GeneralAccurateOCRRequest();
            req.setImageBase64(img64);

            GeneralAccurateOCRResponse resp = client.GeneralAccurateOCR(req);

            return analysisTXResult(GeneralAccurateOCRResponse.toJsonString(resp));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return "";
    }

    public static String analysisTXResult(String result){
        StringBuffer buf = new StringBuffer();
        TxResult txResult = Convert.convert(TxResult.class,result);
        List<TextDetection> textDetections = txResult.getTextDetections();
        textDetections.forEach(e->{
            buf.append(e.getDetectedText()).append("\n");
        });
        return buf.toString();
    }

}
