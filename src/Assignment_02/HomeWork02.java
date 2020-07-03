package Assignment_02;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class HomeWork02 {
    private static final int TASK2SIZE = 8; // размер массива в Задании 2
    private static final int TASK2INC  = 3; // шаг увеличения значений массива в Задании 2
    private static final int TASK4SIZE = 9; // размер массива в Задании 4

    public static void main(String[] args) {
    // Задание 1
        System.out.println("Задание №1"); binarySwap();
    // Задание 2
        System.out.println("Задание №2"); plusThree();
    // Задание 3
        System.out.println("Задание №3"); multiplyByTwo();
    // Задание 4
        System.out.println("Задание №4"); arrayDiagonals();
    // Задание 5
        System.out.println("Задание №5"); minMax();
    // Задание 6
        int[] arr06_1 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] arr06_2 = {1, 1, 1, 2, 1};
        System.out.println("Задание №6");
        System.out.println(compareTwoParts  (arr06_1));
        System.out.println(compareTwoPartsV2(arr06_1));
        System.out.println(compareTwoParts  (arr06_2));
        System.out.println(compareTwoPartsV2(arr06_2));
    // Задание 7
        int[] arr07 = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Задание №7");
        System.out.println(Arrays.toString(shiftArrayElements(arr07, 1)));
        System.out.println(Arrays.toString(shiftArrayElements(arr07, 2)));
        System.out.println(Arrays.toString(shiftArrayElements(arr07, 3)));
        System.out.println(Arrays.toString(shiftArrayElements(arr07, -1)));
        System.out.println(Arrays.toString(shiftArrayElements(arr07, -2)));
        System.out.println(Arrays.toString(shiftArrayElements(arr07, -3)));
        System.out.println(Arrays.toString(shiftArrayElements(arr07, -10)));
    }

    // Задание 1
    public static void binarySwap() {
        int[] intArr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Массив до:    " + Arrays.toString(intArr));

        for (int i = 0; i < intArr.length; i++) {
            // если элемент массива = 0, заменить на 1, иначе - заменить на 0
            intArr[i] = (intArr[i] == 0) ? 1 : 0;
        }

        System.out.println("Массив после: " + Arrays.toString(intArr));
    }
    // Задание 2
    public static void plusThree() {

        int[] intArr = new int[TASK2SIZE];
        for (int i = 1; i < TASK2SIZE; i++) {
            intArr[i] = intArr[i-1] + TASK2INC;
        }
        System.out.println(Arrays.toString(intArr));
    }
    // Задание 3
    public static void multiplyByTwo() {
        int[] intArr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Массив до:    " + Arrays.toString(intArr));

        for (int i = 0; i < intArr.length; i++) {
            if (intArr[i] < 6) intArr[i] *= 2;
        }
        System.out.println("Массив после: " + Arrays.toString(intArr));
    }
    // Задание 4
    public static void arrayDiagonals() {
        int[][] intArr = new int [TASK4SIZE][TASK4SIZE];
        for (int i = 0; i < TASK4SIZE; i++) {
            intArr[i][i] = intArr[i][(TASK4SIZE - 1 - i)] = 1; // два присвоения в одной строчке
            System.out.println(Arrays.toString(intArr[i]));
        }
//        System.out.println(Arrays.deepToString(intArr));
    }
    // Задание 5
    public static void minMax() {
        // алгоритм для min и max очень схож, можно было вынести общие блоки в отдельную функцию,
        // но мне кажется, что здесь игра не стоит свеч
        int[] intArr = {5,3,1,4,10,8,6,7,-9};
        int indMin = 0;
        int indMax = 0;
        for (int i = 1; i < intArr.length; i++) {
            if (intArr[indMin] > intArr[i]) indMin = i;
            if (intArr[indMax] < intArr[i]) indMax = i;
        }

        System.out.printf("Минимальное  значение: [%d] = %d%n", indMin, intArr[indMin]);
        System.out.printf("Максимальное значение: [%d] = %d%n", indMax, intArr[indMax]);

        Arrays.sort(intArr);
        int min = intArr[0];
        int max = intArr[intArr.length - 1];
        System.out.printf("Максимальное значение: %d%n", min);
        System.out.printf("Максимальное значение: %d%n", max);
    }
    // Задание 6. Способ 1
    public static boolean compareTwoParts  (int @NotNull [] intArr) {
        // альтернативный короткий способ вычислить сумму элементов массива. Будем проходить это?
        int arrSumEx = Arrays.stream(intArr).sum();
        // стандартный способ вычислить сумму элементов массива
        int arrSum = 0;
        for (int i : intArr) {
            arrSum += i;
        }
        // проверим, что разницы в результатах нет
        System.out.printf("%d vs %d%n", arrSum, arrSumEx);

        int curSum = 0;
        for (int i : intArr) {
            curSum += i; // считаем текущую сумму элементов
            if (curSum == arrSum / 2.0) return true; // тек.сумма равна половине суммы всех элем.
            else if (curSum > arrSum / 2.0) break;   // тек.сумма перевалила за половину
        }
        return false;
    }
    // Задание 6. Способ 2
    public static boolean compareTwoPartsV2(int @NotNull [] intArr) {
        // кстати насколько оправдано делать так, чтобы не вычислять каждый раз в цикле?
        int arrLength = intArr.length;

        int iSum = 0;     // сумма элементов слева  от ||
        for (int i = 0; i < arrLength - 1; i++) { // не надо доходить до последнего элемента
            iSum += intArr[i];
            int jSum = 0; // сумма элементов справа от ||
            for (int j = i + 1; j < arrLength; j++) {
                jSum += intArr[j];
            }
            if (iSum == jSum) return true; // при равенстве сумм слева и справа от ||
            else if (iSum > jSum) break;
        }
        return false;
    }
    // Задание 7
    public static int @NotNull [] shiftArrayElements (int @NotNull [] arr, int shift) {
        // переписываем значения входящего массива в локальный, чтобы избежать переписи первого
        int[] intArr = arr.clone();

        int arrLength = intArr.length;
        // чтобы не усложнять задачу, ограничим величину сдвига (должна быть меньше длины массива)
        if (arrLength > Math.abs(shift)) {
            int shiftsDone = 0;   // счетчик выполненных сдвигов
            int p = 0;            // [p - previous] индекс сдвигаемого элемента (начинаем с первого)
            int pVal = 0;         // исходное значение сдвигаемого элемента

            do {
                if (p == 0)  {
                    // ВАЖНО! Если после нескольких шагов индекс снова = 0, значит мы зациклились.
                    // Это происходит, когда arrLength и shift оба (не)четные.
                    // Достаточно сдвинуть индекс на 1 и продолжить вычисления.
                    if (shiftsDone > 0) p++;
                    pVal = intArr[p]; // в любом случае получаем исходное значение элемента
                }
                // [n - next, new] определяем новый индекс сдвигаемого элемента
                int n = p + shift;
                // если новый индекс выходит за пределы массива, корректируем его:
                if (n < 0) n = arrLength + n;
                else if (n >= arrLength) n = n - arrLength;

                // сохраняем исходное значение элемента, на позицию которого совершаем сдвиг
                int nVal = intArr[n];
                // совершаем "сдвиг" (по факту перезаписываем значение элемента с индексом n)
                intArr[n] = pVal;
                // следующим будем сдвигать элемент n
                p = n;
                // сдвигаемым значением будет сохраненное прежде исходное значение элемента n
                pVal = nVal;

                shiftsDone++;
            } while (shiftsDone < arrLength);

        } else {
            System.out.println("Величина сдвига должна быть меньше длины массива!\n" +
                                "Будет возвращен исходный массив:");
        }
        return intArr;
    }
}