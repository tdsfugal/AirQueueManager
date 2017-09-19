package com.fugaltech.aqm.service;

import com.fugaltech.aqm.model.AirQueueSpot;
import com.fugaltech.aqm.model.Aircraft;

import javax.inject.Named;
import java.util.Comparator;

@Named
public class AirQueuePriorityComparator implements Comparator<AirQueueSpot> {

    @Override
    public int compare(AirQueueSpot spot1, AirQueueSpot spot2) {
        if ( spot1 == null ) throw new NullPointerException();
        if ( spot2 == null ) throw new NullPointerException();

        // Compare aircraft first
        Aircraft a1 = spot1.getAircraft();
        Aircraft a2 = spot2.getAircraft();

        if ( a1 == null ) throw new NullPointerException();
        if ( a2 == null ) throw new NullPointerException();

        // First Check:  Passenger aircraft have priority over cargo aircraft.  (Assume type Unknown takes lowest priority)
        Aircraft.Type t1 = a1.getType();
        Aircraft.Type t2 = a2.getType();

        if ( t1 == Aircraft.Type.PASSENGER && t2 == Aircraft.Type.CARGO ) return -1;
        if ( t1 == Aircraft.Type.CARGO && t2 == Aircraft.Type.PASSENGER ) return +1;

        if ( t1 == Aircraft.Type.UNKNOWN  ) return +1;  // Arbitrary, but hey, they're unknown...
        if ( t2 == Aircraft.Type.UNKNOWN )  return -1;

        // At this point it is known that a1 and a2 are the same type

        // Second Check:  Larger aircraft have priority over smaller aircraft of the same type.  (Assume type Unknown takes lowest priority)
        Aircraft.Size s1 = a1.getSize();
        Aircraft.Size s2 = a2.getSize();

        if ( s1 == Aircraft.Size.LARGE && s2 == Aircraft.Size.SMALL ) return -1;
        if ( s1 == Aircraft.Size.SMALL && s2 == Aircraft.Size.LARGE ) return +1;

        if ( s1 == Aircraft.Size.UNKNOWN  ) return +1;
        if ( s2 == Aircraft.Size.UNKNOWN )  return -1;

        // Third check. The aircraft are similar so compare on the enqueue times.
        return spot1.getEnqueueIndex() - spot2.getEnqueueIndex();
    }

}
