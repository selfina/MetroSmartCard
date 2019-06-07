package com.delhi.metro.factory;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import com.delhi.metro.FareStrategy;
import com.delhi.metro.impl.WeekdayFareStrategy;
import com.delhi.metro.impl.WeekendFareStrategy;

public class FareStrategyFactory {

	
	
	static final FareStrategy weack= new WeekdayFareStrategy();
	static final FareStrategy week= new WeekendFareStrategy();
	
	
	public static FareStrategy getFareStrategy(LocalDateTime localdatetime) {
		
		if(localdatetime.getDayOfWeek()==DayOfWeek.SATURDAY|| localdatetime.getDayOfWeek()==DayOfWeek.SUNDAY) {
			
			return week;
		}
		else {
		 return weack;	
		}
		
		
	}
}
