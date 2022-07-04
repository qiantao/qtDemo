package com.qt;

public class PNPOLY {
	public static void main(String[] args) {
		float[] testpoint = new float[2];
//		相同的坐标点不在范围内
//		116.396867,39.909677
		testpoint[0]=(float) 39.913783;
		testpoint[1]=(float) 116.383303;
		float[][] array = new float[6][2];
		array[0][0]=(float) 39.913783;
		array[0][1]=(float) 116.383303;
		array[1][0]=(float) 39.906034;
		array[1][1]=(float) 116.381003;
		array[2][0]=(float) 39.906256;
		array[2][1]=(float) 116.390921;
		array[3][0]=(float) 39.909798;
		array[3][1]=(float) 116.410899;
		array[4][0]=(float) 39.909798;//39.914668;
		array[4][1]=(float) 116.390346;//116.410899;
		array[5][0]=(float) 39.920423;
		array[5][1]=(float) 116.388334;
		System.out.println(ifPnPonly(testpoint, array));
		
		
	}
	/**
	 * @param point 目标点经纬度
	 * @param vs  多边形经纬度  多边形的点必须相邻存放
	 * @return true 在多边形内  false 不在多边形内
	 */
	 public static Boolean ifPnPonly(float[] point, float[][] vs) {
		    
		    float x = point[0];
		    float y = point[1];
		    
		    boolean inside = false;
		    for (int i = 0, j = vs.length - 1; i < vs.length; j = i++) {
		    	System.out.println("i:"+i+"j:"+j);
		        float xi = vs[i][0];
		        float yi = vs[i][1];
		        float xj = vs[j][0];
		        float yj = vs[j][1];
		        
		        boolean intersect = ((yi >= y) != (yj >= y))
		            && (x <= (xj - xi) * (y - yi) / (yj - yi) + xi);
		        if (intersect) inside = !inside;
		    }
		    
		    return inside;
	 }
}
