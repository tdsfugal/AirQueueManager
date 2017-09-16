package model;

import java.util.PriorityQueue;
import service.AirQueuePriorityComparator;

public class AirQueue {

    private AirQueuePriorityComparator comparator;
    private PriorityQueue<Aircraft> queue;

    public AirQueue(AirQueuePriorityComparator comparator) {
        this.comparator = comparator;
        queue = new PriorityQueue<Aircraft>(this.comparator);
    }

    public int waiting() {
        return queue.size();
    }

    public Aircraft enqueue(Aircraft a) {
        try {
            if (a != null) {
                queue.add(a);
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Aircraft dequeue() {
        try {
            if (waiting() > 0) {
                return queue.remove();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

