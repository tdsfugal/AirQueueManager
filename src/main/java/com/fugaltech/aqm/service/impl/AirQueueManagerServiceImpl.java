package com.fugaltech.aqm.service.impl;

import com.fugaltech.aqm.model.AirQueue;
import com.fugaltech.aqm.model.Aircraft;
import com.fugaltech.aqm.service.AirQueueManagerService;
import com.fugaltech.aqm.service.util.AircraftJsonUtilities;
import com.fugaltech.aqm.service.util.AircraftJsonUtilities.*;

import org.springframework.context.annotation.ComponentScan;

import javax.inject.Inject;
import javax.inject.Named;

import static com.fugaltech.aqm.service.util.AircraftJsonUtilities.aircraftToJson;
import static com.fugaltech.aqm.service.util.AircraftJsonUtilities.jsonToAircraft;

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

    private String start() {
        started = true;
        return "Queue Started";
    }

    /**
     *
     * Enqueue returns a JSON representation of the added aircraft if the queue operation is successful.
     *
     * @param a
     * @return
     */
    private String enqueue(String requestBody) {
        if (started) try {

            // Verify that request body conforms to contract.  (i.e. vaaidate)

            Aircraft aircraft = AircraftJsonUtilities.jsonToAircraft(requestBody);
            Aircraft response = queue.enqueue(aircraft);
            if (response == null) throw new NullPointerException("Error adding aircraft in AirQueueManagerService.enqueue");
            return response.toJson();
        } catch (Exception ex) {
            return "Error adding aircraft";
        }
        return "Queue not Started";
    }

    /**
     * Dequeue returns a JSON represntation of the highest priority aircraft in the queue.  If the queue is
     * empty it returns an empty json object.  If the operation fails it reurns a diagnostic message.
     *
     * @return
     */
    private String dequeue() {
        if (started) try {
            Aircraft ac = queue.dequeue();
            return (ac != null) ? ac.toJson() : "{}";
        } catch (Exception e) {
            return "Error removing aircraft";
        }
        return "Queue not Started";
    }


    @Override
    public String aqmRequestProcess(RequestType request, String requestBody) {

        switch (request) {
            case START:
                return start();

            case ENQUEUE:
                return enqueue(requestBody);

            case DEQUEUE:
                return dequeue();

            default:
                return null;
        }

    }

    @Override
    public String aqmRequestProcess(RequestType request) {
        return aqmRequestProcess(request, "");
    }

}
