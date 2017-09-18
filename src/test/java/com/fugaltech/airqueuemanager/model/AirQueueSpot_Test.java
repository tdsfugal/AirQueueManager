package com.fugaltech.airqueuemanager.model;

import org.junit.Assert;
import org.junit.Test;

public class AirQueueSpot_Test {

    @Test
    public void ContainsAircraft() {
        Aircraft ac = new Aircraft();
        AirQueueSpot spot = new AirQueueSpot(ac);

        Assert.assertEquals(ac, spot.getAircraft());
    }

    @Test
    public void EnqueuOrder() {
        AirQueueSpot spot1 = new AirQueueSpot(new Aircraft());
        AirQueueSpot spot2 = new AirQueueSpot(new Aircraft());

        Assert.assertTrue(spot1.getEnqueueIndex() < spot2.getEnqueueIndex());
    }

}
