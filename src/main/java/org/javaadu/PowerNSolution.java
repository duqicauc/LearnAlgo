package org.javaadu;

/**
 * @author javaadu
 * @date 2023/04/02
 **/
public class PowerNSolution {

    public static void main(String[] args) {
        System.out.println(pow(2, 10));
        System.out.println(pow(2.1, 3));
        System.out.println(pow(2, -2));
    }

    public static double pow(double x, int n) {
        return myPower(x, n);
    }

    private static double myPower(double x, long m) {
        // 当指数为0时，返回1
        if (m == 0) {
            return 1;
        }
        // 当指数为1时，返回底数
        else if (m == 1) {
            return x;
        }
        // 当指数为负数时，返回其倒数
        else if (m < 0) {
            return 1 / myPower(x, -m);
        }
        // 当指数为偶数时，用 pow(x, n/2) 的平方
        else if (m % 2 == 0) {
            double half = myPower(x, m / 2);
            return half * half;
        }
        // 当指数为奇数时，用 pow(x, n-1) 乘以底数
        else {
            return x * myPower(x, m - 1);
        }
    }
}

