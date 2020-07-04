package Assignment_03;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Совместил выполнение двух заданий - int и Biginteger в одном классе.
 * Для этого рассчитываю сумму периметров для 2-41 квадратов одним методов и 42-10000 другим.
 */

public class Fibonacci {
    public static final int MAX_INT_FIBO_SQUARES = 41;    // квадраты, сумма которых уместится в int
    public static final int MAX_BIG_FIBO_SQUARES = 10000; // квадраты, сумма которых уместится в Big
    public static int squareAmount;          // количество квадратов, заданное пользователем + 1
    public static final int SQUARE_SIDE = 4; // количество сторон квадрата для рассчета периметра
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        squareAmount = getSquareAmount() + 1;
        String format = "Площадь %d квадратов Фибоначчи = %d%n";
        System.out.printf(format,
                squareAmount, (squareAmount <= MAX_INT_FIBO_SQUARES)
                        ? calcFibonacciPerimeter(): calcFibonacciPerimeterBig());
    }

    public static int getSquareAmount() {
        do {
            System.out.println("Введите количество квадратов Фибоначчи (от 1 до " +
                    (MAX_BIG_FIBO_SQUARES - 1) + ")");
            String strSquares = scanner.next();
            // если введенная строка содержит только ненулевое число из 1-4 символов
            if (strSquares.matches("^\\d{1,4}$") && !strSquares.equals("0"))
                return Integer.parseInt(strSquares);
        } while(true);
    }

    private static int calcFibonacciPerimeter() {
        int[] arrFibo = new int[squareAmount];
        // первые 2 элемента равны = 1 (не стал вводить элемент = 0, ибо в нем нет смысла)
        arrFibo[0] = arrFibo[1] = 1;
        // сумма первых двух элементов
        int arrSum = arrFibo[0] + arrFibo[1];
        // рассчитаем значения каждого последующего элемета по формуле Фибоначчи и сохраним сумму
        for (int i = 2; i < squareAmount; i++) {
            arrFibo[i] = arrFibo[i-2] + arrFibo[i-1];
            arrSum += arrFibo[i];
        }
        // вернем сумму элементов, умноженную на количество сторон квадрата
        return arrSum * SQUARE_SIDE;
    }

    private static BigInteger calcFibonacciPerimeterBig() {
        BigInteger[] arrFibo = new BigInteger[squareAmount];
        // первые 2 элемента равны = 1 (не стал вводить элемент = 0, ибо в нем нет смысла)
        arrFibo[0] = arrFibo[1] = BigInteger.valueOf(1);
        // сумма первых двух элементов
        BigInteger arrSum = arrFibo[0].add(arrFibo[1]);

        // рассчитаем значения каждого последующего элемета по формуле Фибоначчи и сохраним сумму
        for (int i = 2; i < squareAmount; i++) {
            arrFibo[i] = arrFibo[i-2].add(arrFibo[i-1]);
            arrSum = arrSum.add(arrFibo[i]);
        }
        // вернем сумму элементов, умноженную на количество сторон квадрата
        return arrSum.multiply(BigInteger.valueOf(SQUARE_SIDE));
    }
}
