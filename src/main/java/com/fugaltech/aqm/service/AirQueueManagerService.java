package com.fugaltech.aqm.service;

import com.fugaltech.aqm.model.AirQueue;
import com.fugaltech.aqm.model.Aircraft;
import org.springframework.context.annotation.ComponentScan;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public interface AirQueueManagerService {

    public Aircraft aqmRequestProcess(AirQueueManagerRequestType request, Aircraft aircraft);
    public Aircraft aqmRequestProcess(AirQueueManagerRequestType request);

}
