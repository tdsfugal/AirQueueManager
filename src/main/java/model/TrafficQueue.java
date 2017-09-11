package model;

import java.util.List;
import java.util.ArrayList;

public class TrafficQueue {

    private List<Aircraft> queue;

    TrafficQueue() {
        queue = new ArrayList<Aircraft>();
    }

    public int waiting () {
        return queue.size();
    }

    public TrafficQueue enqueue(Aircraft a) {
        queue.add(a);
        return this;
    }
}

