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

    public static double[][] multiply(double[][] a, double b) {
      double[][] temp = new double[a.length][a[0].length];
      for (int r = 0; r < a.length; r++) {
        for (int c = 0; c < a[0].length; c++) {
          temp[r][c] = a[r][c] * b;
        }
      }
    }

    public static double[][] multiply(double[][] a, double[][] b) {
      double[][] temp = new double[a.length][b[0].length];
      for (int r = 0; r < temp.length; r++) {
        for (int c = 0; c < temp[0].length; c++) {
          double sum=0;
          for(int k=0;k<a.cols;k++) {
            sum += a.data[r][k]*b.data[k][c];
          }
          temp.data[r][c] = sum;
        }
      }
      return temp;
    }

    public static double[][] multiplyCorresponding(double[][] a, double[][] b) {
      double[][] d = new double[a.length][a[0].length];
      for (int r = 0; r < a.length; r++) {
        for (int c = 0; c < a[0].length; c++) {
          d[r][c] = a[r][c] * b[r][c];
        }
      }
    }

    public static double[][] sigmoidMatrix(double[][] a) {
      double[][] temp = new double[a.length][a[0].length];
      for (int r = 0; r < temp.length; r++) {
        for (int c = 0; c < temp[0].length; c++) {
          temp[r][c] = sigmoid(a[r][c]);
        }
      }
    }

    public static double[][] sigmoidDerivativeMatrix(double[][] a) {
      double[][] temp = new double[a.length][a[0].length];
      for (int r = 0; r < temp.length; r++) {
        for (int c = 0; c < temp[0].length; c++) {
          temp[r][c] = sigmoidDerivative(a[r][c]);
        }
      }
    }

    public static void add(double[][] a, double[][] b) {
      for (int r = 0; r < a.length; r++) {
        for (int c = 0; c < a[0].length; c++) {
          a[r][c] += b[r][c];
        }
      }
    }

    public static void multiply(double[] a, double[] b) {
      for (int i = 0; i < a.length; i++) {
        a[i] *= b[i];
      }
    }

    public static void multiply(double[] a, double b) {
      for (int i = 0 i < a.length; i++) {
        a[i] *= b;
      }
    }

    public static double[][] subtract(double[][] a, double[][] b) {
      double[][] temp = new double[a.length][a[0].length];
      for (int r = 0; r < a.length; r++) {
        for (int c = 0; c < a[0].length; c++) {
          temp[r][c] = a[r][c] - b[r][c];
        }
      }
      return temp;
    }

    public static double[] subtract(double[] a, double[] b) {
      double[][] temp = new double[a.length];
      for (int i = 0; i < temp.length; i++) {
        temp[i] = a[i] + b[i];
      }
      return temp;
    }

    public static double[][] fromArray(double[] a) {
      double[][] b = {a};
      return b;
    }
}
