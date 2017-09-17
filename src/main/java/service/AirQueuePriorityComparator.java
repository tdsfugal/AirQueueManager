package service;

import model.Aircraft;

import java.util.Comparator;


public class AirQueuePriorityComparator implements Comparator<Aircraft> {

    public int compare(Aircraft a1, Aircraft a2) throws NullPointerException, ClassCastException {

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

        // At this point it is known that a1 and a2 are the same size and type

        // Third Check:  Aircraft that have been waiting longer (lower spot no.) have priority if they are the same size and type.
        return a1.getAircraftSpot() - a2.getAircraftSpot();
    }

}
