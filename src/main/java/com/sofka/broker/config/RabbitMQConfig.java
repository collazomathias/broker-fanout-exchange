package com.sofka.broker.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public FanoutExchange exchange() {
        return new TopicExchange("portero.exchange.fanout");
    }

    @Bean
    public Queue queuePrimerPiso() {
        return new Queue("queue.impar.1", false);
    }

    @Bean
    public Queue queueSegundoPiso() {
        return new Queue("queue.par.2", false);
    }

    @Bean
    public Queue queueTercerPiso() {
        return new Queue("queue.impar.3", false);
    }

    @Bean
    public Binding binding1(Queue queuePrimerPiso, FanoutExchange exchange) {
        return BindingBuilder.bind(queuePrimerPiso).to(exchange);
    }

    @Bean
    public Binding binding2(Queue queueSegundoPiso, FanoutExchange exchange) {
        return BindingBuilder.bind(queueSegundoPiso).to(exchange);
    }

    @Bean
    public Binding binding3(Queue queueTercerPiso, FanoutExchange exchange) {
        return BindingBuilder.bind(queueTercerPiso).to(exchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
