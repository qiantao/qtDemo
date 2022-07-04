package com.qt.ui;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/08/29 11:31
 * @version: V1.0
 */
public class FrameMethod extends UIFrame{
    private Logger log = LoggerFactory.getLogger(this.getClass());

    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JButton b4 = new JButton();
    JButton b5 = new JButton();
    JButton b6 = new JButton();
    JButton b7 = new JButton();
    JButton b8 = new JButton();
    JButton b9 = new JButton();
    Map<Integer,JButton> dsMap = new HashMap<>();
    Map<Integer,Boolean> dsBoMap = new HashMap<>();
    boolean exit = false;
    int integral = 0;
    Dsthread dst;
    @Override
    public void show(){
        super.show();
        r();
        startT();
    }

    public void r(){
        dst = new Dsthread();
//        dst.run();
        executorService.execute(dst);
    }

    public void threadBtn(){
        btnThread.setBounds(1100, 660, 100, 100);
        frame.add(btnThread);
        btnThread.addMouseListener(new SubMouseListener() {
            public void mouseClicked(MouseEvent e) {
                startT();
            }

        });
    }

    class Dsthread extends Thread{
        @Override
        public void run() {
            while (true) {
//                log.error("exit={}",exit);
                if (!exit) {
                    int i = (int) (Math.random() * 10);
                    log.error("第{}格", i);
                    JButton temp = dsMap.get(i);
                    if (temp == null) {
                        log.error("随机数错误  i={}", i);
                        continue;
                    }
                    temp.setText("月:来打我呀~~");
                    exit = true;
                    dsBoMap.put(i,true);
                    refresh();
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static String printCicle(){
        StringBuffer buf = new StringBuffer();
        buf.append("\n\t");
        // 分三个大部分 上中下

        for (int i = 0, k = 0; i < 14; i++) {// 打印行

            // 上部分 上分为 四个部分

            if (i < 3) {

                for (int j = 0; j < 5 - 2 * i; j++) {// 1、空心

                    buf.append(" ");

                }

                if (i == 2) {// 2、*

                    for (int j = 0; j < 6 + 4 * i - 1; j++) {

                        buf.append("*");

                    }

                    for (int j = 0; j < 7 - 4 * i + 2; j++) {// 3、空心

                        buf.append(" ");

                    }

                    for (int j = 0; j < 6 + 4 * i - 1; j++) {// 4、*

                        buf.append("*");

                    }

                } else {

                    for (int j = 0; j < 6 + 4 * i; j++) {// 2、*

                        buf.append("*");

                    }

                    for (int j = 0; j < 7 - 4 * i; j++) {// 3、空心

                        buf.append(" ");

                    }

                    for (int j = 0; j < 6 + 4 * i; j++) {// 4、*

                        buf.append("*");

                    }

                }

            } else if (i < 6) {// 中间

                for (int j = 0; j < 29; j++) {

                    buf.append("*");

                }

            } else {// 下部分 6

                if (i == 13) {

                    for (int j = 0; j < 2 * (i - 6); j++) {// 打印空格

                        buf.append(" ");

                    }

                    buf.append("*");

                } else {

                    for (int j = 0; j < 2 * (i - 6) + 1; j++) {// 打印空格

                        buf.append(" ");

                    }

                    for (int j = 1; j < 28 - 4 * k; j++) {

                        buf.append("*");

                    }

                    k++;

                }

            }

            buf.append("\n\t");// 换行

        }

        return buf.toString();
    }

    public void ds(){
        b1.setBounds(0,200,100,100);
        b2.setBounds(100,200,100,100);
        b3.setBounds(200,200,100,100);
        b4.setBounds(0,300,100,100);
        b5.setBounds(100,300,100,100);
        b6.setBounds(200,300,100,100);
        b7.setBounds(0,400,100,100);
        b8.setBounds(100,400,100,100);
        b9.setBounds(200,400,100,100);
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.add(b6);
        frame.add(b7);
        frame.add(b8);
        frame.add(b9);
        dsMap.put(1,b1);
        dsMap.put(2,b2);
        dsMap.put(3,b3);
        dsMap.put(4,b4);
        dsMap.put(5,b5);
        dsMap.put(6,b6);
        dsMap.put(7,b7);
        dsMap.put(8,b8);
        dsMap.put(9,b9);
        b1.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b1,1);
            }
        });
        b2.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b2,2);
            }
        });
        b3.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b3,3);
            }
        });
        b4.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b4,4);
            }
        });
        b5.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b5,5);
            }
        });
        b6.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b6,6);
            }
        });
        b7.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b7,7);
            }
        });
        b8.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b8,8);
            }
        });
        b9.addMouseListener(new SubMouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x(b9,9);
            }
        });
    }

    public void x(JButton b,int i){
        log.error(i+"===="+ JSONUtil.toJsonStr(dsBoMap));
        if(dsBoMap.get(i)==null||dsBoMap.size()!=1||!dsBoMap.containsKey(i)){
            if(integral>0){
                integral--;
            }
        }else {
            b.setText("");
            exit = false;
            integral++;
            dsBoMap.remove(i);
        }
//        textArea.setText("一共打到"+integral+"个尹梦月,打错了会减少打到的数量哦");
        textArea.setText(printCicle());
        refresh();
    }
    public void rightTextArea(){
        rightTextArea.setBounds(320, 0, 320, 550);
        rightTextArea.setText(printCicle());
        frame.add(rightTextArea);
    }
    public void create(){
        frame = new JFrame();
        frame.setLayout(null);
        frame.setTitle("打地鼠");
        int x = 600;
        int y=300;
        int width = 620;
//        int width = 1420;
        int height = 550;
//        int height = 800;
        frame.setBounds(x, y, width, height);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//        9宫格
        ds();
        //文本框
        topTextArea();
        //时间框
        timeTextArea();
        //右侧狂
        rightTextArea();
//        //中间面板
//        centerPanel();
//        //添加按钮
//        addBtn();
//        //左
//        leftBtn();
//        //右
//        rightBtn();
//        //上
//        upBtn();
//        //下
//        downBtn();
//        //移除
//        removeBtn();
        //开始
//        threadBtn();

//        //新增字
//        addLable();
//        //新增按钮名字输入框
//        addText();
//        //移除字
//        removeLable();
//        //移除按钮名字输入框
//        removeText();
    }
}
