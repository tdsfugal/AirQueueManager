package web;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import service.AirQueueManagerService;
import service.AirQueuePriorityComparator;

import static model.AirQueueManagerRequestType.START;

@Controller
@EnableAutoConfiguration
public class AirQueueManagerController {

    AirQueueManagerService service;

    AirQueueManagerController() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        this.service = new AirQueueManagerService(comparator);
    }

    @RequestMapping("/start")
    @ResponseBody
    String start() {
        service.aqmRequestProcess(START);
        return "Air Queue Manager Started!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AirQueueManagerController.class, args);
    }

}
