package Assignment_01;

import java.math.BigDecimal;

public class HomeWork01 {
    public static void main(String[] args) {
        System.out.println("Задание 1:  пустой проект создан, метод main() прописан");
        // 1) Примитивные типы данных. Целочисленные
        byte byteNum     = (byte) ((Math.pow(2,  8) / 2) - 1); // (2^8  / 2) - 1
        short shortNum   = (short)((Math.pow(2, 16) / 2) - 1); // (2^16 / 2) - 1
        int intNum       = (int)  ((Math.pow(2, 32) / 2) - 1); // (2^32 / 2) - 1
        long longNum     = (long) ((Math.pow(2, 64) / 2) - 1); // (2^64 / 2) - 1 *см. questionTwo()!
        // Логические
        boolean toBe     = true;
        boolean notToBe  = false;
        // Символьные
        char charSymb    = 169; // = '©'; инициализируем символ © по его UTF-8 коду
        // С плавающей запятой
        float floatPI    = (float) Math.PI; // обрежет число ПИ до 6  знака после запятой
        double doublePI  = (double)Math.PI; // обрежет число ПИ до 15 знака после запятой

        // 2) Ссылочные типы данных.
        BigDecimal bdPI  = new BigDecimal("3.1415926535897932384"); // больше знаков
        // Строковые
        String stringRef = "Ссылочная строковая переменная";
        // -----------------------------Вывод значений-----------------------------
        System.out.println("Задание 2:  создать переменные и инициализировать");
        System.out.printf("Максимальные значения:\nbyte  - %d\nshort - %d\nint   - %d\nlong  - %d",
                            byteNum, shortNum, intNum, longNum);
        System.out.printf("%n%nboolean-%b or %b%n", toBe, notToBe);
        System.out.printf("char  - %s%n", charSymb);
        System.out.printf("float - %f\ndouble- %f(%%f)\ndouble- %.15f(%%.15f)\ndouble- %" +
                          ".19f(%%.19f)\nbigDec- %.19f(%%.19f)%n",
                            floatPI, doublePI, doublePI, doublePI, bdPI);

        double numA = 10.5, numB = 5.0, numC = 10.0, numD = 2.0;
        System.out.printf("%nЗадание 3:  %.1f * (%.1f + (%.1f / %.1f)) = %.1f",
                numA, numB, numC, numD, algebra (numA, numB,numC, numD));
        System.out.printf("%nЗадание 4a: Сумма %.1f и %4.1f лежит в пределах [10, 20]? %b",
                numA, numB, checkSum(numA, numB));
        System.out.printf("%nЗадание 4b: Сумма %.1f и %.1f лежит в пределах [10, 20]? %b",
                numA, numC, checkSum(numA, numC));

        int numNeg = -10;
        System.out.print ("\nЗадание 5:  "); numSign(numNeg);
        System.out.printf("\nЗадание 6:  Число %d отрицательное? %b",numNeg,isNegative(-10));
        System.out.print ("\nЗадание 7:  ");
        sayHello("Тимофей");
        short year1 = 2000, year2 = 2020, year3 = 1900;
        System.out.print ("\nЗадание 8:  ");
        leapYear(year1); System.out.print(", ");
        leapYear(year2); System.out.print(", ");
        leapYear(year3);

        // -----------------------------ВОПРОСЫ-----------------------------
        // Вопрос по операторам переноса строки
        questionOne();
        // Вопрос по максимальному значению long переменной
        questionTwo(longNum);
    }
    public static double algebra (double a, double b, double c, double d) {
        return a * (b + (c / d));
    }
    public static boolean checkSum (double num1, double num2) {
        double sum = num1 + num2;
        return 20 >= sum && sum >= 10;
    }
    public static void numSign(int intNum) {
        String checkResult = "положительное";
        if (isNegative(intNum)) checkResult = "отрицательное";
        System.out.printf("Число %d %s", intNum, checkResult);
    }
    public static boolean isNegative(int numInt) {
        return (numInt < 0);
    }
    public static void sayHello(String name) {
        System.out.print("Привет, "+ name +"!");
    }
    public static void leapYear(short year) {
        var strResult = "не високосный";
//        if (year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0)) strResult = "високосный";
//        if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) strResult = "високосный";
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) strResult = "високосный";
        System.out.printf("%d год %s", year, strResult);
    }
    public static void questionOne () {
        System.out.printf("\n\nВопрос №1:\n" +
                "%%n и \\n - одно и то же, но в этой документации\n" +
                "https://docs.oracle.com/javase/tutorial/java/data/numberformat.html\n" +
                "указано \"You should always use %%n, rather than \\n.\"© " +
                "\nПочему, не подскажете? Что думаете на этот счет?\n");
    }
    public static void questionTwo (long longNum1) {
        long longNum2 = (long) ((Math.pow(2, 64) / 2) - 2);
        long longNum3 = (long) ((Math.pow(2, 64) / 2) - 512);
        long longNum4 = (long) ((Math.pow(2, 64) / 2) - 513);
        System.out.printf("\nВопрос №2:" +
                "\n-1  :%d\n-2  :%d\n-512:%d\n-513:%d\nlongNum3 - longNum4 = %d. Почему?",
                longNum1, longNum2, longNum3, longNum4, longNum3 - longNum4);
    }
}
