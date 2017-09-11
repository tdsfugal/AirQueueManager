package model;

public class Aircraft {
    public enum Type {
        PASSENGER ("Passenger"),
        CARGO ("Cargo"),
        UNKNOWN ("Unknown");

        private final String value;
        Type (String value) {
            this.value = value;
        }
    }

    public enum Size {
        LARGE ("Small"),
        SMALL ("Large"),
        UNKNOWN ("Unknown");

        private final String value;
        Size (String value) {
            this.value = value;
        }
    }

    // integer provides enough time indexes for an airport use case.  At 60 aircraft/hr int works for 4085 years.
    private static int count = 0;
    private int  aircraftAge;

    private Type aircraftType;
    private Size aircraftSize;

    public Aircraft() {
        this.aircraftAge  = count++;
        this.aircraftType = Type.UNKNOWN;
        this.aircraftSize = Size.UNKNOWN;
    }

    // Builder pattern instead of setters. Use case is immutable-ish.
    public Aircraft type(Type type) {
        this.aircraftType = type;
        return this;
    }

    public Aircraft size(Size size) {
        this.aircraftSize = size;
        return this;
    }

    public Type getAircraftType() {
        return aircraftType;
    }

    public Size getAircraftSize() {
        return aircraftSize;
    }

    public int getAircraftAge() {
        return aircraftAge;
    }

}
