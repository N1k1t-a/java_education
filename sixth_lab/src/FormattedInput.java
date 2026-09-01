
import java.util.Scanner;

public class FormattedInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static Object[] scanf(String format) {
        while (true) {
            System.out.print("Ввод: ");
            String line = scanner.nextLine();
            try {
                return sscanf(format, line);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте ещё раз.");
            }
        }
    }

    public static Object[] sscanf(String format, String in) {
        String[] spec = format.trim().split("\\s+");
        String[] parts = in.trim().split("\\s+");

        Object[] result = new Object[spec.length];

        for (int i = 0; i < spec.length; i++) {
            if (spec[i].equals("%d")) {
                result[i] = Integer.parseInt(parts[i]);
            } else if (spec[i].equals("%s")) {
                result[i] = parts[i];
            } else if (spec[i].equals("%f")) {
                result[i] = Double.parseDouble(parts[i]);
            } else if (spec[i].equals("%c")) {
                result[i] = parts[i].charAt(0);
            } else if (spec[i].equals("%b")) {
                result[i] = Integer.parseInt(parts[i], 2);
            } else if (spec[i].equals("%x")) {
                result[i] = Integer.parseInt(parts[i], 16);
            }
        }
        return result;
    }
}
