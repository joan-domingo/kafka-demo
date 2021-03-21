package cat.xojan.kafkademo.service;

import cat.xojan.kafkademo.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class ExternalEndpointPollingService {

    @Autowired
    ExternalEndpointClient externalEndpointClient;

    @Autowired
    KafkaProducer kafkaProducer;

    public ExternalEndpointPollingService() {
            Timer timer = new Timer();
            timer.schedule(poll(), 0, 5000);
    }

    private TimerTask poll() {
        return new TimerTask() {
            @Override
            public void run() {
                externalEndpointClient.retrieveExternalDataAndProduceTopic(kafkaProducer);
            }
        };
    }
}
