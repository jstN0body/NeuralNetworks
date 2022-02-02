package com.github.jstN0body.neuralNetworks;

public class MathUtil {
    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    /** @param x should be a value returned by the sigmoid function */
    public static double sigmoidDerivative(double x) {
        return x * (1 - x);
    }

    public static double[] dotProduct(double[] a, double[] b) {
        if (a.length != b.length) {
            System.out.println("Vectors have mismatched lengths.");
            return new double[0];
        }

        double[] c = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * b[i];
        }

        return c;
    }

    public static double summateArray(double[] a) {
        double b = 0;
        for (double c : a) {
            b += c;
        }

        return b;
    }
}
