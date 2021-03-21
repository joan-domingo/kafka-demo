package cat.xojan.kafkademo.producer;

import cat.xojan.kafkademo.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaProducer {

    @Value("${kafka.topic.trains}")
    private String kafkaTopicTrains;

    @Autowired
    private KafkaTemplate<String, List<Train>> kafkaTemplate;

    public void produceTopic(List<Train> trainList) {
        try {
            kafkaTemplate.send(kafkaTopicTrains, trainList).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
