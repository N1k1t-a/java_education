public class Main {
    public static void main(String[] args) {

        // Тест 1: конструктор создаёт единичную матрицу 3x3
        System.out.println("=== Тест 1: единичная матрица 3x3 ===");
        Matrix identity = new Matrix(3);
        System.out.println(identity);

        // Тест 2: сложение двух матриц
        System.out.println("=== Тест 2: сложение матриц ===");
        Matrix a = new Matrix(2);
        a.setElement(0, 0, 3);
        a.setElement(0, 1, 1);
        a.setElement(1, 0, 2);
        a.setElement(1, 1, 4);

        Matrix b = new Matrix(2);
        b.setElement(0, 0, 1);
        b.setElement(0, 1, 2);
        b.setElement(1, 0, 0);
        b.setElement(1, 1, 3);

        System.out.println("A:");
        System.out.println(a);
        System.out.println("B:");
        System.out.println(b);
        System.out.println("A + B:");
        System.out.println(a.sum(b));

        // Тест 3: getElement
        System.out.println("=== Тест 3: getElement ===");
        System.out.println("Элемент A[0][0] = " + a.getElement(0, 0));
        System.out.println("Элемент A[1][1] = " + a.getElement(1, 1));

    }

}