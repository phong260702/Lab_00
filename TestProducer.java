package org.example;
import java.util.Properties;
import org.apache.kafka.clients.producer.*;


public class TestProducer {
    private static Properties kafkaProps = new Properties();
    public static void main(String[] args) throws InterruptedException {
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String,String> producer = new KafkaProducer<>(kafkaProps);
        for (int i =0; i < 100; i++){
            Thread.sleep(1000);
            ProducerRecord<String, String> record = new ProducerRecord<>("sequence", Integer.toString(i), "Number: "+ Integer.toString(i));
            try {
                producer.send(record);
                System.out.println("Done");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        producer.close();
    }
}
