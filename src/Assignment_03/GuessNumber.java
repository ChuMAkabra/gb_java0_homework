package Assignment_03;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
/**
 * ??? В каких случаях стоит объявлять переменные класса, а не метода метода?
 * Тогда, когда они используются в разных методах (->не придется передавать их в кач-ве аргументов)?
 * */
    public static final int MAX_TRY_COUNT = 3;
    public static final int MAX_NUMBER = 9;
    public static boolean guessed;
    public static int tryCount;
    public static int rightAnswer;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            guessed = false; // число еще не угадано
            tryCount = 0;    // попыток использовано: 0

            // Генерируем новое число от 0 до MAX_NUMBER включительно
            Random random = new Random();
            rightAnswer = random.nextInt(MAX_NUMBER + 1);

            // Отгадываем, пока не будет дан верный ответ или не закончатся попытки
            do {
                tryCount++; // инкремируем счетчик попыток
                guess();    // пробуем угадать
            } while (tryCount < MAX_TRY_COUNT && !guessed);

            // Повторим, если пользовател решит сыграть еще раз
        } while(oneMoreGame());
/** Решил обойтись без метода System.exit(0), чтобы не покидать программу внезапно */
        scanner.close();
    }
    // Узнаем у пользователя, хотел ли он повторить игру
    private static boolean oneMoreGame() {
        while (true) {
            System.out.println("Повторить игру еще раз? 1 - да / 0 - нет");
            switch (scanner.next()) {
                case "0":
                    return false;
                case "1":
                    return true;
                default:
                    System.out.println("Некорректное значение");
            }
        }
    }
    // Попытка угадать число
    public static void guess() {
        int userAnswer = -1; // целочисленный ответ по дефолту: -1
        // Запрашиваем число у пользователя
        do {
            System.out.println("Введите число от 0 до " + MAX_NUMBER);
            // Считываем данные с полей ввода
            String strAnswer = scanner.next(); // догадка игрока в виде текста
            // Если текст представляет собой число от 0 до 9, конвертировать ответ в число
/** Выбрал подход с регулярным выражением, ибо он лаконичный и простой для понимания */
            if (strAnswer.matches("^[0-9]$")) userAnswer = Integer.parseInt(strAnswer);
            else System.out.println("Некорректное значение");
/** Можно было бы сделать так, но мне не нравится громоздкость и неуклюжесть этой конструкции */
//            switch (strAnswer) {
//                case "0": case "1": case "2": case "3": case "4":
//                case "5": case "6": case "7": case "8": case "9":
//                    userAnswer = Integer.parseInt(strAnswer); break;
//                default:
//                    System.out.println("Некорректное значение. ");
//            }

        // Не начинаем игру, пока не введены корректные данные
        } while (userAnswer < 0 || userAnswer > MAX_NUMBER);

        // Сравниваем ответы
        if (userAnswer == rightAnswer) {
            System.out.println("Поздравляю, вы угадали!\nПравильный ответ: " + rightAnswer);
            guessed = true;
        } else if (tryCount == MAX_TRY_COUNT) {
            System.out.println("К сожалению, вы проиграли...\nПравильный ответ: " + rightAnswer);
        } else if (userAnswer > rightAnswer) {
            System.out.println("Вы ввели слишком большое число! " +
                    "Осталось попыток: " + (MAX_TRY_COUNT - tryCount));
        } else {
            System.out.println("Вы ввели слишком маленькое число! " +
                    "Осталось попыток: " + (MAX_TRY_COUNT - tryCount));
        }
    }
}