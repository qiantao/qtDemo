package com.qt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class FileToFile {
	public static void main(String[] args) {
		FileOutputStream out=null;
		BufferedReader bReader=null;
		FileReader reader =null;
		try{
			File origin = new File("D:/1.txt");
			File target = new File("D:/2.txt");
			if(!target.exists())
			{
				target.createNewFile();
			}
			reader = new FileReader(origin);//定义一个fileReader对象，用来初始化BufferedReader
	        bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
	        StringBuilder sb = new StringBuilder();
			out=new FileOutputStream(target);
	        String s = "";
	        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
	            if(s.contains("第308章")){
	                break;
                }
                sb.append(s);
            }
            s = s.split("第308章")[1];
            s = s.replaceAll("  \n", "");
            s = s.replaceAll("        ", "");
            out.write(s.getBytes());
        }catch(Exception e){
			e.printStackTrace();
			System.out.println("错误-------------------");
		}finally{
			try {
				out.close();
				bReader.close();
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
