package step.bs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TopM {

    public static void main(String[] args) {
        int[] items = new int[]{1500, 2444, 666, 2, 3, 4, 5, 100, 555, 11, 22, 999, 5};

        PriorityQueue<Integer> topM = new PriorityQueue<>(6, Comparator.comparingInt(Integer::intValue));

        for (int i = 0; i < items.length; i++) {
            topM.add(items[i]);
            if (topM.size() > 5) topM.poll();

        }

        for (int i = 0; i < topM.size(); i++) {
            System.out.println(topM.poll());
        }
    }

}
