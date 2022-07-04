package com.qt.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/08/28 11:37
 * @version: V1.0
 */
public class DemoTime {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        service.execute(()->{

        });
    }
}
