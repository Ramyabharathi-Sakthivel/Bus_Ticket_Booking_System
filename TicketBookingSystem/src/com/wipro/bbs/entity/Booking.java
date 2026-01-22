package com.wipro.bbs.entity;

public class Booking {
	
	private String bookingId;
	private String passengerId;
	private String busId;
	private String travelDate;
	private double fare;
	
	public Booking(String bookingId, String passengerId, String busId, String travelDate, double fare) {
		super();
		this.bookingId = bookingId;
		this.passengerId = passengerId;
		this.busId = busId;
		this.travelDate = travelDate;
		this.fare = fare;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	
}
