package cat.xojan.kafkademo.controller;

import cat.xojan.kafkademo.consumer.TrainTopicConsumer;
import cat.xojan.kafkademo.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    TrainTopicConsumer trainTopicConsumer;

    @GetMapping(path = "/api/v1/position", produces = "application/json")
    public List<Train> getTrainsPosition() {
        return trainTopicConsumer.getTrainsData();
    }
}

