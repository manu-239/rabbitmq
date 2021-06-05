package com.amqp.rabbitmq.producer.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq/")
public class RabbitMqController {
	
	@Autowired
	AmqpTemplate amqpTemplate;
	
	@GetMapping("producer")
	public ResponseEntity<String> producer(@RequestParam("exchangeName") String exchange, 
			@RequestParam("key") String key, @RequestParam("message") String message ) {
			amqpTemplate.convertSendAndReceive(exchange, key, message);
			return ResponseEntity.ok("Message sent successfully");
	}
	
	@GetMapping("producer/header")
	public ResponseEntity<String> producerHeader(@RequestParam("exchangeName") String exchange, 
			@RequestParam("key") String key, @RequestParam("message") String message, 
			@RequestParam String header1,   @RequestParam String header2) {
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setHeader("header1", header1);
			messageProperties.setHeader("header2", header2);
			MessageConverter messageConverter = new SimpleMessageConverter();
			Message msg = messageConverter.toMessage(message, messageProperties);
			amqpTemplate.sendAndReceive(exchange, null, msg);
			return ResponseEntity.ok("Message sent successfully");
	}

}
