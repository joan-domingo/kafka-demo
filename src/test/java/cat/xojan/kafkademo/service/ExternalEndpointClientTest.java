package cat.xojan.kafkademo.service;

import cat.xojan.kafkademo.KafkaDemoApplicationTests;
import cat.xojan.kafkademo.model.Train;
import cat.xojan.kafkademo.producer.KafkaProducer;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ExternalEndpointClientTest extends KafkaDemoApplicationTests {

    private MockWebServer mockWebServer;

    private ExternalEndpointClient externalEndpointClient;

    @Mock
    KafkaProducer kafkaProducer;

    @BeforeEach
    public void setUp() {
        mockWebServer = new MockWebServer();
        externalEndpointClient = new ExternalEndpointClient(WebClient.builder(), mockWebServer.url("/").toString());
    }

    @Test
    public void testSomething() throws Exception {
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody("{\"features\": [{\"type\": \"Feature\", \"geometry\": {\"type\": \"Point\", \"coordinates\": [2.164431200953373, 41.387929328696764]}, \"properties\": {\"id\": \"D0336\", \"lin\": \"F1\", \"nc\": \"D0336\", \"dir\": \"D\", \"en\": \"PC\", \"cv\": \"PC12\", \"origen\": \"NA\", \"desti\": \"PC\", \"properes_parades\": [{\"parada\": \"PC\"}], \"estacionat_a\": null, \"en_hora\": true, \"retard\": \"01:44\", \"material\": \"113.03\", \"ocupacio\": {\"linia\": \"S1 BARCELONA\", \"estacio\": \"Proven\\u00e7a\", \"estacio_codi\": \"PR\", \"factor\": \"77.35\", \"dt\": \"2021-03-21T12:11:31+01:00\", \"m1\": {\"tipus_cotxe\": \"m1\", \"persones\": 8, \"massa\": 3831}, \"mi\": {\"tipus_cotxe\": \"mi\", \"persones\": 8, \"massa\": 3621}, \"ri\": {\"tipus_cotxe\": \"ri\", \"persones\": 18, \"massa\": 2844}, \"m2\": {\"tipus_cotxe\": \"m2\", \"persones\": 29, \"massa\": 3992}}}}], \"name\":\"duke\"}");
        mockWebServer.enqueue(mockResponse);

        var expectedResult = new ArrayList<Train>();
        expectedResult.add(new Train("D0336", "F1", 2.164431200953373, 41.387929328696764));

        externalEndpointClient.retrieveExternalDataAndProduceTopic(kafkaProducer);

        Mockito.verify(kafkaProducer).produceTopic(expectedResult);

        mockWebServer.shutdown();
    }
}
