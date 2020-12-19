package com.vzw.bits.weatherReport.consumer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class Consumer {
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	private RestTemplate restTemplate;

	@RabbitListener(queues = "city")
	public void recievedMessages(String msg) {
		logger.info("Recieved Message from City Queue:::: " + msg);
		try {
			recievedMessage(msg);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Consumer(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String postWeatherRes(String msg, String city) {
		String url = "http://localhost:9009/WeatherResponse/WeatherRes?id=" + msg + ",city=" + city;
		return this.restTemplate.getForObject(url, String.class);
	}

	public void recievedMessage(String msg) throws ParseException, JSONException {
		Object obj = new JSONParser().parse(msg);
		JSONObject jo = (JSONObject) obj;
		JSONArray jsonarr_1 = (JSONArray) jo.get("results");
		Iterator itr2 = jsonarr_1.iterator();

		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();
			while (itr1.hasNext()) {
				Map.Entry pair = itr1.next();
				String city = pair.getValue().toString();
				pair = itr1.next();
				RestServiceTemplate restServiceTemplate = new RestServiceTemplate();
				ArrayList<Integer> getTempature = restServiceTemplate.getTempature(pair.getValue().toString());
				postWeatherRes(getTempature.toString(), city);
			}
		}

	}
}
