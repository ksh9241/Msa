package com.example.catalogservice.messagequeue;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    @Autowired
    CatalogRepository repository;

    @KafkaListener(topics = "example-order-topic")
    public void updateQty(String kafkaMessage, Acknowledgment acknowledgment) {
        log.info("Kafka Message: ->" + kafkaMessage);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        CatalogEntity entity = repository.findByProductId((String)map.get("productId"));
        if (entity != null) {
            entity.setStock(entity.getStock() - (Integer)map.get("qty"));
            repository.save(entity);
        }

        // 모든 작업이 끝난 뒤에 OffSet 정보를 커밋할 수 있도록 신호를 보낸다.
        acknowledgment.acknowledge();
    }
}
