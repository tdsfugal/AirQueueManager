package service;

import model.Aircraft;
import org.junit.*;

public class PriorityComparator_Test {

    @Test
    public void Compare_Equality() {  // Hopefully unnecessary, but req. for math completeness
        Aircraft sm_pass  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft sm_carg  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft lg_pass  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);

        PriorityComparator pc = new PriorityComparator();

        Assert.assertTrue(0 == pc.compare( sm_pass  , sm_pass  ));
        Assert.assertTrue(0 == pc.compare( sm_carg  , sm_carg  ));
        Assert.assertTrue(0 == pc.compare( lg_pass  , lg_pass  ));
        Assert.assertTrue(0 == pc.compare( lg_carg  , lg_carg  ));
    }


    @Test
    public void Compare_Defaults() {  // Hopefully unnecessary, but req. for math completeness
        Aircraft def_pass = new Aircraft().type(Aircraft.Type.PASSENGER);
        Aircraft def_carg = new Aircraft().type(Aircraft.Type.CARGO);
        Aircraft def_sm   = new Aircraft().size(Aircraft.Size.SMALL);
        Aircraft def_lg   = new Aircraft().size(Aircraft.Size.LARGE);
        Aircraft sm_pass  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft sm_carg  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);

        PriorityComparator pc = new PriorityComparator();

        Assert.assertTrue(0 < pc.compare( sm_carg  , def_carg ));
        Assert.assertTrue(0 < pc.compare( sm_carg  , def_lg   ));

        Assert.assertTrue(0 > pc.compare( def_pass , sm_pass  ));
        Assert.assertTrue(0 > pc.compare( def_sm   , sm_carg  ));
    }

    @Test
    public void Compare_Type_Policy() {  // Hopefully unnecessary, but req. for math completeness
        Aircraft sm_pass  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft sm_carg  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft lg_pass  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);

        PriorityComparator pc = new PriorityComparator();

        Assert.assertTrue( 0 < pc.compare( lg_pass  , lg_carg  ));
        Assert.assertTrue( 0 < pc.compare( sm_pass  , sm_carg  ));

        Assert.assertTrue( 0 > pc.compare( lg_carg  , lg_pass  ));
        Assert.assertTrue( 0 > pc.compare( sm_carg  , sm_pass  ));
    }

    @Test
    public void Compare_Size_Policy() {  // Hopefully unnecessary, but req. for math completeness
        Aircraft sm_pass  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft sm_carg  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft lg_pass  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);

        PriorityComparator pc = new PriorityComparator();

        Assert.assertTrue(0 < pc.compare( lg_pass  , sm_pass  ));
        Assert.assertTrue(0 < pc.compare( lg_carg  , sm_carg  ));

        Assert.assertTrue(0 > pc.compare( sm_pass  , lg_pass  ));
        Assert.assertTrue(0 > pc.compare( sm_carg  , lg_carg  ));
    }

    @Test
    public void Compare_Age_Policy() {  // Hopefully unnecessary, but req. for math completeness
        Aircraft sm_pass1  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft sm_carg1  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft lg_pass1  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg1  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);

        Aircraft sm_pass2  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft sm_carg2  = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft lg_pass2  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg2  = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);

        PriorityComparator pc = new PriorityComparator();

        Assert.assertTrue(0 < pc.compare( sm_pass1  , sm_pass2  ));
        Assert.assertTrue(0 < pc.compare( sm_carg1  , sm_carg2  ));
        Assert.assertTrue(0 < pc.compare( lg_pass1  , lg_pass2  ));
        Assert.assertTrue(0 < pc.compare( lg_carg1  , lg_carg2  ));

        Assert.assertTrue(0 > pc.compare( sm_pass2  , sm_pass1  ));
        Assert.assertTrue(0 > pc.compare( sm_carg2  , sm_carg1  ));
        Assert.assertTrue(0 > pc.compare( lg_pass2  , lg_pass1  ));
        Assert.assertTrue(0 > pc.compare( lg_carg2  , lg_carg1  ));

    }
}
