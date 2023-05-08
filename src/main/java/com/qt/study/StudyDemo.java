package com.qt.study;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author MuYang
 * @Date 2023/2/26 20:37
 * @version: V1.0
 */
public class StudyDemo {

    final String str = "123";

    public static void main(String[] args) {

        int m =36;
        List<Integer> M = new ArrayList<>();

        List<Integer> am = a(m, M);
        for (int i = 0; i < am.size(); i++) {
            System.out.print(am.get(i)+" ");
        }
        System.out.println("");

        int n =45;
        List<Integer> N = new ArrayList<>();
        List<Integer> an = a(n, N);

        List<Integer> total = new ArrayList<>();
        //am 2 2 3 3  233
        //an 5 3 3  53
        for (int i = 0; i < am.size(); i++) {
            Integer integer = am.get(i);
            if(an.contains(integer)){
                total.add(integer);

                int i1 = an.indexOf(integer);
                an.remove(i1);
            }else{
                total.add(integer);
            }
        }

        //最终的结果
        total.addAll(an);
        for (int i = 0; i < total.size(); i++) {
            System.out.print(total.get(i)+" ");
        }
    }


    /**
     *
     * @param m 需要取约数的数
     * @param M 最终返回的约数数组
     * @return 约数数组
     */
    public static List<Integer> a(int m ,List<Integer> M){

        if(m<2){
            System.out.println("约数是1");
            M.add(1);
            return M;
        }else {
            for (int i = 2; i < m; i++) {
                if (m % i == 0) {
                    //将约数i存储
                    M.add(i);
                    //获得商 =9
                    int m1 = m / i;
                    return a(m1, M);
                }
            }
            M.add(m);
        }

        return M;
    }
}
