package service;

import model.AirQueueSpot;
import model.Aircraft;
import org.junit.*;

public class AirQueuePriorityComparator_Test {

    // Lesser Aircraft dequeue earlier than greater Aircraft

    @Test
    public void Compare_Aircraft_Defaults() {  // Hopefully unnecessary, but req. for math completeness
        AirQueueSpot def_pass = new AirQueueSpot( new Aircraft()
                .type(Aircraft.Type.PASSENGER));
        AirQueueSpot def_carg = new AirQueueSpot( new Aircraft()
                .type(Aircraft.Type.CARGO));
        AirQueueSpot def_sm   = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.SMALL));
        AirQueueSpot def_lg   = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.LARGE));
        AirQueueSpot sm_pass  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER));
        AirQueueSpot sm_carg  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO));

        AirQueuePriorityComparator pc = new AirQueuePriorityComparator();

        Assert.assertTrue(0 > pc.compare( sm_carg  , def_carg ));
        Assert.assertTrue(0 > pc.compare( sm_carg  , def_lg   ));

        Assert.assertTrue(0 < pc.compare( def_pass , sm_pass  ));
        Assert.assertTrue(0 < pc.compare( def_sm   , sm_carg  ));
    }

    @Test
    public void Compare_Aircraft_Type_Policy() {  // Hopefully unnecessary, but req. for math completeness
        AirQueueSpot sm_pass  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot sm_carg  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO) );
        AirQueueSpot lg_pass  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot lg_carg  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO) );

        AirQueuePriorityComparator pc = new AirQueuePriorityComparator();

        Assert.assertTrue( 0 > pc.compare( lg_pass  , lg_carg  ));
        Assert.assertTrue( 0 > pc.compare( sm_pass  , sm_carg  ));

        Assert.assertTrue( 0 < pc.compare( lg_carg  , lg_pass  ));
        Assert.assertTrue( 0 < pc.compare( sm_carg  , sm_pass  ));
    }

    @Test
    public void Compare_Aircraft_Size_Policy() {  // Hopefully unnecessary, but req. for math completeness
        AirQueueSpot sm_pass  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot sm_carg  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO) );
        AirQueueSpot lg_pass  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot lg_carg  = new AirQueueSpot( new Aircraft()
                .size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO) );

        AirQueuePriorityComparator pc = new AirQueuePriorityComparator();

        Assert.assertTrue(0 > pc.compare( lg_pass  , sm_pass  ));
        Assert.assertTrue(0 > pc.compare( lg_carg  , sm_carg  ));

        Assert.assertTrue(0 < pc.compare( sm_pass  , lg_pass  ));
        Assert.assertTrue(0 < pc.compare( sm_carg  , lg_carg  ));
    }

    @Test
    public void Compare_Overall_Policy_With_Age() {
        AirQueueSpot sm_pass1  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot sm_carg1  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO) );
        AirQueueSpot lg_pass1  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot lg_carg1  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO) );
        AirQueueSpot sm_pass2  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot sm_carg2  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO) );
        AirQueueSpot lg_pass2  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER) );
        AirQueueSpot lg_carg2  = new AirQueueSpot(new Aircraft()
                        .size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO) );

        AirQueuePriorityComparator pc = new AirQueuePriorityComparator();

        Assert.assertTrue(0 > pc.compare( sm_pass1, sm_pass2 ));
        Assert.assertTrue(0 > pc.compare( sm_carg1, sm_carg2 ));
        Assert.assertTrue(0 > pc.compare( lg_pass1, lg_pass2 ));
        Assert.assertTrue(0 > pc.compare( lg_carg1, lg_carg2 ));
        Assert.assertTrue(0 < pc.compare( sm_pass2, sm_pass1 ));
        Assert.assertTrue(0 < pc.compare( sm_carg2, sm_carg1 ));
        Assert.assertTrue(0 < pc.compare( lg_pass2, lg_pass1 ));
        Assert.assertTrue(0 < pc.compare( lg_carg2, lg_carg1 ));

    }

}
