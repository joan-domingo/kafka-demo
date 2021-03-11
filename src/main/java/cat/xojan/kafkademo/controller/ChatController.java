package cat.xojan.kafkademo.controller;

import cat.xojan.kafkademo.KafkaConstants;
import cat.xojan.kafkademo.consumer.KafkaTopicListener;
import cat.xojan.kafkademo.model.Message;
import cat.xojan.kafkademo.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    KafkaTopicListener kafkaTopicListener;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            //Sending the message to kafka topic queue
            System.out.println("producing topic..."+ message);
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/position", produces = "application/json")
    public List<Train> getTrainsPosition() {
        return kafkaTopicListener.getTrainsData();
    }
}

