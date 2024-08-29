package com.jeferro.products.products.infrastructure.adapters.kafka;

import com.jeferro.products.products.domain.events.ProductEvent;
import com.jeferro.products.products.infrastructure.ProductsProperties;
import com.jeferro.products.products.infrastructure.adapters.kafka.mappers.ProductEventKafkaMapper;
import com.jeferro.shared.domain.events.EventBusPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductEventKafkaPublisher implements EventBusPublisher<ProductEvent> {

  private static final Logger logger = LoggerFactory.getLogger(ProductEventKafkaPublisher.class);

  private final ProductEventKafkaMapper productEventKafkaMapper = ProductEventKafkaMapper.INSTANCE;

  private final ProductsProperties productsProperties;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public ProductEventKafkaPublisher(ProductsProperties productsProperties,
	  KafkaTemplate<String, Object> kafkaTemplate) {
	this.productsProperties = productsProperties;
	this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void publish(ProductEvent event) {
	String key = event.getProductCode().toString();
	var data = productEventKafkaMapper.toDTO(event);

	kafkaTemplate.send(productsProperties.topic(), key, data)
		.exceptionally(cause -> {
		  logger.error("Error sending event {}", data, cause);
		  return null;
		});
  }

}