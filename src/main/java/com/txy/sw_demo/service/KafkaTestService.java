package com.txy.sw_demo.service;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * @Auther: tianxiayu
 * @Date: 2020/11/2 15:23
 */
@Service
public class KafkaTestService {

    @Value("${monitor.kafka.address}")
    private String kafkaServerAddress;

    public String produce(String topic, String msg) {
        Properties properties = new Properties();
        // bootstrap.servers kafka集群地址 host1:port1,host2:port2 ....
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
        // key.deserializer 消息key序列化方式
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // value.deserializer 消息体序列化方式
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 2000);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        try {
            producer.send(new ProducerRecord<>(topic, msg));
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            producer.close();
        }

        return "OK";
    }

    /**
     * 获取指定主题的最近一条消息，如果没有消息则返回空串 ""
     * @param topic
     * @return
     */
    public String consume(String topic) {
        Properties properties = new Properties();
        //bootstrap.servers kafka集群地址 host1:port1,host2:port2 ....
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
        // key.deserializer 消息key序列化方式
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value.deserializer 消息体序列化方式
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // group.id 消费组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-group");
        // enable.auto.commit 设置自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // auto.offset.reset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);
        // 消费者订阅主题，可以订阅多个主题
        consumer.subscribe(Arrays.asList(topic));
        String res = "";
        try {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            Iterator<ConsumerRecord<String, String>> itr = records.iterator();

            while(itr.hasNext()){
                res = itr.next().value().toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            consumer.close();
        }

        return res;
    }
}