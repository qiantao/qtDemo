package com.qt.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/08/28 11:39
 * @version: V1.0
 */
public class UIFrame extends JFrame {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    ExecutorService executorService = Executors.newFixedThreadPool(20);
    HashMap<String, JButton> map= new HashMap<>();

    JTextArea textArea = new JTextArea();
    JTextArea rightTextArea = new JTextArea();
    JTextArea textAreaTime = new JTextArea();
    JFrame frame = new JFrame();
    JButton button = new JButton("添加按钮");
    JButton remvoeButton = new JButton("移除按钮");
    JButton left = new JButton("左移");
    JButton right = new JButton("右移");
    JButton up = new JButton("上移");
    JButton down = new JButton("下移");
    JButton btnThread = new JButton("开始");


    JLabel addName = new JLabel("新增按钮名称:");
    JLabel removeName = new JLabel("移除按钮名称:");
    JTextField btnNameText = new JTextField();
    JTextField removeBtnNameText = new JTextField();
    JPanel panel = new JPanel();
    public String getBtnName(){
        String str = "当前按钮数量:"+map.size()+"\n";
        for (String key : map.keySet()){
            str += key+"\n";
        }

        return str;
    }
    public void addBtn(){
        //添加
        button.setBounds(0, 660, 100, 100);
        frame.add(button);
        button.addMouseListener(new SubMouseListener() {
            public void mouseClicked(MouseEvent e) {
                addButton();
            }

        });
    }

    public void leftBtn(){
        left.setBounds(120, 660, 100, 100);
        frame.add(left);
        left.addMouseListener(new SubMouseListener() {
            public void mouseClicked(MouseEvent e) {
                left();
            }

        });
    }

    public void rightBtn(){
        right.setBounds(240, 660, 100, 100);
        frame.add(right);
        right.addMouseListener(new SubMouseListener() {
            public void mouseClicked(MouseEvent e) {
                right();
            }

        });
    }

    public void upBtn(){
        up.setBounds(360, 660, 100, 100);
        frame.add(up);
        up.addMouseListener(new SubMouseListener() {
            public void mouseClicked(MouseEvent e) {
                up();
            }

        });
    }

    public void downBtn(){
        down.setBounds(480, 660, 100, 100);
        frame.add(down);
        down.addMouseListener(new SubMouseListener() {
            public void mouseClicked(MouseEvent e) {
                down();
            }

        });
    }

    public void removeBtn(){
        remvoeButton.setBounds(600, 660, 100, 100);
        frame.add(remvoeButton);
        remvoeButton.addMouseListener(new SubMouseListener() {
            public void mouseClicked(MouseEvent e) {
                remvoeButton();
            }

        });
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

    public void addLable() {
        addName.setBounds(750, 660, 100, 50);
        frame.add(addName);
    }

    public void addText(){
        btnNameText.setBounds(860, 660, 200, 30);
        frame.add(btnNameText);
    }

    public void removeLable(){
        removeName.setBounds(750, 710, 100, 50);
        frame.add(removeName);
    }

    public void removeText(){
        removeBtnNameText.setBounds(860, 710, 200, 30);
        frame.add(removeBtnNameText);
    }

    public void topTextArea(){
        textArea.setBounds(0, 0, 300, 20);
        textArea.setText("888");
        frame.add(textArea);
    }

    public void timeTextArea(){
        textAreaTime.setBounds(0, 100, 300, 20);
        frame.add(textAreaTime);
    }

    public void centerPanel(){
//        panel.setBounds(0,200,1401,401);
//        panel.setBorder(new Border() {
//            @Override
//            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
////                画纵向
//                for (int i =0 ;i <= width/20 ; i++){
//                    g.drawLine(i*20,0,i*20,height+1);
//                }
//                //画横向
//                for(int i=0;i<=height/20;i++){
//                    g.drawLine(0,i*20,1400+1,i*20);
//                }
////                g.drawString("123",20,20);
//            }
//
//            @Override
//            public Insets getBorderInsets(Component c) {
//                return new Insets(c.getX(),c.getY(),c.getWidth(),c.getHeight());
//            }
//
//            @Override
//            public boolean isBorderOpaque() {
//                return false;
//            }
//        });
//        frame.add(panel);
    }

    public void left(){
        String btnName = btnNameText.getText();
        if(!map.containsKey(btnName)){
            log.error("找不到按钮 名称={}" ,btnName);
            return;
        }
        JButton tempBtn = map.get(btnName);
        Rectangle r = tempBtn.getBounds();
        tempBtn.setBounds(r.x-10,r.y,r.width,r.height);
        log.error("按钮: {} 左移 10  现在位置 x={},y={},width={},height={}",btnName,r.x-10,r.y,r.width,r.height);
    }

    public void right(){
        String btnName = btnNameText.getText();
        if(!map.containsKey(btnName)){
            log.error("找不到按钮 名称={}" ,btnName);
            return;
        }
        JButton tempBtn = map.get(btnName);
        Rectangle r = tempBtn.getBounds();
        tempBtn.setBounds(r.x+10,r.y,r.width,r.height);
        log.error("按钮: {} 右移 10  现在位置 x={},y={},width={},height={}",btnName,r.x+10,r.y,r.width,r.height);
    }

    public void up(){
        String btnName = btnNameText.getText();
        if(!map.containsKey(btnName)){
            log.error("找不到按钮 名称={}" ,btnName);
            return;
        }
        JButton tempBtn = map.get(btnName);
        Rectangle r = tempBtn.getBounds();
        tempBtn.setBounds(r.x,r.y-10,r.width,r.height);
        log.error("按钮: {} 上移 10  现在位置 x={},y={},width={},height={}",btnName,r.x,r.y-10,r.width,r.height);
    }

    public void down(){
        String btnName = btnNameText.getText();
        if(!map.containsKey(btnName)){
            log.error("找不到按钮 名称={}" ,btnName);
            return;
        }
        JButton tempBtn = map.get(btnName);
        Rectangle r = tempBtn.getBounds();
        tempBtn.setBounds(r.x,r.y+10,r.width,r.height);
        log.error("按钮: {} 下移 10  现在位置 x={},y={},width={},height={}",btnName,r.x,r.y+10,r.width,r.height);
    }

    public void addButton(){
        String btnName = btnNameText.getText();
        if(map.containsKey(btnName)){
            log.error("已存在按钮 添加失败");
            return;
        }
        if("".equals(btnName)){
            log.error("按钮名字为空");
            return;
        }
        JButton tempBtn = new JButton(btnName);
        tempBtn.setBounds(0, 260, 100, 100);

        tempBtn.addMouseListener(new SubMouseListener() {

            public void mouseClicked(MouseEvent e) {
                log.error("当前按钮为 {}",btnName);
            }
        });
        frame.add(tempBtn);
        map.put(btnName,tempBtn);
        textAreaText();
        refresh();
    }

    public void remvoeButton(){
        String btnName = removeBtnNameText.getText();
        if(!map.containsKey(btnName)){
            log.error("找不到按钮 名称={}" ,btnName);
            return;
        }
        frame.remove(map.get(btnName));
        map.remove(btnName);
        textAreaText();
        refresh();
    }



    public void refresh(){
        frame.validate();
        frame.repaint();
    }

    public void textAreaText(){
        textArea.setText(getBtnName());
    }

    public void textTimeText(){
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(new Date());
        textAreaTime.setText(str);
    }
    TimeThread t ;
    boolean flag = false;
    public void startT(){
        if(!flag) {
            flag = true;
            log.error("启动线程");
            btnThread.setText("停止");
            if(t==null) {
                t = new TimeThread();
//                t.run();
                executorService.execute(t);
            }
        }else{
            flag = false;
            log.error("停止线程");
            btnThread.setText("开始");
            refresh();
        }
    }

    public void show(){
        frame.setVisible(true);
//        startT();
    }

    class TimeThread extends Thread {
        @Override
        public void run() {

            while (true) {
                if(flag) {
                    textTimeText();
                    refresh();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void refreshThread(){
        try {
            SwingUtilities.invokeAndWait(()-> {
                int count = 0;
                    while (count<3) {
                        //更新的操作
                        frame.setTitle(System.currentTimeMillis() + "");
                        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count++;
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
