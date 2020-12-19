package com.vzw.bits.weather.postMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private AmqpTemplate amqpTemplate;
	
	@Value("${exchange}")
	private String exchange;
	
	@Value("${routingkey}")
	private String routinekey;
	
	public void produceMsg(String msg) {
		logger.info("Weather message posted::"+msg);
		amqpTemplate.convertAndSend(exchange,routinekey,msg);
	}

}
