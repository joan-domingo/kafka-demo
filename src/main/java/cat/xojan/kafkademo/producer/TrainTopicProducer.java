package cat.xojan.kafkademo.producer;

import cat.xojan.kafkademo.KafkaConstants;
import cat.xojan.kafkademo.model.Feature;
import cat.xojan.kafkademo.model.ExternalServiceResponse;
import cat.xojan.kafkademo.model.Train;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class TrainTopicProducer {

    @Autowired
    private KafkaTemplate<String, List<Train>> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TrainTopicProducer.class);

    private final WebClient webClient;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);

    public TrainTopicProducer() {
        Timer timer = new Timer();
        webClient = WebClient.create("https://geotren.fgc.cat");
        timer.schedule(poll(), 0, 5000);
    }

    private TimerTask poll() {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    var response = webClient.get()
                            .uri("/tracker/trens.geojson")
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(ExternalServiceResponse.class)
                            .block(REQUEST_TIMEOUT);

                    var trains = convertResponseToTrainDataList(response);

                    kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC_TRAINS, trains).get();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.warn("Failed to retrieve geo positions");
                }
            }
        };
    }

    private List<Train> convertResponseToTrainDataList(ExternalServiceResponse externalServiceResponse) {
        List<Train> trainList = new ArrayList<>();
        for (Feature f : externalServiceResponse.getFeatures()) {
            Train t = convertFeatureToTrain(f);
            trainList.add(t);
        }
        return trainList;
    }

    private Train convertFeatureToTrain(Feature f) {
        var id = f.getProperties().getId();
        var line = f.getProperties().getLin();
        var longitude = f.getGeometry().getCoordinates().get(0);
        var latitude = f.getGeometry().getCoordinates().get(1);

        return new Train(id, line, longitude, latitude);
    }
}
