
public class Main {

    private static final Object FIRST_LOCK = new Object();
    private static final Object SECOND_LOCK = new Object();

    public static void main(String[] args) {
        Thread firstThread = new Thread(() -> {
            synchronized (FIRST_LOCK) {
                System.out.println("Первый поток");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("В первом потоке пытаемся попасть во второй замок");

                synchronized (SECOND_LOCK) {
                    System.out.println("первый поток во втором блоке");
                }
            }
        });

        Thread secondThread = new Thread(() -> {
            synchronized (SECOND_LOCK) {
                System.out.println("Второй поток");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Во втором потоке пытаемся залезть в первый");

                synchronized (FIRST_LOCK) {
                    System.out.println("Второй поток в первом блоке");
                }

            }
        });

        firstThread.start();
        secondThread.start();

    }

}