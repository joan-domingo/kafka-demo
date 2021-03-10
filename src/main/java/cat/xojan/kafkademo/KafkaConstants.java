package cat.xojan.kafkademo;

public class KafkaConstants {
    public static final String KAFKA_TOPIC = "kafka-chat";
    public static final String GROUP_ID = "kafka-sandbox";

    public static String getKafkaBroker(String env) {
        return env.equals("debug")
                ? "localhost:9092"
                : "\"b-2.kafka-demo-cluster.9yoc6i.c4.kafka.eu-central-1.amazonaws.com:9092,b-1.kafka-demo-cluster.9yoc6i.c4.kafka.eu-central-1.amazonaws.com:9092\"";
    }
}
