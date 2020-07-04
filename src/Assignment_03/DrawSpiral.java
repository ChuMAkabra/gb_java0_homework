package Assignment_03;

import java.util.Scanner;

public class DrawSpiral {
    public static Scanner scanner = new Scanner(System.in);
    public static int mHeight; // высота матрицы
    public static int mWidth;  // ширина матрицы

    public static void main(String[] args) {
        System.out.println("Введите высоту матрицы:");
        mHeight = getDimension();
        System.out.println("Введите ширину матрицы:");
        mWidth  = getDimension();
        drawSpiral(mHeight, mWidth);
    }

    public static int getDimension() {
        do {
            String strDimension = scanner.next();
            // если введенная строка содержит только ненулевое число из 1-2 символов
            if (strDimension.matches("^\\d{1,2}$") && !strDimension.equals("0"))
                return Integer.parseInt(strDimension);
            else
                System.out.println("Введите целочисленное значение от 1 до 99");
        } while(true);
    }

    public static void drawSpiral(int height, int width) {
        int [][] matrix = new int [height][width];
        int curVal = 1;             // значение, записываемое в массив следующим
        int maxVal = height * width;// максимальное значение, записываемое в массив

        int i = -1; // счетчик для строки
        int j = -1; // счетчик для колонки

        do {
            /**
             * Перед первым витком каждого из 4 циклов сдвигаем i и j на 1, чтобы:
             * а) не выйти за пределы массива
             * б) не перезаписать снова последнюю ячейку предыдущего цикла
             * P.S. каждый цикл содержит одинаковое тело, но не стал выносить его в функцию
             */
            // Двигаемся вниз по колонке
            for (++j, ++i; i < height; i++) {
                if (matrix[i][j] != 0) break;
                else matrix[i][j] = curVal++;
            }
            // Двигаемся вправо по ряду
            for (++j, --i; j < width; j++) {
                if (matrix[i][j] != 0) break;
                else matrix[i][j] = curVal++;
            }
            // Двигаемся вверх по колонке
            for (--j, --i; i >= 0; i--) {
                if (matrix[i][j] != 0) break;
                else matrix[i][j] = curVal++;
            }
            // Двигаемся влево по ряду
            for (--j, ++i; j >= 0; j--) {
                if (matrix[i][j] != 0) break;
                else matrix[i][j] = curVal++;
            }
            // Уменьшаем высоту и ширину массива, чтобы не проходить снова по тем же элементам
            height--; width--;
        } while (height > 0 && width > 0);

        height = matrix.length;
        width = matrix[0].length;
        /**
         * Определяем формат вывода элементов матрицы. Число символов = (степень, в которую надо
         * возвести 10, чтобы получить максимальное число)+1 без дробной части
         */
        String format = "%0" + (int)(Math.log10(maxVal) + 1) + "d ";

        for (i = 0; i < height; i++) {
            for (j = 0; j < width; j++) {
                System.out.printf(format, matrix[i][j]);
            }
            System.out.println();
        }
    }
}
