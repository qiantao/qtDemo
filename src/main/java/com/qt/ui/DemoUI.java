package com.qt.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/08/28 11:39
 * @version: V1.0
 */
public class DemoUI {
    public static void main(String[] args) {
        FrameMethod uiFrame= new FrameMethod();
        uiFrame.create();
        uiFrame.show();

        JFrame frame = new JFrame();
        frame.setBounds(0,0,200,200);
        Point p = new Point();
        p.setLocation(1,1);
        p.move(3,3);
        JPanel jp = new JPanel();
//        frame.add();
//        System.out.println(FrameMethod.printCicle());
    }

}
