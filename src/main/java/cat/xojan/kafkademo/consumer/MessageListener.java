package cat.xojan.kafkademo.consumer;

import cat.xojan.kafkademo.KafkaConstants;
import cat.xojan.kafkademo.model.Message;
import cat.xojan.kafkademo.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println("consuming topic via kafka listener..."+ message);
        // template.convertAndSend("/topic/group", message);
    }

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC_TRAINS,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(List<Train> trains) {
        System.out.println("consuming topic via kafka listener..."+ trains);
        // template.convertAndSend("/topic/group", message);
    }
}


