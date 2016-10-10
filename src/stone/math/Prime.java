package stone.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.math.BigInteger;

/*
 * Copyright (C) 2014-2016  Stone Chen
 * All rights reserved by  Stone Chen
 *
 *  Modification History:
 *  Date        Author      Version     Description
 *  -----------------------------------------------
 *  2016年9月13日     Stone Chen	1.0       	[实现基本功能]
 *  2016年9月28日      Stone Chen 1.1         [添加判断大数是否为质数函数，调用BigInteger提供的方法]
 *
 */

/**
 * [功能说明]素数工具类，工具类具有私有构造器，不允许创建实例，提供各种静态方法
 */
public final class Prime {

    /** [初始化时生成的sPrimeArray长度] */
    private static final int sInitPrimeArrayLength = 1000;
    /** [sPrimeArray数组的长度] */
    private static int sPrimeArrayLength = 0;
    /** [从2开始的素数数组] */
    private static int[] sPrimeArray = {};
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
            int index = binarySearch(sPrimeArray, num, true);
            return Arrays.copyOf(sPrimeArray, index + 1);
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
     * [功能说明]用二分法在数组arr中查找key，找到返回索引，返回-1
     * 
     * @param arr
     *            待搜索的数组
     * @param key
     *            关键字
     * @return 索引值
     */
    public static int binarySearch(int[] arr, int key) {
        return binarySearch(arr, key, false);
    }

    /**
     * [功能说明]用二分法在数组arr中查找key，找到返回索引，否则根据isStrict标志返回
     * 
     * @param arr
     *            待搜索的数组
     * @param key
     *            关键字
     * @param isStrict
     *            是否要求在找不到key时返回小于key的最大值的索引
     * @return 索引值
     */
    public static int binarySearch(int[] arr, int key, boolean isStrict) {
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
        // 若数组中找不到，是否返回小于key的最大数的索引
        if (isStrict) {
            int result = start > end ? end : -1;
            return result;
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
     * [功能说明]采用Miller-Rabin算法判断一个大整数是否为素数，可靠性为1-1/(2^certainty)
     * 
     * @param num
     *            待判断的整数
     * @return 是素数返回true
     */
    public static boolean isProbablePrime(long num, int certainty) {
        String numStr = String.valueOf(num);
        BigInteger bigNum = new BigInteger(numStr);
        return bigNum.isProbablePrime(certainty);
    }
}
