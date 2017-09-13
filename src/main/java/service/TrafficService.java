package service;

import model.Aircraft;
import model.TrafficQueue;

public class TrafficService {

    private PriorityComparator comparator;
    private TrafficQueue queue;
    private Boolean started;

    public TrafficService() {
        this.comparator = new PriorityComparator();
        this.queue = new TrafficQueue(comparator);
        started = false;
    }

    public void start() {
        queue.clear();
        started = true;
    }


    public void enqueue(Aircraft a) {
        if (started) queue.enqueue(a);
    }

    public Aircraft dequeue() {
        return started ? queue.dequeue() : null;
    }

}


