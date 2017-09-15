package service;

import model.Aircraft;
import model.TrafficServiceRequestType;
import org.junit.*;

public class TrafficService_Test {

    @Test
    public void TestServiceStart() {
        TrafficService service = new TrafficService();

        Aircraft foo = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        // Service is not started, queueing an aircraft should not work
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, foo);
        Assert.assertEquals(null, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));

        // Start service.
        service.aqmRequestProcess(TrafficServiceRequestType.START);

        // Service should work
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, foo);
        Assert.assertEquals(foo, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));
    }


    @Test
    public void TestServicePolicy() {
        TrafficService service = new TrafficService();
        service.aqmRequestProcess(TrafficServiceRequestType.START);

        // first batch
        Aircraft unknown_1 = new Aircraft();
        Aircraft sm_carg_1 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.SMALL);
        Aircraft lg_carg_1 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.LARGE);
        Aircraft sm_pass_1 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.SMALL);
        Aircraft lg_pass_1 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, unknown_1);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, sm_carg_1);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, lg_carg_1);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, sm_pass_1);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, lg_pass_1);

        // second batch
        Aircraft unknown_2 = new Aircraft();
        Aircraft sm_carg_2 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.SMALL);
        Aircraft lg_carg_2 = new Aircraft().type(Aircraft.Type.CARGO).size(Aircraft.Size.LARGE);
        Aircraft sm_pass_2 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.SMALL);
        Aircraft lg_pass_2 = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, unknown_2);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, sm_carg_2);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, lg_carg_2);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, sm_pass_2);
        service.aqmRequestProcess(TrafficServiceRequestType.ENQUEUE, lg_pass_2);

        // check order of dequeue
        Assert.assertEquals(lg_pass_1, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));
        Assert.assertEquals(lg_pass_2, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));

        Assert.assertEquals(sm_pass_1, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));
        Assert.assertEquals(sm_pass_2, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));

        Assert.assertEquals(lg_carg_1, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));
        Assert.assertEquals(lg_carg_2, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));

        Assert.assertEquals(sm_carg_1, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));
        Assert.assertEquals(sm_carg_2, service.aqmRequestProcess(TrafficServiceRequestType.DEQUEUE));

        // Don't really care what happens with the unknown types as long as they don't interfere with the known types.
    }
}
