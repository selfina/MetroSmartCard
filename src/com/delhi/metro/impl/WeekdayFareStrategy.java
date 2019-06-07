package com.delhi.metro.impl;

import com.delhi.metro.FareStrategy;

public class WeekdayFareStrategy implements FareStrategy {

	@Override
	public String getName() {
		
		return WeekdayFareStrategy.class.toGenericString();
	}

	@Override
	public double getFarePerStation() {

		return 7;
	}

}
