package com.fugaltech.airqueuemanager.model;

import com.fugaltech.airqueuemanager.service.AirQueuePriorityComparator;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.PriorityQueue;

/**
 *
 * An AirQueue instance mainains a sorted queue of aircraft.
 *
 * Sort order is defined by the AirQueuePriorityComparator.
 *
 */
@Named
public class AirQueue {

    private AirQueuePriorityComparator comparator;
    private PriorityQueue<AirQueueSpot> queue;

    @Inject
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

