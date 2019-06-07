package com.delhi.metro.model;

import java.time.LocalDateTime;

import com.delhi.metro.FareStrategy;

public class CardTrx {

	
	 long id;

	    SmartCard card;

	    Station source;
	    Station destination;

	    int distance;

	    LocalDateTime startTime;
	    LocalDateTime endTime;

	    double balance;
	    double fare;

	    FareStrategy fareStrategyUsed;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public SmartCard getCard() {
			return card;
		}

		public void setCard(SmartCard card) {
			this.card = card;
		}

		public Station getSource() {
			return source;
		}

		public void setSource(Station source) {
			this.source = source;
		}

		public Station getDestination() {
			return destination;
		}

		public void setDestination(Station destination) {
			this.destination = destination;
		}

		public int getDistance() {
			return distance;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

		public LocalDateTime getStartTime() {
			return startTime;
		}

		public void setStartTime(LocalDateTime startTime) {
			this.startTime = startTime;
		}

		public LocalDateTime getEndTime() {
			return endTime;
		}

		public void setEndTime(LocalDateTime endTime) {
			this.endTime = endTime;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		public double getFare() {
			return fare;
		}

		public void setFare(double fare) {
			this.fare = fare;
		}

		public FareStrategy getFareStrategyUsed() {
			return fareStrategyUsed;
		}

		public void setFareStrategyUsed(FareStrategy fareStrategyUsed) {
			this.fareStrategyUsed = fareStrategyUsed;
		}

		
	    
	    
	    
	
}
