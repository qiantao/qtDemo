package com.qt.ocr;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/12/07 11:24
 * @version: V1.0
 */
public class OcrResult {
    private String filePath;
    private List<OcrResultDetail> ocrResultDetailList;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<OcrResultDetail> getOcrResultDetailList() {
        return ocrResultDetailList;
    }

    public void setOcrResultDetailList(List<OcrResultDetail> ocrResultDetailList) {
        this.ocrResultDetailList = ocrResultDetailList;
    }
}
