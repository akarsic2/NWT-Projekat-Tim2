package com.nwt.movie.models;

import javax.management.Notification;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.json.JSONObject;


@Component
public class QueueProducer {
    @Autowired
    RabbitMessagingTemplate template;

    @Bean
    Queue queue() {
      return new Queue("Movies", false);
    }
    @Bean
    Queue queueActor() {
      return new Queue("Movie-actor", false);
    }
    @Bean
    Queue queueUser() {
      return new Queue("Movie-user", false);
    }

    @Bean
    Queue queueList() {
      return new Queue("Movie-list", false);
    }
    public void send(String movie){
      template.convertAndSend("Movies", movie);
      template.convertAndSend("Movie-actor", movie);
      template.convertAndSend("Movie-user",movie);
      template.convertAndSend("Movie-list",movie);

    }


}