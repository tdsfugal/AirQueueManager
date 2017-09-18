package service;

import model.Aircraft;
import model.AirQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AirQueueManagerService {

    private AirQueue queue;
    private Boolean started;

    @Autowired
    public AirQueueManagerService(AirQueue queue) {
        this.queue = queue;
        started = false;
    }

    private Aircraft start() {
        started = true;
        return null;
    }

    private Aircraft enqueue(Aircraft a) {
        if (started) try {
            queue.enqueue(a);
            return a;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    private Aircraft dequeue() {
        if (started) try {
            Aircraft ac = queue.dequeue();
            return ac;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Aircraft aqmRequestProcess(AirQueueManagerRequestType request, Aircraft aircraft) {

        switch (request) {
            case START:
                return this.start();

            case ENQUEUE:
                return this.enqueue(aircraft);

            case DEQUEUE:
                return this.dequeue();

            default:
                return null;
        }

    }

    public Aircraft aqmRequestProcess(AirQueueManagerRequestType request) {
        return aqmRequestProcess(request, null);
    }
}
