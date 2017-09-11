package model;

import org.junit.*;

public class TrafficQueue_Test {

    @Test
    public void EmptyQueue() {
        TrafficQueue q = new TrafficQueue();
        Assert.assertEquals(0, q.waiting());
    }

    @Test
    public void EnqueudAllForms() {
        TrafficQueue q = new TrafficQueue();

        Aircraft def = new Aircraft();
        q.enqueue(def);

        Aircraft def_pass = new Aircraft().type(Aircraft.Type.PASSENGER);
        Aircraft def_carg = new Aircraft().type(Aircraft.Type.CARGO);
        Aircraft def_sm   = new Aircraft().size(Aircraft.Size.SMALL);
        Aircraft def_lg   = new Aircraft().size(Aircraft.Size.LARGE);
        q.enqueue(def_pass);
        q.enqueue(def_carg);
        q.enqueue(def_sm);
        q.enqueue(def_lg);

        Aircraft sm_pass = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft sm_carg = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft lg_pass = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);
        q.enqueue(sm_pass);
        q.enqueue(sm_carg);
        q.enqueue(lg_pass);
        q.enqueue(lg_carg);

        Assert.assertEquals(9, q.waiting());
    }

}
