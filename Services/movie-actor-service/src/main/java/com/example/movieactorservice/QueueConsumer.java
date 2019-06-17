package com.example.movieactorservice;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.management.Notification;

import com.example.movieactorservice.film.Film;
import com.example.movieactorservice.film.FilmRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Component
public class QueueConsumer {
    @Autowired 
    FilmRepository movieRepository;
    /*@RabbitListener(queues = "TestQ")*/
    /*public void processMessage(String content) {
      System.out.println(content);
}*/
    @RabbitListener(queues = "Movie-actor")
    public void processMessage(String movie) {
        try {
            JSONObject jsonObject = new JSONObject(movie);
            System.out.println("Jeeeel");
            movieRepository.save(new Film(jsonObject.getInt("id"), jsonObject.getString("naziv")));
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}