import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class MathAlgorithms {
  public static class ComplexNumber {
    public double re;
    public double im;
    public ComplexNumber(double re, double im) {
      this.re = re;
      this.im = im;
    }
    public ComplexNumber add(ComplexNumber other) {
      return new ComplexNumber(this.re + other.re, this.im + other.im);
    }
    public ComplexNumber subtract(ComplexNumber other) {
      return new ComplexNumber(this.re - other.re, this.im - other.im);
    }
    public ComplexNumber multiply(ComplexNumber other) {
      return new ComplexNumber(this.re * other.re - this.im * other.im, this.re * other.im + this.im * other.re);
    }
    public double radius() {
      return Math.sqrt(re * re + im * im);
    }
  }
  public static int getBit(int number, int bitPosition) {
    return (number >> bitPosition) & 1;
  }
  public static int setBit(int number, int bitPosition) {
    return number | (1 << bitPosition);
  }
  public static int clearBit(int number, int bitPosition) {
    return number & ~(1 << bitPosition);
  }
  public static int updateBit(int number, int bitPosition, int bitValue) {
    int normalizedValue = bitValue == 1 ? 1 : 0;
    int clearMask = ~(1 << bitPosition);
    return (number & clearMask) | (normalizedValue << bitPosition);
  }
  public static boolean isEven(int number) {
    return (number & 1) == 0;
  }
  public static boolean isPositive(int number) {
    if (number == 0) return false;
    return ((number >> 31) & 1) == 0;
  }
  public static int switchSign(int number) {
    return ~number + 1;
  }
  public static int multiplyByTwo(int number) {
    return number << 1;
  }
  public static int divideByTwo(int number) {
    return number >> 1;
  }
  public static int countSetBits(int originalNumber) {
    int setBitsCount = 0;
    int number = originalNumber;
    while (number != 0) {
      setBitsCount += number & 1;
      number >>>= 1;
    }
    return setBitsCount;
  }
  public static int bitsDiff(int numberA, int numberB) {
    return countSetBits(numberA ^ numberB);
  }
  public static int bitLength(int number) {
    int bitsCounter = 0;
    while ((1 << bitsCounter) <= number) {
      bitsCounter++;
    }
    return bitsCounter;
  }
  public static int fullAdder(int a, int b) {
    int result = 0;
    int carry = 0;
    for (int i = 0; i < 32; i++) {
      int ai = getBit(a, i);
      int bi = getBit(b, i);
      int aiPlusBi = ai ^ bi;
      int bitSum = aiPlusBi ^ carry;
      carry = (aiPlusBi & carry) | (ai & bi);
      result |= bitSum << i;
    }
    return result;
  }
  public static int bitwiseMultiplyUnsigned(int a, int b) {
    int result = 0;
    int multiplier = b;
    int bitIndex = 0;
    while (multiplier != 0) {
      if ((multiplier & 1) != 0) {
        result = fullAdder(result, a << bitIndex);
      }
      bitIndex++;
      multiplier >>>= 1;
    }
    return result;
  }
  public static int bitwiseMultiply(int a, int b) {
    if (b == 0 || a == 0) return 0;
    int multiplyResult = bitwiseMultiplyUnsigned(Math.abs(a), Math.abs(b));
    if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
      return switchSign(multiplyResult);
    }
    return multiplyResult;
  }
  public static boolean isPowerOfTwo(int number) {
    if (number <= 0) return false;
    return (number & (number - 1)) == 0;
  }
  public static String floatToBinary64(double number) {
    long bits = Double.doubleToLongBits(number);
    String binary = Long.toBinaryString(bits);
    while (binary.length() < 64) {
      binary = "0" + binary;
    }
    return binary;
  }
  public static long factorial(int number) {
    long result = 1;
    for (int i = 2; i <= number; i++) {
      result *= i;
    }
    return result;
  }
  public static List<Long> fibonacciSequence(int n) {
    List<Long> seq = new ArrayList<>();
    if (n <= 0) return seq;
    seq.add(1L);
    if (n == 1) return seq;
    seq.add(1L);
    for (int i = 2; i < n; i++) {
      seq.add(seq.get(i - 1) + seq.get(i - 2));
    }
    return seq;
  }
  public static long fibonacciNth(int n) {
    if (n <= 1) return n;
    long a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
      long c = a + b;
      a = b;
      b = c;
    }
    return b;
  }
  public static long fibonacciNthClosedForm(int position) {
    double sqrt5 = Math.sqrt(5);
    double phi = (1 + sqrt5) / 2;
    return Math.round(Math.pow(phi, position) / sqrt5);
  }
  public static List<Long> primeFactors(long n) {
    List<Long> factors = new ArrayList<>();
    while (n % 2 == 0) {
      factors.add(2L);
      n /= 2;
    }
    for (long i = 3; i * i <= n; i += 2) {
      while (n % i == 0) {
        factors.add(i);
        n /= i;
      }
    }
    if (n > 2) {
      factors.add(n);
    }
    return factors;
  }
  public static boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    for (int i = 5; i * i <= n; i += 6) {
      if (n % i == 0 || n % (i + 2) == 0) return false;
    }
    return true;
  }
  public static long GCD(long a, long b) {
    return b == 0 ? Math.abs(a) : GCD(b, a % b);
  }
  public static long LCM(long a, long b) {
    return (a == 0 || b == 0) ? 0 : Math.abs(a * b) / GCD(a, b);
  }
  public static List<Integer> sieveOfEratosthenes(int maxNumber) {
    boolean[] isPrime = new boolean[maxNumber + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    if (maxNumber >= 1) isPrime[1] = false;
    for (int p = 2; p * p <= maxNumber; p++) {
      if (isPrime[p]) {
        for (int i = p * p; i <= maxNumber; i += p) {
          isPrime[i] = false;
        }
      }
    }
    List<Integer> primes = new ArrayList<>();
    for (int i = 2; i <= maxNumber; i++) {
      if (isPrime[i]) primes.add(i);
    }
    return primes;
  }
  public static List<List<Integer>> pascalTriangle(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
          row.add(1);
        } else {
          row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
        }
      }
      triangle.add(row);
    }
    return triangle;
  }
  public static double radianToDegree(double radian) {
    return radian * (180.0 / Math.PI);
  }
  public static double degreeToRadian(double degree) {
    return degree * (Math.PI / 180.0);
  }
  public static long fastPower(long base, long power) {
    long result = 1;
    while (power > 0) {
      if ((power & 1) == 1) {
        result *= base;
      }
      base *= base;
      power >>= 1;
    }
    return result;
  }
  public static double hornerMethod(double[] coefficients, double x) {
    double result = coefficients[0];
    for (int i = 1; i < coefficients.length; i++) {
      result = result * x + coefficients[i];
    }
    return result;
  }
  public static double[][] matrixAdd(double[][] a, double[][] b) {
    int rows = a.length;
    int cols = a[0].length;
    double[][] c = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        c[i][j] = a[i][j] + b[i][j];
      }
    }
    return c;
  }
  public static double[][] matrixSubtract(double[][] a, double[][] b) {
    int rows = a.length;
    int cols = a[0].length;
    double[][] c = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        c[i][j] = a[i][j] - b[i][j];
      }
    }
    return c;
  }
  public static double[][] matrixMultiply(double[][] a, double[][] b) {
    int rowsA = a.length;
    int colsA = a[0].length;
    int colsB = b[0].length;
    double[][] c = new double[rowsA][colsB];
    for (int i = 0; i < rowsA; i++) {
      for (int j = 0; j < colsB; j++) {
        for (int k = 0; k < colsA; k++) {
          c[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return c;
  }
  public static double[][] matrixTranspose(double[][] a) {
    int rows = a.length;
    int cols = a[0].length;
    double[][] t = new double[cols][rows];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        t[j][i] = a[i][j];
      }
    }
    return t;
  }
  public static double euclideanDistance(double[] p, double[] q) {
    double sum = 0;
    for (int i = 0; i < p.length; i++) {
      double diff = p[i] - q[i];
      sum += diff * diff;
    }
    return Math.sqrt(sum);
  }
  public static int integerPartition(int number) {
    int[][] partitionMatrix = new int[number + 1][number + 1];
    for (int numberIndex = 1; numberIndex <= number; numberIndex++) {
      for (int summandIndex = 1; summandIndex <= number; summandIndex++) {
        if (numberIndex == summandIndex) {
          partitionMatrix[numberIndex][summandIndex] = 1 + partitionMatrix[numberIndex][summandIndex - 1];
        } else if (numberIndex < summandIndex) {
          partitionMatrix[numberIndex][summandIndex] = partitionMatrix[numberIndex][numberIndex];
        } else {
          partitionMatrix[numberIndex][summandIndex] = partitionMatrix[numberIndex - summandIndex][summandIndex] + partitionMatrix[numberIndex][summandIndex - 1];
        }
      }
    }
    return partitionMatrix[number][number];
  }
  public static double squareRootNewton(double number, double tolerance) {
    if (number < 0) throw new IllegalArgumentException();
    if (number == 0) return 0;
    double root = number;
    while (Math.abs(root * root - number) > tolerance) {
      root = 0.5 * (root + (number / root));
    }
    return root;
  }
  public static double liuHuiPi(int splitCount) {
    double n = 6;
    double sideLength = 1;
    for (int i = 0; i < splitCount; i++) {
      double h = Math.sqrt(1 - Math.pow(sideLength / 2, 2));
      double subSideLength = Math.sqrt(Math.pow(1 - h, 2) + Math.pow(sideLength / 2, 2));
      sideLength = subSideLength;
      n *= 2;
    }
    return (n * sideLength) / 2;
  }
  public static ComplexNumber[] DFT(ComplexNumber[] inputSignal) {
    int n = inputSignal.length;
    ComplexNumber[] output = new ComplexNumber[n];
    for (int k = 0; k < n; k++) {
      ComplexNumber sum = new ComplexNumber(0, 0);
      for (int t = 0; t < n; t++) {
        double angle = (2 * Math.PI * t * k) / n;
        ComplexNumber exp = new ComplexNumber(Math.cos(angle), -Math.sin(angle));
        sum = sum.add(inputSignal[t].multiply(exp));
      }
      output[k] = sum;
    }
    return output;
  }
}