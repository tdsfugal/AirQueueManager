package com.fugaltech.airqueuemanager.model;

import com.google.gson.*;

import java.util.UUID;

/**
 *
 *  The Aircraft class holds queue-independent information about a specific aircraft.
 *
 *  If a unique id is not provided with the aircraft then one is generated for it.
 *
 */
public class Aircraft {

    /**
     * The Type Enum describes the type of aircraft.  Currently only cargo and passenger types are supported.
     */
    public enum Type {
        PASSENGER ("Passenger"),
        CARGO ("Cargo"),
        UNKNOWN ("Unknown");

        private final String name;
        Type (String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    /**
     * The Size enum describes the size of the aircraft.  Currently only large and small sizes are supported.
     */
    public enum Size {
        LARGE ("Large"),
        SMALL ("Small"),
        UNKNOWN ("Unknown");

        private final String name;
        Size (String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    private String id;
    private Type type;
    private Size size;

    public Aircraft(String id) {
        this.id = id;
        this.type = Type.UNKNOWN;
        this.size = Size.UNKNOWN;
    }

    public Aircraft() {
        this.id = UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }

    @Override
    public boolean equals(Object that) {
        if (that instanceof Aircraft) {
            Aircraft thatA = (Aircraft) that;
            return this.getId() == thatA.getId() &&
                    this.getSize() == thatA.getSize() &&
                    this.getType() == thatA.getType();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Aircraft " + id + " is a " + size + ", " + type + " aircraft.";
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
