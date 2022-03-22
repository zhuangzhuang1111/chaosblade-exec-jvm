package com.alibaba.chaosblade.exec.plugin.mercury;

/**
 * @author ljzhxx@gmail.com
 */
public interface MercuryConstant {

    String TARGET_NAME = "mercury";

    String PRODUCER_KEY = "topic";

    String CONSUMER_KEY = "subscription";

    String TOPIC_KEY = "topic";

    //String PRODUCER_CLASS = "org.apache.kafka.clients.producer.KafkaProducer";
    String KAFKA_PRODUCER_CLASS = "com.tuhu.mercury.producer.ProducerKafkaImpl";
    String RABBITMQ_PRODUCER_CLASS = "com.tuhu.mercury.producer.ProducerRmqImpl";

    String SEND = "send";
    String SEND_BATCH = "sendBatch";

    String KAFKA_CONSUMER_CLASS = "com.tuhu.mercury.enhance.kafka.consume.OrdinalConsumeTaskRequest";
    String KAFKA_CONSUME_METHOD = "processConsumeStatus";

    String RABBITMQ_CONSUMER_CLASS = "com.tuhu.mercury.consumer.ConsumerRmqImpl";
    String RMQ_CONSUME_METHOD = "processMessage";
}
