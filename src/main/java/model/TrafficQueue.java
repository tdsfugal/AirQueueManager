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

    public void enqueue(Aircraft a) {
        if (a != null) queue.add(a);
    }

    public Aircraft dequeue() {
        return queue.remove();
    }
}

