import java.util.ArrayList;
import java.util.Random;

import arraylist.ParallelArrayListMin;
import matrix.ParallelMatrixProduct;
import matrix.UsualMatrix;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        testSmallMatrixProduct();
        System.out.println();
        compareMatrixProduct();
        System.out.println();
        compareMinimumSearch();
    }

    private static void testSmallMatrixProduct() throws InterruptedException {
        System.out.println("=== Тест 1: умножение матриц 3x2 и 2x2 ===");

        UsualMatrix first = new UsualMatrix(3, 2);
        first.setElement(0, 0, 1);
        first.setElement(0, 1, 2);
        first.setElement(1, 0, 3);
        first.setElement(1, 1, 4);
        first.setElement(2, 0, 5);
        first.setElement(2, 1, 6);

        UsualMatrix second = new UsualMatrix(2, 2);
        second.setElement(0, 0, 7);
        second.setElement(0, 1, 8);
        second.setElement(1, 0, 9);
        second.setElement(1, 1, 10);

        UsualMatrix expected = new UsualMatrix(3, 2);
        expected.setElement(0, 0, 25);
        expected.setElement(0, 1, 28);
        expected.setElement(1, 0, 57);
        expected.setElement(1, 1, 64);
        expected.setElement(2, 0, 89);
        expected.setElement(2, 1, 100);

        UsualMatrix usualResult = first.product(second);
        ParallelMatrixProduct parallelProduct = new ParallelMatrixProduct(2);
        UsualMatrix parallelResult = parallelProduct.product(first, second);

        System.out.println("Первая матрица:");
        printMatrix(first);
        System.out.println("Вторая матрица:");
        printMatrix(second);
        System.out.println("Результат обычного умножения:");
        printMatrix(usualResult);
        System.out.println("Результат многопоточного умножения (2 потока):");
        printMatrix(parallelResult);
        System.out.println(
                "Обычный результат совпадает с ожидаемым: "
                        + matricesEqual(usualResult, expected));
        System.out.println(
                "Обычный и многопоточный результаты равны: "
                        + matricesEqual(usualResult, parallelResult));
    }

    private static void compareMatrixProduct() throws InterruptedException {
        System.out.println("=== Тест 2: скорость умножения матриц 700x700 ===");

        int size = 700;
        int threadCount = Runtime.getRuntime().availableProcessors();

        UsualMatrix first = new UsualMatrix(size, size);
        UsualMatrix second = new UsualMatrix(size, size);

        Random random = new Random();

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                first.setElement(row, column, random.nextInt(10));
                second.setElement(row, column, random.nextInt(10));
            }
        }

        long usualStart = System.nanoTime();
        UsualMatrix usualResult = first.product(second);
        long usualTime = System.nanoTime() - usualStart;

        ParallelMatrixProduct parallelProduct =
                new ParallelMatrixProduct(threadCount);

        long parallelStart = System.nanoTime();
        UsualMatrix parallelResult = parallelProduct.product(first, second);
        long parallelTime = System.nanoTime() - parallelStart;

        System.out.println("Размер матриц: " + size + "x" + size);
        System.out.println("Количество потоков: " + threadCount);
        System.out.printf(
                "Обычное умножение: %.3f мс%n",
                usualTime / 1_000_000.0);
        System.out.printf(
                "Многопоточное умножение: %.3f мс%n",
                parallelTime / 1_000_000.0);
        System.out.println(
                "Контрольный элемент обычного результата: "
                        + usualResult.getElement(0, 0));
        System.out.println(
                "Контрольный элемент параллельного результата: "
                        + parallelResult.getElement(0, 0));
        System.out.println(
                "Результаты полностью совпадают: "
                        + matricesEqual(usualResult, parallelResult));
    }

    private static void compareMinimumSearch() throws InterruptedException {
        System.out.println("=== Тест 3: поиск минимума в ArrayList ===");

        int listSize = 100_000;
        ArrayList<Integer> numbers = new ArrayList<>(listSize);
        Random random = new Random();

        for (int index = 0; index < listSize; index++) {
            numbers.add(random.nextInt(1_000_000));
        }
        numbers.set(54_321, -1_000_000);

        long sequentialMinStart = System.nanoTime();
        int sequentialMin = ParallelArrayListMin.findSequential(numbers);
        long sequentialMinTime = System.nanoTime() - sequentialMinStart;

        int minThreadCount = Runtime.getRuntime().availableProcessors();
        long parallelMinStart = System.nanoTime();
        int parallelMin = ParallelArrayListMin.findParallel(
                numbers,
                minThreadCount);
        long parallelMinTime = System.nanoTime() - parallelMinStart;

        System.out.println("Размер ArrayList: " + listSize);
        System.out.println("Количество потоков: " + minThreadCount);
        System.out.printf(
                "Однопоточный поиск: %.3f мс%n",
                sequentialMinTime / 1_000_000.0);
        System.out.printf(
                "Многопоточный поиск: %.3f мс%n",
                parallelMinTime / 1_000_000.0);
        System.out.println("Минимум однопоточного поиска: " + sequentialMin);
        System.out.println("Минимум многопоточного поиска: " + parallelMin);
        System.out.println("Ожидаемый минимум: -1000000");
        System.out.println(
                "Результаты совпадают: "
                        + (sequentialMin == parallelMin
                                && sequentialMin == -1_000_000));
    }

    private static boolean matricesEqual(
            UsualMatrix first,
            UsualMatrix second) {
        if (first.getRows() != second.getRows()
                || first.getColumns() != second.getColumns()) {
            return false;
        }

        for (int row = 0; row < first.getRows(); row++) {
            for (int column = 0; column < first.getColumns(); column++) {
                if (first.getElement(row, column)
                        != second.getElement(row, column)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printMatrix(UsualMatrix matrix) {
        for (int row = 0; row < matrix.getRows(); row++) {
            for (int column = 0; column < matrix.getColumns(); column++) {
                System.out.print(matrix.getElement(row, column) + " ");
            }
            System.out.println();
        }
    }
}
