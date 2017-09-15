package service;

import model.Aircraft;
import model.TrafficQueue;
import model.TrafficServiceRequestType;

public class TrafficService {

    private PriorityComparator comparator;
    private TrafficQueue queue;
    private Boolean started;

    public TrafficService() {
        this.comparator = new PriorityComparator();
        this.queue = new TrafficQueue(comparator);
        started = false;
    }

    private Aircraft start() {
        queue.clear();
        started = true;
        return null;
    }

    private Aircraft enqueue(Aircraft a) {
        if (started) try {
            queue.enqueue(a);
            return a;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    private Aircraft dequeue() {
        if (started) try {
            Aircraft ac = queue.dequeue();
            return ac;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Aircraft aqmRequestProcess(TrafficServiceRequestType request, Aircraft aircraft) {

        switch (request) {
            case START:
                return this.start();

            case ENQUEUE:
                return this.enqueue(aircraft);

            case DEQUEUE:
                return this.dequeue();

            default:
                return null;
        }

    }

    public Aircraft aqmRequestProcess(TrafficServiceRequestType request) {
        return aqmRequestProcess(request, null);
    }
}
