package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.ZonedDateTime;

/**
 *
 * This wrapper class maintains the enqueue information needed to resolve queue scheduling for similar aircraft.
 *
 */
public class AirQueueSpot {

    private Aircraft aircraft;

    // integer provides enough indexes for an airport use case.  At 60 aircraft/hr int works for 4085 years.
    private static int count = 0;
    private int enqueueOrder;

    public AirQueueSpot(Aircraft aircraft) {
        this.aircraft = aircraft;
        this.enqueueOrder = count++;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public int getEnqueueOrder() {
        return enqueueOrder;
    }

}
