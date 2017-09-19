package com.fugaltech.aqm.web;

import com.fugaltech.aqm.model.Aircraft;
import com.fugaltech.aqm.service.AirQueueManagerService;
import com.fugaltech.aqm.service.AirQueueManagerService.RequestType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return service.aqmRequestProcess(RequestType.START);
    }


    @PostMapping("/enqueue")
    public String enqueue(@RequestBody String requestBody) {
        return service.aqmRequestProcess(RequestType.ENQUEUE, requestBody);
    }

    @GetMapping("/dequeue")
    public String dequeue() {
        return service.aqmRequestProcess(RequestType.DEQUEUE);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AirQueueManagerController.class, args);
    }

}

