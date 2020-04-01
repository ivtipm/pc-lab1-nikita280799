import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelProcesses {

    private static int sum = 0;

    static synchronized void addToSum(int partSum) {
        sum += partSum;
    }

    public static void main(String[] args) {
        int listSize = 300000000;
        int countOfThreads = 350;
        List<Integer> list = new ArrayList<>();
        int min = -100000;
        int max = 100000;
        int diff = max - min;
        Random random = new Random();
        for (int i = 0; i < listSize; i++) {
            list.add(min + random.nextInt(diff + 1));
        }
        long startTime = System.currentTimeMillis();
        MyThread[] threads = new MyThread[countOfThreads];
        int curIndex = 0;
        int nextIndex;
        for (int i = 0; i < countOfThreads; i++) {
            nextIndex = curIndex + listSize / countOfThreads;
            nextIndex += i < listSize % countOfThreads ? 1 : 0;
            threads[i] = new MyThread(list, curIndex, nextIndex - curIndex);
            curIndex = nextIndex;
            threads[i].run();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Sum of array = " + sum + "\nFound for " + (endTime - startTime) + " milliseconds");

        /*
        startTime = System.currentTimeMillis();
        int checkSum = 0;
        for (Integer i: list) {
            checkSum += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("Checksum of array = " + checkSum + "\nFound for " + (endTime - startTime) + " milliseconds");
        */
    }
}