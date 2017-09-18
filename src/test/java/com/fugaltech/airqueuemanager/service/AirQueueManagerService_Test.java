package com.fugaltech.airqueuemanager.service;

import com.fugaltech.airqueuemanager.model.AirQueue;
import com.fugaltech.airqueuemanager.model.Aircraft;
import org.junit.*;

public class AirQueueManagerService_Test {

    @Test
    public void TestServiceStart() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        AirQueue queue = new AirQueue(comparator);
        AirQueueManagerService service = new AirQueueManagerService(queue);

        Aircraft foo = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        // Service is not started, queueing an aircraft should not work
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, foo);
        Assert.assertEquals(null, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));

        // Start service.
        service.aqmRequestProcess(AirQueueManagerRequestType.START);

        // Service should work
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, foo);
        Assert.assertEquals(foo, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));
    }


    @Test
    public void TestServicePolicy() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        AirQueue queue = new AirQueue(comparator);
        AirQueueManagerService service = new AirQueueManagerService(queue);
        service.aqmRequestProcess(AirQueueManagerRequestType.START);

        // first batch
        Aircraft unknown_1 = new Aircraft();
        Aircraft sm_carg_1 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.SMALL);
        Aircraft lg_carg_1 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.LARGE);
        Aircraft sm_pass_1 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.SMALL);
        Aircraft lg_pass_1 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, unknown_1);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, sm_carg_1);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, lg_carg_1);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, sm_pass_1);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, lg_pass_1);

        // second batch
        Aircraft unknown_2 = new Aircraft();
        Aircraft sm_carg_2 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.SMALL);
        Aircraft lg_carg_2 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.LARGE);
        Aircraft sm_pass_2 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.SMALL);
        Aircraft lg_pass_2 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, unknown_2);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, sm_carg_2);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, lg_carg_2);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, sm_pass_2);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, lg_pass_2);

        // check order of dequeue
        Assert.assertEquals(lg_pass_1, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));
        Assert.assertEquals(lg_pass_2, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));

        Assert.assertEquals(sm_pass_1, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));
        Assert.assertEquals(sm_pass_2, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));

        Assert.assertEquals(lg_carg_1, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));
        Assert.assertEquals(lg_carg_2, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));

        Assert.assertEquals(sm_carg_1, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));
        Assert.assertEquals(sm_carg_2, service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE));

        // Don't really care what happens with the unknown types as long as they don't interfere with the known types.
    }
}
