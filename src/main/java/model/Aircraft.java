package model;

import com.google.gson.*;

import java.time.ZonedDateTime;

public class Aircraft {

    /**
     * The Type Enum describes the type of aircraft.  Currently only cargo and passenger types are supported.
     */
    public enum Type {
        PASSENGER ("Passenger"),
        CARGO ("Cargo"),
        UNKNOWN ("Unknown");

        private final String value;
        Type (String value) {
            this.value = value;
        }
    }

    /**
     * The Size enum describes the size of the aircraft.  Currently only large and small sizes are supported.
     */
    public enum Size {
        LARGE ("Small"),
        SMALL ("Large"),
        UNKNOWN ("Unknown");

        private final String value;
        Size (String value) {
            this.value = value;
        }
    }

    private ZonedDateTime queueTime;        // Larger spot numbers queued later
    private Type type;
    private Size size;

    public Aircraft() {
        this.queueTime = ZonedDateTime.now();
        this.type = Type.UNKNOWN;
        this.size = Size.UNKNOWN;
    }

    // Builder pattern instead of setters. Use case is immutable-ish.
    public Aircraft type(Type type) {
        this.type = type;
        return this;
    }

    public Aircraft size(Size size) {
        this.size = size;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }

    public ZonedDateTime getQueueTime() {
        return queueTime;
    }

    public String toString() {
        return "The Aircraft queued at time " + queueTime +
                " is a " + size + " " + type + " aircraft.";
    }

    public String toJson() {
        try {
            Gson g = new Gson();
            String elem = g.toJson(this);
            System.out.println(elem);
            return elem;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
