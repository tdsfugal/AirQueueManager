package com.fugaltech.aqm.service.impl;

import com.fugaltech.aqm.model.AirQueue;
import com.fugaltech.aqm.model.Aircraft;
import com.fugaltech.aqm.service.AirQueueManagerRequestType;
import com.fugaltech.aqm.service.AirQueueManagerService;
import org.springframework.context.annotation.ComponentScan;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@ComponentScan("com.fugaltech.aqm.model")
public class AirQueueManagerServiceImpl implements AirQueueManagerService {

    private AirQueue queue;
    private Boolean started;

    @Inject
    public AirQueueManagerServiceImpl(AirQueue queue) {
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

    @Override
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
