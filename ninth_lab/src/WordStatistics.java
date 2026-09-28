import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class WordStatistics {
    private static final HashMap<String, Integer> WORD_COUNTS = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[args.length];

        for (int fileIndex = 0; fileIndex < args.length; fileIndex++) {
            String fileName = args[fileIndex];

            threads[fileIndex] = new Thread(() -> {
                processFile(fileName);
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (String word : WORD_COUNTS.keySet()) {
            System.out.println(word + ": " + WORD_COUNTS.get(word));
        }
    }

    private static void processFile(String fileName) {
        try (Scanner scanner = new Scanner(Path.of(fileName))) {
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                addWord(word);
            }
        } catch (IOException exception) {
            System.out.println("Не удалось прочитать файл: " + fileName);
        }
    }

    private static void addWord(String word) {
        synchronized (WORD_COUNTS) {
            int currentCount = WORD_COUNTS.getOrDefault(word, 0);
            WORD_COUNTS.put(word, currentCount + 1);
        }
    }
}
