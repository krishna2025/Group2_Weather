package com.vzw.bits.weatherReport.consumer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class RestServiceTemplate {
	public static void pause() {
		try {
			Thread.sleep(1000);
		}catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public ArrayList<Integer> getTempature(String str){
		
		ArrayList<Integer> rands = new ArrayList();
		int min = Integer.parseInt(str);
		while (min > 0) {
			pause();
			rands.add(ThreadLocalRandom.current().nextInt(0, 100));
			min--;
		}
		return rands;
	}
}
