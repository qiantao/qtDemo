package com.qt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StringToFile {
	public static void main(String[] args) {
		FileOutputStream out=null;
		BufferedReader bReader=null;
		FileReader reader =null;
		try{
//			File f = new File("E:/3.txt");
			File target = new File("E:/4.txt");
			if(!target.exists())
			{
				target.createNewFile();
			}
//			reader = new FileReader(f);//定义一个fileReader对象，用来初始化BufferedReader
//	        bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
	        StringBuilder sb = new StringBuilder();
			out=new FileOutputStream(target);
	        String s = getSql1();
	        out.write(s.getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				out.close();
//				bReader.close();
//				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		Scanner input = new Scanner(System.in);
//		int i =  input.nextInt();
//		System.out.println("first:"+i++);
//		System.out.println(i);
//		addI(i);
//		System.out.println("sencond:"+i++);
//		System.out.println("sencond:"+i++);
//		String str = "a";
//		changeString(str);
//		System.out.println("a:"+str);
	}
	
	public static String getSql(){
//		List<String> strList = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<100;i++){
			if(i==86) continue;
			String fix = "111";
			if(i<10){
				fix = "0"+i;
			}else{
				fix = ""+i;
			}
			String str  = "select * from ("
			+"\n select mer_code,card_number,count(*) m from member_card_merchant_relation_"+fix
			+"\n where `status`= 1 and create_time > '2018-07-31 00:00:00' GROUP BY mer_code,card_number ) t"+fix
			+"\n where t"+fix+".m > 1 \n";
			
			buf.append(str);
			if(i!=99){
				buf.append(" UNION \n");
			}
		}
		return buf.toString();
		
	}
	public static String getSql1(){
//		List<String> strList = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		buf.append("select * from (");
		for(int i=0;i<100;i++){
			if(i==86) continue;
			String fix = "111";
			if(i<10){
				fix = "0"+i;
			}else{
				fix = ""+i;
			}
			String str  = "select mer_code,count(*) n from member_card_merchant_relation_"+fix+" where create_time >='2018-01-01 00:00:00' and `status` = 1 GROUP BY mer_code \n";
			
			buf.append(str);
			if(i!=99){
				buf.append(" UNION \n");
			}
		}
		buf.append(") orderinfo by n desc");
		return buf.toString();
		
	}
	public static void addI(int i){
		i = i +1;
	}
	public static void changeString(String str){
		str = "abc";
		System.out.println("abc:"+str);
	}
}
