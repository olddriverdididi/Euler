package stone.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

/*
 * Copyright (C) 2014-2016  Stone Chen
 * All rights reserved by  Stone Chen
 *
 *  Modification History:
 *  Date        Author      Version     Description
 *  -----------------------------------------------
 *  2016年9月13日     Stone Chen	1.0       	[实现基本功能]
 *  2016年9月28日      Stone Chen 1.1         [添加Miller-Rabin算法判断大数是否为素数函数isMrPrime()]
 *
 */

/**
 * [功能说明]素数类
 */
public final class Prime {

    /** [初始化时生成的sPrimeArray长度] */
    private static final int sInitPrimeArrayLength = 5;
    /** [sPrimeArray数组的长度] */
    private static int sPrimeArrayLength = 0;
    /** [从2开始的素数数组] */
    private static int[] sPrimeArray = { 1 };
    /** [小于等于此数的整数都已被验证是否为素数] */
    private static int sMaxCheckedPrime = 1;

    /**
     * 类初始化代码块
     */
    static {
        sPrimeArray = getPrimeArrayByLength(sInitPrimeArrayLength);
    }

    /**
     * 工具类的私有构造器
     */
    private Prime() {

    }

    /**
     * @return the primeArray
     */
    public static int[] getPrimeArray() {
        return sPrimeArray;
    }

    /**
     * @return the PrimeArrayLength
     */
    public static int getPrimeArrayLength() {
        return sPrimeArrayLength;
    }

    /**
     * @return the MaxCheckedPrime
     */
    public static int getMaxCheckedPrime() {
        return sMaxCheckedPrime;
    }

    /**
     * [功能说明]判断一个数是否为素数
     * 
     * @param num
     *            待判断的整数
     * @return num是素数返回true,否则返回false
     */
    public static boolean isPrime(int num) {
        boolean flag = true;
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * [功能说明]返回长度为length的素数数组，如果数组长度超过sPrimeArray，则会替换原来的数组,
     * 函数负责维护sPrimeArray、sMaxCheckedPrime、sPrimeArrayLength
     * 
     * @param length
     *            返回的数组长度
     * @return int素数数组
     */
    public static int[] getPrimeArrayByLength(int length) {
        // length<1则返回空数组
        if (length < 1) {
            return new int[] {};
        }
        // 如果length小于sPrimeArrayLength，则返回sPrimeArray的子集
        if (length <= sPrimeArrayLength) {
            return Arrays.copyOf(sPrimeArray, length);
        }
        // length大于sPrimeArrayLength情况的处理，生成一个长为length的数组，sPrimeArray直接填补在前面
        int[] primeArray = Arrays.copyOf(sPrimeArray, length);
        int count = sPrimeArrayLength;
        for (int num = (sPrimeArrayLength == 0 ? 2 : sPrimeArray[sPrimeArrayLength - 1]); count < length; num++) {
            if (isPrime(num)) {
                primeArray[count] = num;
                count++;
            }
        }
        // 维护三个关键变量更新
        sPrimeArray = primeArray;
        sPrimeArrayLength = length;
        sMaxCheckedPrime = sPrimeArray[sPrimeArray.length - 1];
        return primeArray;
    }

    /**
     * [功能说明]返回num以下的素数数组，如果数组长度超过sPrimeArray，则会替换原来的数组，
     * 函数负责维护sPrimeArray、sMaxCheckedPrime、sPrimeArrayLength
     * 
     * @param num
     *            请求的素数数组上限
     * @return 素数数组
     */
    public static int[] getPrimeArrayBelow(int num) {
        if (num < 2) {
            return new int[] {};
        }

        ArrayList<Integer> primeList = new ArrayList<Integer>();
        // 将sPrimeArray中小于等于num的素数加入list
        if (num <= sMaxCheckedPrime) {
            for (int prime : sPrimeArray) {
                if (prime <= num) {
                    primeList.add(prime);
                } else {
                    break;
                }
            }
        }
        // 将大于sMaxCheckedPrime小于等于num的素数加入list
        for (int n = sMaxCheckedPrime + 1; n <= num; n++) {
            if (isPrime(n)) {
                primeList.add(n);
            }
        }
        // 将list转换为int数组
        int[] primeArray = new int[primeList.size()];
        for (int i = 0; i < primeList.size(); i++) {
            primeArray[i] = primeList.get(i);
        }
        if (primeArray.length > sPrimeArrayLength) {
            sPrimeArray = primeArray;
            sPrimeArrayLength = primeArray.length;
            sMaxCheckedPrime = primeArray[sPrimeArrayLength - 1];
        }
        return primeArray;
    }

    /**
     * [功能说明]返回num在sPrimeArray的索引，如果不存在则返回-1,用二分法查找
     * 
     * @param num
     *            待查找的整数
     * @return index 索引
     */
    public static int getPrimeIndex(int num) {
        return binarySearch(sPrimeArray, num);
    }

    /**
     * [功能说明]用二分法在数组arr中查找key，找到返回索引，否则返回-1
     * 
     * @param arr
     *            待搜索的数组
     * @param key
     *            关键字
     * @return 索引值
     */
    public static int binarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (key < arr[middle]) {
                end = middle - 1;
            } else if (key > arr[middle]) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * [功能说明]检查sPrimeArrayLength、sMaxCheckedPrime是否对应sPrimeArray</br>
     * 具体判断sPrimeArray.length == sPrimeArrayLength?</br>
     * sMaxCheckedPrime == sPrimeArray[sPrimeArray.length-1]?</br>
     * 若都对应，返回true，否则修正并返回false
     * 
     * @return 是否对应
     */
    public static boolean check() {
        boolean flag = true;
        if (sPrimeArray.length != sPrimeArrayLength) {
            sPrimeArrayLength = sPrimeArray.length;
            flag = false;
        }
        if (sPrimeArray[sPrimeArray.length - 1] != sMaxCheckedPrime) {
            sMaxCheckedPrime = sPrimeArray[sPrimeArray.length - 1];
            flag = false;
        }
        return flag;
    }

    /**
     * [功能说明]利用sPrimeArray快速判断是否为素数
     * 
     * @param num
     *            待判断的整数
     * @return 是否为素数
     */
    public static boolean isQuickPrime(int num) {
        if (num > sMaxCheckedPrime) {
            boolean flag = true;
            int i = 0;
            for (; i < sPrimeArrayLength && sPrimeArray[i] <= Math.sqrt(num); i++) {
                if (num % sPrimeArray[i] == 0) {
                    flag = false;
                    break;
                }
            }
            if (i == sPrimeArrayLength) {
                for (int n = sMaxCheckedPrime; n <= Math.sqrt(num); n++) {
                    if (num % n == 0) {
                        flag = false;
                        break;
                    }
                }
            }
            return flag;
        }

        if (getPrimeIndex(num) == -1) {
            return false;
        }

        return true;
    }

    /**
     * [功能说明]采用Miller-Rabin算法判断一个大整数是否为素数 有较小概率返回错误结果
     * 
     * @param num
     *            待判断的整数
     * @return 是素数返回true
     */
    public static boolean isMrPrime(long num) {
        if (num <= 2) {
            if (num == 2) {
                return true;
            }
            return false;
        }

        if (num % 2 == 0) {
            return false;
        }
        long u = num - 1;
        while (u % 2 == 0) {
            u /= 2;
        }
        int s = 2;
        long[] aArray = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };
        for (int i = 0; i < s; i++) {
            long a = aArray[i];
            long x = ((long) Math.pow(a, u)) % num;
            while (u < num) {
                long y = x * x % num;
                if (y == 1 && x != 1 && x != num - 1) {
                    return false;
                }
                x = y;
                u = u * 2;
            }
            if (x != 1) {
                return false;
            }
        }
        return true;
    }
}
