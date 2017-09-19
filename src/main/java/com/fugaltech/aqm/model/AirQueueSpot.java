package com.fugaltech.aqm.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.ZonedDateTime;

/**
 *
 * This wrapper class maintains the enqueue information needed to resolve queue scheduling for similar aircraft.
 *
 * EnqueueTime is the time that the aircaft was added to the queue.
 *
 * EnqueueIndex is a unique ascending integer that indicates the order of the addition to the queue. Integer provides
 * enough indexes for an airport use case.  At 60 aircraft/hr a positive integer could be assigned to each
 * aircraft for 4085 years without repetition.
 *
 */
public class AirQueueSpot {

    private Aircraft aircraft;

    private static int count = 0;
    private int enqueueIndex;
    private ZonedDateTime enqueueTime;

    public AirQueueSpot(Aircraft aircraft) {
        this.enqueueTime = ZonedDateTime.now();
        this.enqueueIndex = count++;
        this.aircraft = aircraft;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public int getEnqueueIndex() {
        return enqueueIndex;
    }

    public ZonedDateTime getEnqueueTime() {
        return enqueueTime;
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof AirQueueSpot) {
            AirQueueSpot thatA = (AirQueueSpot) that;
            return this.getAircraft().equals(thatA.getAircraft()) &&
                    this.getEnqueueIndex() == thatA.getEnqueueIndex() &&
                    this.getEnqueueTime().equals(thatA.getEnqueueTime());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String aircratDesc = (aircraft != null) ? aircraft.toString() : "<Error: Aircraft Missing>";
        return aircratDesc + " It was queued at " + enqueueTime + " with enqueue index " + enqueueIndex;
    }

    /**
     *
     * Method toJson serializes the information contained in this POJO to a Json serialized string.
     *
     * @return
     */
    public String toJson() {
        try {
            Gson g = new GsonBuilder().create();
            return g.toJson(this);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
