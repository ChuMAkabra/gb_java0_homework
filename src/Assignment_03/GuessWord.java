package Assignment_03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessWord {

    public static final int HINT_SIZE = 15; // размер подсказки (верные буквы и #)
/** ??? Приветствуется ли объявление массивов константами, если не планируем их менять? */
    public static final String[] WORDS = {"apple", "orange", "lemon", "banana", "apricot",
            "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi",
            "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple",
            "pumpkin", "potato"};

    public static void main(String[] args) {
        Random random = new Random();                           // объект случайных элементов
        String rightWord = WORDS[random.nextInt(WORDS.length)]; // выбираем случайное слово
        Scanner scanner = new Scanner(System.in);               // создаем объект сканнера
        boolean guessed = false;                                // флаг в позицию "не угадано"

        do {
            System.out.println("Введите слово и нажмите Enter:");
            String guess = scanner.next().toLowerCase();        // догадку в нижний регистр

            if (guess.equals(rightWord)) {                      // если слова совпадают
                guessed = true;
                System.out.println("Вы угадали! Правильный ответ: " + guess);
            }
            else {
                System.out.println("Вы пока не угадали. Угаданные буквы: ");
                System.out.println(compareWords(guess, rightWord));
            }
        } while (!guessed);
        scanner.close(); // закрываем сканнер.
/** ??? мы таким образом освобождаем память, уничтожая объект, или как это работает? */
    }

    private static String compareWords(String guess, String rightWord) {
        char[] hintArray = new char[HINT_SIZE];
        Arrays.fill(hintArray, '#') ;

        int rightSize = Math.min (rightWord.length(), guess.length());

        for (int i = 0; i < rightSize; i++) {
            if (guess.charAt(i) == rightWord.charAt(i)) hintArray[i] = guess.charAt(i);
        }
        return String.copyValueOf(hintArray);
    }
}
