package com.amqp.rabbitmq.producer.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	/* Queues */
	@Bean
	Queue defaultQueue() {
		return new Queue("default-queue",false);
	}
	
	@Bean
	Queue directQueue() {
		return new Queue("direct-queue",false);
	}
	
	@Bean
	Queue headersQueue() {
		return new Queue("headers-queue",false);
	}
	
	@Bean
	Queue topicQueue() {
		return new Queue("topic-queue",false);
	}
	
	@Bean
	Queue fanoutOneQueue() {
		return new Queue("fanout-one-queue",false);
	}
	
	@Bean
	Queue fanoutTwoQueue() {
		return new Queue("fanout-two-queue",false);
	}
	
	/* Exchanges */
	@Bean
	DirectExchange directExchange(){
		return new DirectExchange("direct-exchange");
	}
	
	@Bean
	TopicExchange topicExchange(){
		return new TopicExchange("topic-exchange");
	}
	
	@Bean
	FanoutExchange fanoutExchange(){
		return new FanoutExchange("fanout-exchange");
	}
	@Bean
	HeadersExchange headerExchange(){
		return new HeadersExchange("headers-exchange");
	}
	
	/* Bindings */
	@Bean
	Binding defaultQueueBinding(Exchange directExchange) {
		return BindingBuilder.bind(defaultQueue()).to(directExchange).with("default-queue").noargs();
	}
	
	@Bean
	Binding topicExchangeBinding(TopicExchange topicExchange) {
			return BindingBuilder.bind(topicQueue()).to(topicExchange).with("topic.#");
	}
	@Bean
	Binding directQueueBinding(DirectExchange directExchange) {
			return BindingBuilder.bind(directQueue()).to(directExchange).with("direct");
	}
	@Bean
	Binding headersQueueBinding(HeadersExchange headerExchange) {
			Map<String,Object> headerValues = new HashMap<>();
			headerValues.put("header1", "value1");
			headerValues.put("header2", "value2");
			return BindingBuilder.bind(headersQueue()).to(headerExchange).whereAll(headerValues).match();
	}
	
	@Bean
	Binding fanoutQueueOneBinding(FanoutExchange fanoutExchange) {
			return BindingBuilder.bind(fanoutOneQueue()).to(fanoutExchange);
	}
	
	@Bean
	Binding fanoutQueueTwoBinding(FanoutExchange fanoutExchange) {
			return BindingBuilder.bind(fanoutTwoQueue()).to(fanoutExchange);
	}
	
}
