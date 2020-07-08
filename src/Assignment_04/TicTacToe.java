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

package Assignment_04;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_AI = 'O';
    public static final String EMPTY = " ";
    public static final String FIRST_EMPTY_CHAR = "  ";

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

    public static void playGame() {
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

    private static void humanTurn() {
        int rowNumber, colNumber;

        do {
            System.out.println("Ход пользователя! Введите номера строки и столбца");
            System.out.print("Строка = ");
            rowNumber = scanner.nextInt();
            System.out.print("Столбец = ");
            colNumber = scanner.nextInt();

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
        // check horizontal
//        if (map[i][0] == symbol && map[i][1] == symbol && map[i][2] == symbol) return true;
        int dotsInLine = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                dotsInLine = (map[i][j] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == DOTS_TO_WIN) return true;

                int dotsToCheck = SIZE - (j + 1);
                if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
            }
        }

        // check vertical
//        if (map[0][i] == symbol && map[1][i] == symbol && map[2][i] == symbol) return true;
        dotsInLine = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                dotsInLine = (map[j][i] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == DOTS_TO_WIN) return true;

                int dotsToCheck = SIZE - (j + 1);
                if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
            }
        }

        // check diagonal 1
        int shift = SIZE - DOTS_TO_WIN;
        for (int s = -shift; s <= shift; s++) {
            for (int d = 0; d < SIZE; d++) {
                if (d + s < 0) continue;

                dotsInLine = (map[d][d + s] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == DOTS_TO_WIN) return true;

                int dotsToCheck = SIZE - (d + 1);
                if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
            }
        }

        // check diagonal 2
        for (int s = shift; s >= -shift; s--) {
            for (int d = SIZE - 1; d >= 0; d--) {
                if ((SIZE - 1) - d + s < 0) continue;

                dotsInLine = (map[d][(SIZE - 1) - d + s] == symbol) ? ++dotsInLine : 0;
                if (dotsInLine == DOTS_TO_WIN) return true;

                int dotsToCheck = d;
                if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
            }
        }


        // check diagonal 1
//        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
//        dotsInLine = 0;
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE-DOTS_TO_WIN; j++) {
//                for (int k = 0; k < SIZE; k++) {
//                    dotsInLine = (map[i][i + j] == symbol) ? ++dotsInLine : 0;
//                    if (dotsInLine == DOTS_TO_WIN) return true;
//
//                    int dotsToCheck = SIZE - (i + 1);
//                    if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
//                }
//            }
//        }

        // check diagonal 2
//        dotsInLine = 0;
////        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol) return true;
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = (SIZE-1) - i; j >= 0; j--) {
//                dotsInLine = (map[i][j] == symbol) ? ++dotsInLine : 0;
//                if (dotsInLine == DOTS_TO_WIN) return true;
//
//                int dotsToCheck = SIZE - (i + 1);
//                if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
//            }
//        }

        /**
         * new diagonals 1
         */
//        dotsInLine = 0;
//        for (int i = 0; i < SIZE; i++) {
//            dotsInLine = (map[i][i + 1] == symbol) ? ++dotsInLine : 0;
//            if (dotsInLine == DOTS_TO_WIN) return true;
//
//            int dotsToCheck = SIZE - (i + 1);
//            if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
//        }
//
//        dotsInLine = 0;
//        for (int i = 0; i < SIZE; i++) {
//            dotsInLine = (map[i + 1][i] == symbol) ? ++dotsInLine : 0;
//            if (dotsInLine == DOTS_TO_WIN) return true;
//
//            int dotsToCheck = SIZE - (i + 1);
//            if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
//        }

        /**
         * new diagonals 2
         */

//        dotsInLine = 0;
////        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol) return true;
//        for (int i = 0; i < SIZE - 1; i++) {
//            dotsInLine = (map[i][(SIZE - 1) - i - 1] == symbol) ? ++dotsInLine : 0;
//            if (dotsInLine == DOTS_TO_WIN) return true;
//
//            int dotsToCheck = SIZE - (i + 1);
//            if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
//        }
//
//        dotsInLine = 0;
////        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol) return true;
//        for (int i = 0; i < SIZE; i++) {
//            dotsInLine = (map[(SIZE - 1) - i][i] == symbol) ? ++dotsInLine : 0;
//            if (dotsInLine == DOTS_TO_WIN) return true;
//
//            int dotsToCheck = SIZE - (i + 1);
//            if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
//        }

        return false;
//        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol) return true;
//        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol) return true;
//        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol) return true;


//        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol) return true;
//        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol) return true;
//        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol) return true;

//        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol) return true;
//        if (map[0][2] == symbol && map[1][1] == symbol && map[2][0] == symbol) return true;
    }

//    private static int checkStep(char symbol, int shift) {
//        int dotsInLine = 0;
//        int start = Math.max(shift, 0);
//        int end = Math.min(SIZE, SIZE + shift);
//        if (end - start + 1 < DOTS_TO_WIN) return 0;
//
//        for (int i = start; i < end; i++) {
//            for (int j = i; j < SIZE; j++) {
//            dotsInLine = (map[i][j] == symbol) ? ++dotsInLine : 0;
//            if (dotsInLine == DOTS_TO_WIN) break;
//
//            int dotsToCheck = SIZE - (j + 1);
//            if (DOTS_TO_WIN > dotsInLine + dotsToCheck) break;
//            }
//        }
//        return dotsInLine;
//    }

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
