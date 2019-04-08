package com.ncepu.feilong505.LabManage.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO rabbitmq 配置
 * 
 * @author xtysummer1121@foxmail.com
 * @date 2019年3月12日
 */
@Configuration
public class RabbitMQConfig {
	// 点对点消息队列
	@Bean
	public Queue newQueue() {
		return new Queue("newqueue");
	}
	//点对点的交换机
	@Bean
	DirectExchange newExcange() {
		return new DirectExchange("newexchange");
	}
	//绑定消息队列和交换机，和路由键
	@Bean
	Binding bindingExchangeMessage() {
		return BindingBuilder.bind(newQueue()).to(newExcange()).with("newqueue");
	}
}
