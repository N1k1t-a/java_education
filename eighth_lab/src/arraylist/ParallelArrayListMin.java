package arraylist;

import java.util.ArrayList;

public class ParallelArrayListMin {

    public static int findSequential(ArrayList<Integer> numbers) {
        int min = numbers.get(0);

        for (int index = 1; index < numbers.size(); index++) {
            if (numbers.get(index) < min) {
                min = numbers.get(index);
            }
        }

        return min;
    }

    public static int findParallel(
            ArrayList<Integer> numbers,
            int threadCount) throws InterruptedException {
        int[] localMins = new int[threadCount];
        Thread[] threads = new Thread[threadCount];
        int partSize = (numbers.size() + threadCount - 1) / threadCount;

        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            int currentThread = threadIndex;
            int startIndex = threadIndex * partSize;
            int endIndex = Math.min(startIndex + partSize, numbers.size());

            threads[threadIndex] = new Thread(() -> {
                int min = Integer.MAX_VALUE;

                for (int index = startIndex; index < endIndex; index++) {
                    if (numbers.get(index) < min) {
                        min = numbers.get(index);
                    }
                }

                localMins[currentThread] = min;
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int min = localMins[0];
        for (int index = 1; index < localMins.length; index++) {
            if (localMins[index] < min) {
                min = localMins[index];
            }
        }

        return min;
    }
}
