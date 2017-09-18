package com.fugaltech.airqueuemanager.model;

import org.junit.*;

public class Aircraft_Test {

    @Test
    public void Enum_Size() {
        Assert.assertEquals("Large", Aircraft.Size.LARGE.toString());
        Assert.assertEquals("Small", Aircraft.Size.SMALL.toString());
        Assert.assertEquals("Unknown", Aircraft.Size.UNKNOWN.toString());
    }

    @Test
    public void Enum_Type() {
        Assert.assertEquals("Passenger", Aircraft.Type.PASSENGER.toString());
        Assert.assertEquals("Cargo", Aircraft.Type.CARGO.toString());
        Assert.assertEquals("Unknown", Aircraft.Type.UNKNOWN.toString());
    }

    @Test
    public void Constructor() {
        Aircraft a = new Aircraft("foo");
        Assert.assertEquals(Aircraft.Type.UNKNOWN, a.getType());
        Assert.assertEquals(Aircraft.Size.UNKNOWN, a.getSize());
    }

    @Test
    public void Represents_Type() {
        Aircraft pass = new Aircraft("foo")
                .type(Aircraft.Type.PASSENGER);
        Assert.assertEquals(Aircraft.Type.PASSENGER, pass.getType());

        Aircraft carg = new Aircraft("foo")
                .type(Aircraft.Type.CARGO);
        Assert.assertEquals(Aircraft.Type.CARGO, carg.getType());
    }

    @Test
    public void Represents_Size() {
        Aircraft small = new Aircraft("foo")
                .size(Aircraft.Size.SMALL);
        Assert.assertEquals(Aircraft.Size.SMALL, small.getSize());

        Aircraft large = new Aircraft("foo")
                .size(Aircraft.Size.LARGE);
        Assert.assertEquals(Aircraft.Size.LARGE, large.getSize());
    }

    @Test
    public void toJson() {
        String expectedJson;
        Aircraft ac;

        ac = new Aircraft("AC1").size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        expectedJson = "{\"id\":\"AC1\",\"type\":\"PASSENGER\",\"size\":\"LARGE\"}";  // Somewhat brittle but
        Assert.assertEquals(expectedJson, ac.toJson());

        ac = new Aircraft("AC2").size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);
        expectedJson = "{\"id\":\"AC2\",\"type\":\"CARGO\",\"size\":\"LARGE\"}";      // gets the job done. Would
        Assert.assertEquals(expectedJson, ac.toJson());

        ac = new Aircraft("AC3").size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        expectedJson = "{\"id\":\"AC3\",\"type\":\"PASSENGER\",\"size\":\"SMALL\"}";  // write a better test for
        Assert.assertEquals(expectedJson, ac.toJson());

        ac = new Aircraft("AC4").size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        expectedJson = "{\"id\":\"AC4\",\"type\":\"CARGO\",\"size\":\"SMALL\"}";      // production code.
        Assert.assertEquals(expectedJson, ac.toJson());

    }

}
