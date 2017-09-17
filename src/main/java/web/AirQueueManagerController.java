package web;

import com.google.gson.JsonNull;
import model.Aircraft;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import service.AirQueueManagerService;
import service.AirQueuePriorityComparator;

import javax.annotation.*;

import static model.AirQueueManagerRequestType.DEQUEUE;
import static model.AirQueueManagerRequestType.ENQUEUE;
import static model.AirQueueManagerRequestType.START;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@EnableAutoConfiguration
public class AirQueueManagerController {

    AirQueueManagerService service;

    AirQueueManagerController() {
        AirQueuePriorityComparator comparator = new AirQueuePriorityComparator();
        this.service = new AirQueueManagerService(comparator);
    }

    @RequestMapping(value="/", method=GET)
    public String start() {
        service.aqmRequestProcess(START);

        Aircraft ac = new Aircraft("foo").size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        service.aqmRequestProcess(ENQUEUE, ac);

        return "This should return the web page as a static HTML file";
    }


    @RequestMapping(value="/enqueue", method=POST)
    public String enqueue(Object request) {
        Aircraft ac = new Aircraft("foo").size(Aircraft.Size.LARGE).type(Aircraft.Type.PASSENGER);
        service.aqmRequestProcess(ENQUEUE, ac);
        return ac.toJson();
    }

    @RequestMapping(value="/dequeue", method=GET)
    public String dequeue() {
        Aircraft ac = service.aqmRequestProcess(DEQUEUE);
        String json = (ac != null) ? ac.toJson() : "{}";
        return json;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AirQueueManagerController.class, args);
    }

}

