package listmovie;

import listmovie.Repositories.MovieBasicInfoRepository;
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
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.management.Notification;


import com.fasterxml.jackson.databind.ObjectMapper;
import listmovie.*;
import listmovie.Models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class QueueConsumer {
    @Autowired
    MovieBasicInfoRepository movieRepository;


    /*@RabbitListener(queues = "TestQ")*/
    /*public void processMessage(String content) {
      System.out.println(content);
}*/
    @RabbitListener(queues = "Movie-list")
    public void processMessage(String movie) {
        try {
            JSONObject jsonObject = new JSONObject(movie);
            movieRepository.save(new MovieBasicInfo(jsonObject.getInt("id"), jsonObject.getString("naziv")));
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}