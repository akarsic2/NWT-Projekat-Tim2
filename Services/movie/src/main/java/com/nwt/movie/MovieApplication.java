package com.nwt.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nwt.movie.models.QueueProducer;

import org.springframework.boot.CommandLineRunner;
import org.json.JSONObject;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MovieApplication implements CommandLineRunner{
	@Autowired
	QueueProducer producer;
	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		JSONObject o = new JSONObject();
		o.put("id",1);
		o.put("naziv","kk");
		producer.send(o);
	}
}
