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

    private Type aircraftType;
    private Size aircraftSize;

    public Aircraft() {
        this.aircraftType = Type.UNKNOWN;
        this.aircraftSize = Size.UNKNOWN;
    }

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

}
