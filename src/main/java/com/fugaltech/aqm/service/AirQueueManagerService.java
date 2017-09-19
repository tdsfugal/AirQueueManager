package com.fugaltech.aqm.service;

import javax.inject.Named;

/**
 *
 * The AirQueueManagerService operates a queue of aircraft.  It has one method that accepts two argumetns, a request
 * type that specializes the request, and a Json request body that provides any additional parameters.
 *
 */
@Named
public interface AirQueueManagerService {

    /**
     *
     * The request type enumeration describes the behaviors of the RequestProcess:
     *
     *    START - Starts the queue
     *    ENQUEUE - Adds one aircraft to the queue
     *    DEQUEUE - Pops the highest priority aircraft from the queue
     */
    enum RequestType {
        START, ENQUEUE, DEQUEUE;
    }

    /**
     *  Both the input requestBody string and the output string for this function are in serialized JSON form.
     *  The RequestBody is not required for all request types.
     *
     * @param requestType
     * @param requestBody
     * @return
     */
    public String aqmRequestProcess(RequestType requestType, String requestBody);
    public String aqmRequestProcess(RequestType requestType);

}

