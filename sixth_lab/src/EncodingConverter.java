import java.io.*;
import java.nio.charset.Charset;

public class EncodingConverter {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println(
                    "Использование: java EncodingConverter <вход> <выход> <кодировка_входа> <кодировка_выхода>");
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args[1];
        String sourceEncoding = args[2];
        String targetEncoding = args[3];

        validate(inputPath, sourceEncoding, targetEncoding);

        try {
            convert(inputPath, outputPath, sourceEncoding, targetEncoding);
            System.out.println("Готово: " + inputPath + " -> " + outputPath);
        } catch (IOException e) {
            System.err.println("Ошибка при конвертации: " + e.getMessage());
            System.exit(3);
        }
    }

    private static void validate(String inputPath, String sourceEncoding, String targetEncoding) {
        File file = new File(inputPath);
        if (!file.exists()) {
            System.err.println("Файл не найден: " + inputPath);
            System.exit(2);
        }
        if (!Charset.isSupported(sourceEncoding)) {
            System.err.println("Неизвестная кодировка: " + sourceEncoding);
            System.exit(2);
        }
        if (!Charset.isSupported(targetEncoding)) {
            System.err.println("Неизвестная кодировка: " + targetEncoding);
            System.exit(2);
        }
    }

    private static void convert(String inputPath, String outputPath,
            String sourceEncoding, String targetEncoding) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputPath), sourceEncoding));
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(outputPath), targetEncoding))) {

            char[] buffer = new char[8192];
            int length;
            while ((length = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, length);
            }
        }
    }
}
