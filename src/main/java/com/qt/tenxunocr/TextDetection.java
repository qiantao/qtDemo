package com.qt.tenxunocr;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/11/26 14:06
 * @version: V1.0
 */
public class TextDetection {
    /**
     * 解析结果
     */
    private String DetectedText;

    private Long Confidence;

    /**
     * 位置
     */
    private List<Polygon> Polygon;


    private String AdvancedInfo;

    private List<ItemPolygon> ItemPolygon;

    public String getDetectedText() {
        return DetectedText;
    }

    public void setDetectedText(String detectedText) {
        DetectedText = detectedText;
    }

    public Long getConfidence() {
        return Confidence;
    }

    public void setConfidence(Long confidence) {
        Confidence = confidence;
    }

    public List<Polygon> getPolygon() {
        return Polygon;
    }

    public void setPolygon(List<Polygon> polygon) {
        Polygon = polygon;
    }

    public String getAdvancedInfo() {
        return AdvancedInfo;
    }

    public void setAdvancedInfo(String advancedInfo) {
        AdvancedInfo = advancedInfo;
    }

    public List<ItemPolygon> getItemPolygon() {
        return ItemPolygon;
    }

    public void setItemPolygon(List<ItemPolygon> itemPolygon) {
        ItemPolygon = itemPolygon;
    }
}
