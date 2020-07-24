/**
 * Порядок выполнения:
 * 1. main() -> initMap() - инициализация массива
 * -------------------------------------------
 * 2. main() -> printMap() - печать массива
 * 3. printMap() -> printMapHeader() - печать впервой строки вывода
 * 4. printMap() -> printMapRows() - печать остальной части массива
 * -------------------------------------------
 * 5. main() -> playGame() - запуск игры. 2 части: человек и машина
 * * -------------------------------------------
 * 6. playGame() -> humanTurn() - ввод числа в терминал
 * 7. humanTurn -> isCellValid - проверка на валидность введенного числа
 * 8. playGame() -> printMap (см п.3-4)
 * * -------------------------------------------
 * 9. playGame() -> checkEnd() - проверка на завершение
 * 10. checkEnd -> checkWin() - проверка на победу
 * 11. checkEnd -> isMapFull() - проверка на ничью
 * * -------------------------------------------
 * 12. п.6-11, но с aiTurn, вместо humanTurn
 * * -------------------------------------------
 * 13. повторение п.6-12
 */

package Assignment_08;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static final int SIZE = 7;
    public static int dotsToWin;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_AI = 'O';
    public static final String EMPTY = " ";
    public static final String FIRST_EMPTY_CHAR = "  ";
    public static int diagonalShift; // модуль максимального сдвига

    public static char[][] map = new char[SIZE][SIZE];
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {

        turnGame();

    }

    public static void turnGame() {
        initMap();

        printMap();

        playGame();
    }

    public static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        printMapHeader();

        printMapRows();
    }

    public static void printMapHeader() {
        System.out.print(FIRST_EMPTY_CHAR);
        for (int i = 0; i < SIZE; i++) {
            printNumber(i);
        }
        System.out.println();
    }

    public static void printNumber(int i) {
        System.out.print(i + 1 + EMPTY);
    }

    public static void printMapRows() {
        for (int i = 0; i < SIZE; i++) {
            printNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void playGame() {

        if (SIZE >= 3) {
            defineDotsToWin();
            defineDiagonalShift();
        } else {
            System.out.println("Задайте размер поля не меньше 3");
            System.exit(0);
        }

        while (true) {
            humanTurn();
            printMap();
            if (checkEnd(DOT_HUMAN, "Вы выиграли!")) {
                System.exit(0);
            }

            aiTurn();
            printMap();
            if (checkEnd(DOT_AI, "К сожалению, Вы проиграли...")) {
                System.exit(0);
            }

        }
    }

    public static void defineDotsToWin() {
        if      (SIZE <=  5) dotsToWin = 3;
        else if (SIZE <= 10) dotsToWin = 4;
        else if (SIZE >  10) dotsToWin = 5;
    }

    public static void defineDiagonalShift() {
        // достаточно проверять только диагонали, вмещающие победное кол-во полей. Число таких
        // диагоналей как слева, так и справа от основной диагонали определим как "сдвиг"
        diagonalShift = SIZE - dotsToWin;
    }

    private static void humanTurn() {
        int rowNumber = -1, colNumber = -1;

        do {
            try {
                System.out.println("Ход пользователя! Введите номера строки и столбца");
                System.out.print("Строка = ");
                rowNumber = scanner.nextInt();
                System.out.print("Столбец = ");
                colNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Пожалуйста введите числовые значения");
                scanner.next();
            }
        } while (!isCellValid(rowNumber, colNumber, DOT_HUMAN));

        map[rowNumber - 1][colNumber - 1] = DOT_HUMAN;
    }

    private static boolean isCellValid(int rowNumber, int colNumber, char symbol) {

        boolean isHuman = symbol == DOT_HUMAN;

        if ((rowNumber < 1 || rowNumber > SIZE) || (colNumber < 1 || colNumber > SIZE)) {
            if (isHuman)
            System.out.println("\nПроверьте значения строки и столбца!");
            return false;
        }

        if (map[rowNumber - 1][colNumber - 1] != DOT_EMPTY) {
            if (isHuman)
            System.out.println("\nВы выбрали занятую ячейку");
            return false;
        }

        return true;
    }

    private static void aiTurn() {
        int rowNumber, colNumber;

        do {
            rowNumber = random.nextInt(SIZE) + 1;
            colNumber = random.nextInt(SIZE) + 1;

        } while (!isCellValid(rowNumber, colNumber, DOT_AI));

        map[rowNumber - 1][colNumber - 1] = DOT_AI;
    }

    private static boolean checkEnd(char symbol, String winMessage) {
        


        if (checkWin(symbol)) {
            System.out.println(winMessage);
            return true;
        }

        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }

    private static boolean checkWin(char symbol) {
        if (checkHorizontal(symbol)) return true;
        if (checkVertical(symbol)) return true;
        if (checkDescendingDiagonal(symbol)) return true;
        return checkAscendingDiagonal(symbol);
    }

    private static boolean checkHorizontal(char symbol) {
        int dotsInLine = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                dotsInLine = (map[i][j] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == dotsToWin) return true;

                int dotsToCheck = SIZE - (j + 1);
                if (dotsToWin > dotsInLine + dotsToCheck) break;
            }
        }
        return false;
    }

    private static boolean checkVertical(char symbol) {
        int dotsInLine = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                dotsInLine = (map[j][i] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == dotsToWin) return true;

                int dotsToCheck = SIZE - (j + 1);
                if (dotsToWin > dotsInLine + dotsToCheck) break;
            }
        }
        return false;
    }

    private static boolean checkDescendingDiagonal(char symbol) {
        int dotsInLine = 0;
        for (int s = -diagonalShift; s <= diagonalShift; s++) {
            for (int d = 0; d < SIZE; d++) {
                if (d + s < 0) continue;

                dotsInLine = (map[d][d + s] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == dotsToWin) return true;

                int dotsToCheck = SIZE - (d + 1);
                if (dotsToWin > dotsInLine + dotsToCheck) break;
            }
        }
        return false;
    }

    private static boolean checkAscendingDiagonal(char symbol) {
        int dotsInLine = 0;
        for (int s = diagonalShift; s >= -diagonalShift; s--) {
            for (int d = SIZE - 1; d >= 0; d--) {
                if ((SIZE - 1) - d + s < 0) continue;

                dotsInLine = (map[d][(SIZE - 1) - d + s] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == dotsToWin) return true;

                int dotsToCheck = d;
                if (dotsToWin > dotsInLine + dotsToCheck) break;
            }
        }
        return false;
    }

    private static boolean isMapFull() {
        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY)
                    return false;
            }
        }
        return Boolean.TRUE;
    }
}
