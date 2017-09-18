package model;

import java.util.PriorityQueue;

import org.springframework.stereotype.Service;
import service.AirQueuePriorityComparator;

/**
 *
 * An AirQueue instance mainains a sorted queue of aircraft.
 *
 * Sort order is defined by the AirQueuePriorityComparator.
 *
 */
@Service
public class AirQueue {

    private AirQueuePriorityComparator comparator;
    private PriorityQueue<AirQueueSpot> queue;

    public AirQueue(AirQueuePriorityComparator comparator) {
        this.comparator = comparator;
        queue = new PriorityQueue<AirQueueSpot>(this.comparator);
    }

    /**
     *
     * Method waiting returns the number of aircraft waiting in the queue.
     *
     * @return
     */
    public int waiting() {
        return queue.size();
    }

    /**
     *
     * Method enqueue inserts an aircraft in the sorted queue.  The aircraft's position in the queue is implicitly
     * defined by the policy embodied in the AirQueuePriorityComparator.
     *
     * @param a
     * @return
     */
    public Aircraft enqueue(Aircraft a) {
        try {
            if (a != null) {
                queue.add( new AirQueueSpot(a) );
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * Method dequeue pops the highest priority aircraft off the sorted queue.
     *
     * @return
     */
    public Aircraft dequeue() {
        try {
            if (waiting() > 0) {
                return queue.remove().getAircraft();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

