package com.fugaltech.aqm.web;

import com.fugaltech.aqm.model.Aircraft;
import com.fugaltech.aqm.service.AirQueueManagerRequestType;
import com.fugaltech.aqm.service.AirQueueManagerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@EnableAutoConfiguration
@ComponentScan("com.fugaltech.aqm.service")
public class AirQueueManagerController {

    AirQueueManagerService service;

    @Inject
    AirQueueManagerController(AirQueueManagerService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String start() {
        service.aqmRequestProcess(AirQueueManagerRequestType.START);
        return "index";
    }


    @PostMapping("/enqueue")
    public String enqueue(String request) {

        System.out.println(request);

        Aircraft ac = new Aircraft("foo").size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        service.aqmRequestProcess(AirQueueManagerRequestType.ENQUEUE, ac);

        return ac.toJson();
    }

    @GetMapping("/dequeue")
    public String dequeue() {
        Aircraft ac = service.aqmRequestProcess(AirQueueManagerRequestType.DEQUEUE);
        String json = (ac != null) ? ac.toJson() : "{}";
        return json;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AirQueueManagerController.class, args);
    }

}

