package com.sofka.broker.controllers;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProducerController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping("/send")
    public String publishUser(@RequestParam String message) {
        amqpTemplate.convertAndSend("portero.exchange.fanout", "", message);
        return "Message sent successfully";
    }

}
