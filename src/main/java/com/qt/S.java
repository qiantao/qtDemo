package com.qt;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Author MuYang
 * @Date 2022/11/24 17:35
 * @version: V1.0
 */
public class S {
    public static void main(String[] args) throws Exception{
            int[] gold = {400, 500, 200, 300, 350};
            int[] people = {5, 5, 3, 4, 3};

//            long startTime_1 = System.nanoTime();
//            System.out.println(getMostGold_1(5, 10, gold, people));
//            long endTime_1 = System.nanoTime();
//            System.out.println("递归算法(用时)："+(endTime_1 - startTime_1)+"ns");

            long startTime_2 = System.nanoTime();
            System.out.println(getMostGold_2(5, 10, gold, people));
            long endTime_2 = System.nanoTime();
            System.out.println("动态规划(用时)："+(endTime_2 - startTime_2)+"ns");
        }

        public static int getMostGold_1(int n, int worker, int[] g, int[] p) {//递归算法
            if(n==1&&worker<p[0])
                return 0;
            if(n==1&&worker>=p[0])
                return g[0];
            if(n>1&&worker<p[n - 1])
                return getMostGold_1(n - 1, worker, g, p);
            return Math.max(getMostGold_1(n - 1, worker, g, p), (getMostGold_1(n - 1, worker - p[n - 1], g, p) + g[n - 1]));
        }

        public static int getMostGold_2(int n,int w,int []g,int[] p){//动态规划
            int[] F = new int[w + 1];
            for (int i=n;i>0;i--)
                for (int j = w; j > 0; j--)
                    if (j>=p[i - 1])
                        F[j] = Math.max(F[j], F[j - p[i - 1]] + g[i - 1]);

            for (int i = 0; i < F.length; i++) {
                System.out.print(F[i]+" ");
            }
            System.out.println("---");
            return F[w];
        }

}
