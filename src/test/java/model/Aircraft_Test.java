package model;

import org.junit.*;

public class Aircraft_Test {

    @Test
    public void Aircraft_Default() {
        Aircraft a = new Aircraft();
        Assert.assertEquals(Aircraft.Type.UNKNOWN, a.getType());
        Assert.assertEquals(Aircraft.Size.UNKNOWN, a.getSize());
    }

    @Test
    public void Aircraft_Spot() {
        Aircraft a1 = new Aircraft();
        Aircraft a2 = new Aircraft();
        Assert.assertTrue(a1.getAircraftSpot() < a2.getAircraftSpot());
    }

    @Test
    public void Aircraft_Type() {
        Aircraft pass = new Aircraft()
                .type(Aircraft.Type.PASSENGER);
        Assert.assertEquals(Aircraft.Type.PASSENGER, pass.getType());

        Aircraft carg = new Aircraft()
                .type(Aircraft.Type.CARGO);
        Assert.assertEquals(Aircraft.Type.CARGO, carg.getType());
    }

    @Test
    public void Aircraft_Size() {
        Aircraft small = new Aircraft()
                .size(Aircraft.Size.SMALL);
        Assert.assertEquals(Aircraft.Size.SMALL, small.getSize());

        Aircraft large = new Aircraft()
                .size(Aircraft.Size.LARGE);
        Assert.assertEquals(Aircraft.Size.LARGE, large.getSize());
    }

    @Test
    public void Aircraft_Equality_Type() {
        Aircraft pass = new Aircraft().type(Aircraft.Type.PASSENGER);
        Aircraft carg = new Aircraft().type(Aircraft.Type.CARGO);
        Aircraft def  = new Aircraft();

        Assert.assertEquals(Aircraft.Type.CARGO, carg.getType());
    }

    @Test
    public void Aircraft_Equality_Size() {
        Aircraft lg  = new Aircraft().size(Aircraft.Size.LARGE);
        Aircraft sm  = new Aircraft().size(Aircraft.Size.SMALL);
        Aircraft def  = new Aircraft();

        Assert.assertEquals(Aircraft.Size.LARGE, lg.getSize());
    }

}
