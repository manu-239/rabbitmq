package com.amqp.rabbitmq.consumer.listner;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListner {
	

	@RabbitListener(queues = {"direct-queue"})
	public void recievedMessageFromDirectQueue(String message) {
		System.out.println("Recieved Message From RabbitMQ direct-queue : " + message + ", Thread ="+Thread.currentThread());
	}
}
