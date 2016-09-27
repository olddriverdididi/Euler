package main;

import java.util.Arrays;
import java.util.Vector;

import stone.math.Prime;

/**
 * @author "陈李俊"
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        solve();
        long endTime = System.currentTimeMillis();
        long useTime = endTime - startTime;
        System.out.println("\nfunction solve use " + useTime + " ms");
    }

    /**
     * 答题函数，把解题代码写在这里
     */
    public static void solve() {
        System.out.println(Prime.isMrPrime(35L));
    }

    public static int getLength(int num) {
        int length = 0;
        num = Math.abs(num);
        if (num == 0) {
            return 1;
        }
        while (num > 0) {
            length++;
            num /= 10;
        }
        return length;
    }

    public static int[] transToArray(String str) {
        int[] numArray = new int[str.length()];
        char[] charArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            numArray[i] = Integer.parseInt("" + charArray[i]);
        }
        Arrays.sort(numArray);
        return numArray;
    }

    public static int f(int n, int m) {
        n = n % m;
        Vector v = new Vector();

        for (;;) {
            v.add(n);
            n *= 10;
            n = n % m;
            if (n == 0)
                return 0;
            if (v.indexOf(n) >= 0)
                return v.size() - v.indexOf(n);
        }
    }

}
