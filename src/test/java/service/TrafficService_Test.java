package service;

import model.Aircraft;
import org.junit.*;

public class TrafficService_Test {

    @Test
    public void StartService() {
        TrafficService service = new TrafficService();

        Aircraft foo = new Aircraft().type(Aircraft.Type.PASSENGER).size(Aircraft.Size.LARGE);

        // Service is not started, should not work
        service.enqueue(foo);
        Assert.assertEquals(null, service.dequeue());

        // Start service.  It should work.
        service.start();
        service.enqueue(foo);
        Assert.assertEquals(foo, service.dequeue());

    }
}
