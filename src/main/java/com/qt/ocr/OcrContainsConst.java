package com.qt.ocr;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/12/21 17:09
 * @version: V1.0
 */
public class OcrContainsConst {
    /**
     * 批准文号
     */
    public static List<String> containsPZWHList  = new ArrayList<>();

    static {
        containsPZWHList.add("国药准字");
    }

    /**
     * 商标
     */
    public static List<String> containsSBList  = new ArrayList<>();
    static {
        containsSBList.add("R");
        containsSBList.add("TM");
    }

    public static List<String>  containsCFYFLList  = new ArrayList<>();
    static {
        containsCFYFLList.add("食健字");
    }

}

