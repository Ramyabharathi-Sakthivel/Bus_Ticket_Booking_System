package com.wipro.bbs.service;

import java.util.ArrayList;
import com.wipro.bbs.entity.Booking;
import com.wipro.bbs.entity.Bus;
import com.wipro.bbs.entity.Passenger;
import com.wipro.bbs.util.BookingNotFoundException;
import com.wipro.bbs.util.BookingOperationException;
import com.wipro.bbs.util.BusFullException;
import com.wipro.bbs.util.InvalidPassengerException;

public class BookingService {

	private ArrayList<Passenger> passenger;
	private ArrayList<Bus> bus;
	private ArrayList<Booking> booking;

	public BookingService(ArrayList<Passenger> passenger, ArrayList<Bus> bus, ArrayList<Booking> booking) {
		this.passenger = passenger;
		this.bus = bus;
		this.booking = booking;
	}

	public boolean validatePassenger(String passengerId) throws InvalidPassengerException {
		for (Passenger p : passenger) {
			if (p.getPassengerId().equals(passengerId))
				return true;
		}
		throw new InvalidPassengerException();
	}

	public Bus findBus(String busId) throws BookingOperationException {
		for (Bus b : bus) {
			if (b.getBusId().equals(busId))
				return b;
		}
		throw new BookingOperationException();
	}

	public Booking bookTicket(String passengerId, String busId, String travelDate) throws Exception {
		
		validatePassenger(passengerId);
		
		Bus bus = findBus(busId);
		if (bus.getAvailableSeats() <= 0)
			throw new BusFullException();
		
		double fare = calculateFare(bus);
		
		String bookingId = "Booking" +(booking.size() + 1);
		
		bus.setAvailableSeats(bus.getAvailableSeats() - 1);
		
		Booking booking = new Booking(bookingId, passengerId, busId, travelDate, fare);
		this.booking.add(booking);
		return booking;
	}

	public double calculateFare(Bus bus) {
		double fare = bus.getBaseFare();
		if (bus.getBusType().equalsIgnoreCase("AC"))
			fare *= 2.5;
		else if (bus.getBusType().equalsIgnoreCase("Sleeper"))
			fare *= 3.5;
		return fare;
	}

	public void cancelBooking(String bookingId) throws BookingNotFoundException {
		for (Booking b : booking) {
			if (b.getBookingId().equals(bookingId)) {
				for (Bus bs : bus) {
					if (bs.getBusId().equals(b.getBusId())) {
						bs.setAvailableSeats(bs.getAvailableSeats() + 1);
						break;
					}
				}
				booking.remove(b);
				return;
			}
		}
		throw new BookingNotFoundException();
	}

	public void printPassengerBookings(String passengerId) {
		for (Booking b : booking) {
			if (b.getPassengerId().equals(passengerId)) {
				System.out.println("Booking ID: " + b.getBookingId() +" Bus: " + b.getBusId() +" Date: " + b.getTravelDate() +" Fare: " + b.getFare());
			}
		}
	}
}
