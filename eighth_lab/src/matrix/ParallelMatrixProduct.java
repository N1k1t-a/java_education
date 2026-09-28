package matrix;

public class ParallelMatrixProduct {
    private int threadCount;

    public ParallelMatrixProduct(int threadCount) {
        this.threadCount = threadCount;
    }

    public UsualMatrix product(
            UsualMatrix first,
            UsualMatrix second) throws InterruptedException {
        UsualMatrix result = new UsualMatrix(
                first.getRows(),
                second.getColumns());

        Thread[] threads = new Thread[threadCount];

        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            int firstRowThread = threadIndex;

            threads[threadIndex] = new Thread(() -> {
                for (int row = firstRowThread; row < first.getRows(); row += threadCount) {
                    for (int column = 0; column < second.getColumns(); column++) {
                        int sum = 0;

                        for (int k = 0; k < first.getColumns(); k++) {
                            sum += first.getElement(row, k)
                                    * second.getElement(k, column);
                        }

                        result.setElement(row, column, sum);
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return result;
    }
}
