import java.util.List;

class MyThread implements Runnable{

    private List<Integer> list;

    private int startIndex;

    private int count;


    MyThread(List<Integer> list, int startIndex, int count) {
        this.list = list;
        this.startIndex = startIndex;
        this.count = count;
    }

    @Override
    public void run() {
        int partSum = 0;
        for (int i = startIndex; i < startIndex + count; i++) {
            partSum += list.get(i);
        }
        ParallelProcesses.addToSum(partSum);
    }
}