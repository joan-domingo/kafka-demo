package cat.xojan.kafkademo.consumer;

import cat.xojan.kafkademo.KafkaConstants;
import cat.xojan.kafkademo.model.Message;
import cat.xojan.kafkademo.model.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaTopicListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicListener.class);

    private List<Train> trainsData = new ArrayList<>();

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println("consuming topic via kafka listener..."+ message);
    }

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC_TRAINS,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(List<Train> trains) {
        logger.debug("Consuming Trains data topic");
        trainsData = trains;
    }

    public List<Train> getTrainsData() {
        return trainsData;
    }
}


