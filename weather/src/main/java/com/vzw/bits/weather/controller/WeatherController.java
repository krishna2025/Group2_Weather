package com.vzw.bits.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vzw.bits.weather.postMsg.Producer;

@RestController
public class WeatherController {
	@Autowired
	Producer producer;
	
	@RequestMapping(value="/WeatherMQ", method = RequestMethod.GET,consumes= {MediaType.ALL_VALUE})
	public String getCityState(@RequestBody String iput) {
		producer.produceMsg(iput);
		return "Successfull Posted the Weather Message";
	}

}
