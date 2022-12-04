package com.microservices.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentsAMQPConfiguration {
    @Bean
    public Queue createQueue() {
//        return new Queue("Payment concluded", false);
       return QueueBuilder.nonDurable("Payment concluded").build();
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializerAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}