package com.delhi.metro.services;

import java.time.LocalDateTime;

import com.delhi.metro.FareStrategy;
import com.delhi.metro.factory.FareStrategyFactory;
import com.delhi.metro.model.Station;

public class FareCalculator {

	  public double getFare(Station source, Station destination, LocalDateTime localDateTime) {
	        FareStrategy fareStrategy = FareStrategyFactory.getFareStrategy(localDateTime);
	        int distance = source.distance(destination);

	        double fare = distance * fareStrategy.getFarePerStation();

	        return fare;
	    }
	
}
