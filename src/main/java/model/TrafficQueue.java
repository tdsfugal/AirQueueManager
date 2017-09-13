package model;

import java.util.PriorityQueue;
import service.PriorityComparator;

public class TrafficQueue {

    private PriorityComparator comparator;
    private PriorityQueue<Aircraft> queue;

    public TrafficQueue(PriorityComparator comparator) {
        this.comparator = comparator;
        queue = new PriorityQueue<Aircraft>(this.comparator);
    }

    public int waiting() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
    }

    public TrafficQueue enqueue(Aircraft a) {
        queue.add(a);
        return this;
    }

    public Aircraft dequeue() {
        return queue.remove();
    }
}

