import stone.math.Prime;

/*
 * Copyright (C) 2014-2016  福建星网视易信息系统有限公司
 * All rights reserved by  福建星网视易信息系统有限公司
 *
 *  Modification History:
 *  Date        Author      Version     Description
 *  -----------------------------------------------
 *  2016年9月13日     陈李俊     		1.0       	[修订说明]
 *
 */

/**
 * [功能说明]
 */
public class Main {

    /**
     * [功能说明]
     * 
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // 答题起始区

        int range = 100_0000;
        for (int n = 0;n<range;n++){
            boolean normalFlag = Prime.isPrime(n);
//            boolean quickFlag = Prime.isQuickPrime(n);
        }
        
        // 答题结束区
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;
        System.out.println("use " + useTime + " ms");

    }

    
}
