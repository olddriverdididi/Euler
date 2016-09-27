/*
 * Copyright (C) 2014-2016  福建星网视易信息系统有限公司
 * All rights reserved by  福建星网视易信息系统有限公司
 *
 *  Modification History:
 *  Date        Author      Version     Description
 *  -----------------------------------------------
 *  2016年9月18日     陈李俊     		1.0       	[修订说明]
 *
 */
package stone.math;

import java.util.ArrayList;

/**
 * [功能说明]排列组合类
 */
public class Permutation {
    /**
     * [功能说明]求str中选取i个字符的排列组合。
     * 
     * @param str
     * @param i
     * @return
     */
    public static ArrayList<String> permutation(String str, int i) {
        ArrayList<String> list = new ArrayList<String>();
        permutation(str.toCharArray(), i, list);
        return list;

    }

    public static void permutation(char[] ss, int i, ArrayList<String> list) {
        if (ss == null || i < 0 || i > ss.length) {
            return;
        }
        if (i == ss.length) {
            list.add(new String(ss));
        } else {
            for (int j = i; j < ss.length; j++) {
                char temp = ss[j];// 交换前缀,使之产生下一个前缀
                ss[j] = ss[i];
                ss[i] = temp;
                permutation(ss, i + 1, list);
                temp = ss[j]; // 将前缀换回来,继续做上一个的前缀排列.
                ss[j] = ss[i];
                ss[i] = temp;
            }
        }
    }

    public static int[] circle(int[] numArray) {
        int temp = numArray[numArray.length - 1];
        for (int i = numArray.length - 1; i > 0; i--) {
            numArray[i] = numArray[i - 1];
        }
        numArray[0] = temp;
        return numArray;
    }

    public static String[] insert(String str, char sperater, int num) {
        String[] result = new String[3];
        int[] index = new int[str.length()];
        return result;
    }
    
    /**
     * [功能说明]返回从str中选b个字符的字符串组
     * @param str
     * @param b
     * @return
     */
    public static char[] permutationC(String str, int b) {
         char[] result = new char[combinationAB(str.length(), b)];
         
         return result;
    }
    
    public static int combinationAB(int a,int b) {
        int result = 0;
        for (;a>0;a--){
            result *= a;
        }
        for (;b>0;b--){
            result /= b;
        }
        return result;
    }
}
