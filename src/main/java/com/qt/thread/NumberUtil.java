package com.qt.thread;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/06/13 16:45
 * @version: V1.0
 */
public class NumberUtil {
    public static int addNum = 0;

    public static int add10(int num) {
        addNum = num;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addNum + 10;
    }
}
