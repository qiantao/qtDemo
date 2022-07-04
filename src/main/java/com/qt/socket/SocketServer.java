package com.qt.socket;


import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

public class SocketServer {
    public static String _pattern = "yyyy-MM-dd HH:mm:ss SSS";
    public static SimpleDateFormat format = new SimpleDateFormat(_pattern);

    // 设置超时间
    public static void main(String[] args) {
        try {
            // 启动监听端口 8001
            ServerSocket ss = new ServerSocket(8998);
            // 接收请求
            Socket s = ss.accept();
            new Thread(new SocThread(s)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

class SocThread implements Runnable {
    public void run() {
        try {
            System.out.println(socket.toString());
            socket.setKeepAlive(true);
            socket.setSoTimeout(5 * 1000);
            String _pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(_pattern);
            StringBuffer buffer = new StringBuffer();
            Writer writer = null;
            while (true) {
//                System.out.println("开始读取消息：" + format.format(new Date()));
                try {
                    InputStream ips = socket.getInputStream();
                    byte[] b = new byte[1024];
                    // 往服务写数据
                    OutputStream os = socket.getOutputStream();
                    writer = new OutputStreamWriter(os);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//                    String s = null;
//                    while ((s = bufferedReader.readLine()) != null) {
//                        System.out.println(s);
//                        buffer.append(s);
//                    }
                    int data = -1;
//                    StringBuffer buf = new StringBuffer();
                    while ((data = ips.read(b)) != -1) {
                        System.out.println(new String (b,0,data));
                        System.out.println(new String (b,0,data));
//                        buf.append(new String (b,0,data));
                        String msg = "revice message:" + new String(b, 0, data);
//                        writer.write(msg.getBytes(StandardCharsets.UTF_8));
                        os.write(msg.getBytes());
                        os.flush();
//                        writer.flush();

                    }
                } catch (SocketTimeoutException e) {
//                    if(buffer.length()>0){
//                        writer.write("revice message:"+buffer.toString());
//                        writer.flush();
//                        buffer = new StringBuffer();
//                    }
                    System.out.println("未读取到数据");
                } catch (SocketException e) {
                    System.out.println("未读取到数据");
                } catch (Exception e) {
                    System.out.println("未读取到数据");
                }
                Thread.sleep(1000);
//                System.out.println(socket.isBound()); // 是否邦定
//                System.out.println(socket.isClosed()); // 是否关闭
//                System.out.println(socket.isConnected()); // 是否连接
//                System.out.println(socket.isInputShutdown()); // 是否关闭输入流
//                System.out.println(socket.isOutputShutdown()); // 是否关闭输出流
//                System.out.println("读取消息结束：" + format.format(new Date()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Socket socket = null;

    public SocThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
