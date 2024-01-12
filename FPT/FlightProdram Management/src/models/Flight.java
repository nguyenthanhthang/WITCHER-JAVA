/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Hp
 */
public class Flight {
    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        // Kiểm tra chuỗi số hiệu chuyến bay có đúng định dạng Fxyzt không
        if (flightNumber.matches("F\\d{4}")) {
            this.flightNumber = flightNumber;
        } else {
            throw new IllegalArgumentException("Invalid flight number format. It should be like F0001.");
        }
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
     
      public String toDisplay() {
        return "Flight{" + "flightNumber=" + flightNumber + ", departureCity=" + departureCity + ", destinationCity=" + destinationCity + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", availableSeats=" + availableSeats + '}';
    }

    @Override
    public String toString() {
        return  flightNumber + ", " + departureCity + ", " + destinationCity + ", " + departureTime + ", " + arrivalTime + ", " + availableSeats ;
    }

    
}

