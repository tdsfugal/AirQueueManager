package model;

import org.junit.*;
import service.AirQueuePriorityComparator;

public class AirQueue_Test {

    @Test
    public void EmptyQueue() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        AirQueue q = new AirQueue(comparator);
        Assert.assertEquals(0, q.waiting());
    }

    @Test
    public void EnqueueAllForms() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        AirQueue q = new AirQueue(comparator);

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


    @Test
    public void DequeueAllForms() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        AirQueue q = new AirQueue(comparator);

        Aircraft def = new Aircraft();
        q.enqueue(def);

        Aircraft sm_carg1 = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft sm_pass1 = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg1 = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);
        Aircraft lg_pass1 = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        q.enqueue(sm_carg1);
        q.enqueue(sm_pass1);
        q.enqueue(lg_carg1);
        q.enqueue(lg_pass1);

        Aircraft sm_carg2 = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.CARGO);
        Aircraft sm_pass2 = new Aircraft().size(Aircraft.Size.SMALL).type(Aircraft.Type.PASSENGER);
        Aircraft lg_carg2 = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.CARGO);
        Aircraft lg_pass2 = new Aircraft().size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        q.enqueue(sm_carg2);
        q.enqueue(sm_pass2);
        q.enqueue(lg_carg2);
        q.enqueue(lg_pass2);

        Assert.assertEquals(lg_pass1, q.dequeue());
        Assert.assertEquals(lg_pass2, q.dequeue());

        Assert.assertEquals(sm_pass1, q.dequeue());
        Assert.assertEquals(sm_pass2, q.dequeue());

        Assert.assertEquals(lg_carg1, q.dequeue());
        Assert.assertEquals(lg_carg2, q.dequeue());

        Assert.assertEquals(sm_carg1, q.dequeue());
        Assert.assertEquals(sm_carg2, q.dequeue());

        Assert.assertEquals(def     , q.dequeue());    // Unknown aircraft are last
    }

}
