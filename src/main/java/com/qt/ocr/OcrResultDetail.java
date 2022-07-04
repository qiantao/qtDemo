package com.qt.ocr;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/12/07 11:24
 * @version: V1.0
 */
public class OcrResultDetail {
    private int key;
    private String name;
    private String value;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
