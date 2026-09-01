public class Main {
    public static void main(String[] args) {

        // Тест sscanf — парсим строку
        Object[] result = FormattedInput.sscanf("%d %s %c %f", "10 ten v 11.2");
        System.out.println("%d -> " + result[0]);
        System.out.println("%s -> " + result[1]);
        System.out.println("%c -> " + result[2]);
        System.out.println("%f -> " + result[3]);

        // Тест доп: %x (16-ричная) и %b (двоичная)
        Object[] result2 = FormattedInput.sscanf("%x %b", "1f 1010");
        System.out.println("\n%x -> " + result2[0]); // 31
        System.out.println("%b -> " + result2[1]);   // 10

        // Тест scanf — ввод с клавиатуры
        System.out.println("\nВведите: 10 ten v 11.2");
        Object[] fromKeyboard = FormattedInput.scanf("%d %s %c %f");
        System.out.println("%d -> " + fromKeyboard[0]);
        System.out.println("%s -> " + fromKeyboard[1]);
        System.out.println("%c -> " + fromKeyboard[2]);
        System.out.println("%f -> " + fromKeyboard[3]);

        // Тест scanf для %x и %b — ввод с клавиатуры
        System.out.println("\nВведите: 1f 1010 (hex и binary)");
        Object[] fromKeyboard2 = FormattedInput.scanf("%x %b");
        System.out.println("%x -> " + fromKeyboard2[0]);
        System.out.println("%b -> " + fromKeyboard2[1]);
    }
}
