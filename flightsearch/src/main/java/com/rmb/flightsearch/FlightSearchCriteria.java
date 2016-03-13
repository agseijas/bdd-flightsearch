package com.rmb.flightsearch;

import java.time.ZonedDateTime;

public class FlightSearchCriteria {
	
	private String originIATA;

	private String destinationIATA;
	
	private ZonedDateTime departureDate;
	
	private int adults;
	private int children;
	private int infants;

	public String getOriginIATA(){
		return this.originIATA;
	}

	public void setOriginIATA(String originIATA) {
		this.originIATA = originIATA;
	}
	
	public String getDestinationIATA() {
		return destinationIATA;
	}

	public void setDestinationIATA(String destinationIATA) {
		this.destinationIATA = destinationIATA;
	}

	public ZonedDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(ZonedDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getInfants() {
		return infants;
	}

	public void setInfants(int infants) {
		this.infants = infants;
	}

}
