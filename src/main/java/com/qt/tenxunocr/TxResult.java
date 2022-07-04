package com.qt.tenxunocr;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/11/26 14:10
 * @version: V1.0
 */
public class TxResult {
    private List<TextDetection> TextDetections;
    private String Language;
    private String Angel;
    private String RequestId;

    public List<TextDetection> getTextDetections() {
        return TextDetections;
    }

    public void setTextDetections(List<TextDetection> textDetections) {
        TextDetections = textDetections;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getAngel() {
        return Angel;
    }

    public void setAngel(String angel) {
        Angel = angel;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }
}
