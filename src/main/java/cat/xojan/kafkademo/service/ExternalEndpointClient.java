package cat.xojan.kafkademo.service;

import cat.xojan.kafkademo.model.ExternalServiceResponse;
import cat.xojan.kafkademo.model.Feature;
import cat.xojan.kafkademo.model.Train;
import cat.xojan.kafkademo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExternalEndpointClient {

    private final WebClient webClient;
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(5);

    public ExternalEndpointClient(WebClient.Builder builder,
                                  @Value("${external.service.baseUrl}") String baseUrl) {
        webClient = builder.baseUrl(baseUrl).build();
    }

    protected void retrieveExternalDataAndProduceTopic(KafkaProducer kafkaProducer) {
        try {
            var response = webClient.get()
                    .uri("/tracker/trens.geojson")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(ExternalServiceResponse.class)
                    .block(REQUEST_TIMEOUT);

            var trains = convertResponseToTrainDataList(response);

            kafkaProducer.produceTopic(trains);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
