package cat.xojan.kafkademo.consumer;

import cat.xojan.kafkademo.model.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainTopicConsumer {
    private static final Logger logger = LoggerFactory.getLogger(TrainTopicConsumer.class);

    private List<Train> trainsData = new ArrayList<>();

    @KafkaListener(
            topics = "${kafka.topic.trains}",
            groupId = "${kafka.group.id}"
    )
    public void listenToTrainData(List<Train> trains) {
        logger.debug("Consuming Trains data topic");
        trainsData = trains;
    }

    public List<Train> getTrainsData() {
        return trainsData;
    }
}


