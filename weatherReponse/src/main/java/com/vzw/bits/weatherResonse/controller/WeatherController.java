package com.vzw.bits.weatherResonse.controller;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@RequestMapping(value = "/WeatherRes", method = RequestMethod.GET, consumes = { MediaType.ALL_VALUE }, produces = {
			MediaType.ALL_VALUE })
	public void getCityState(@QueryParam(value = "id") String id, @QueryParam(value = "city") String city) {

		logger.info("Final Weather Report for Temp" + id);
	}

}
