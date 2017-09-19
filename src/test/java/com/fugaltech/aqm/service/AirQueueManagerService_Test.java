package com.fugaltech.aqm.service;

import com.fugaltech.aqm.model.AirQueue;
import com.fugaltech.aqm.model.Aircraft;
import com.fugaltech.aqm.service.impl.AirQueueManagerServiceImpl;
import com.fugaltech.aqm.service.AirQueueManagerService.RequestType;
import org.junit.*;

public class AirQueueManagerService_Test {

    @Test
    public void TestServiceStart() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        AirQueue queue = new AirQueue(comparator);
        AirQueueManagerService service = new AirQueueManagerServiceImpl(queue);

        Aircraft foo = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        // Service is not started, queueing an aircraft should not work
        service.aqmRequestProcess(RequestType.ENQUEUE, foo.toJson());
        Assert.assertEquals("Queue not Started", service.aqmRequestProcess(RequestType.DEQUEUE));

        // Start service.
        service.aqmRequestProcess(RequestType.START);

        // Service should work
        service.aqmRequestProcess(RequestType.ENQUEUE, foo.toJson());
        Assert.assertEquals(foo.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));
    }

    @Test
    public void TestServicePolicy() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        AirQueue queue = new AirQueue(comparator);
        AirQueueManagerService service = new AirQueueManagerServiceImpl(queue);
        service.aqmRequestProcess(RequestType.START);

        // first batch
        Aircraft unknown_1 = new Aircraft();
        Aircraft sm_carg_1 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.SMALL);
        Aircraft lg_carg_1 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.LARGE);
        Aircraft sm_pass_1 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.SMALL);
        Aircraft lg_pass_1 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        service.aqmRequestProcess(RequestType.ENQUEUE, unknown_1.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, sm_carg_1.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, lg_carg_1.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, sm_pass_1.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, lg_pass_1.toJson());

        // second batch
        Aircraft unknown_2 = new Aircraft();
        Aircraft sm_carg_2 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.SMALL);
        Aircraft lg_carg_2 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.LARGE);
        Aircraft sm_pass_2 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.SMALL);
        Aircraft lg_pass_2 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        service.aqmRequestProcess(RequestType.ENQUEUE, unknown_2.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, sm_carg_2.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, lg_carg_2.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, sm_pass_2.toJson());
        service.aqmRequestProcess(RequestType.ENQUEUE, lg_pass_2.toJson());

        // check order of dequeue
        Assert.assertEquals(lg_pass_1.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));
        Assert.assertEquals(lg_pass_2.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));

        Assert.assertEquals(sm_pass_1.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));
        Assert.assertEquals(sm_pass_2.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));

        Assert.assertEquals(lg_carg_1.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));
        Assert.assertEquals(lg_carg_2.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));

        Assert.assertEquals(sm_carg_1.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));
        Assert.assertEquals(sm_carg_2.toJson(), service.aqmRequestProcess(RequestType.DEQUEUE));

        // Don't really care what happens with the unknown types as long as they don't interfere with the known types.
    }
}
