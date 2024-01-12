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
public class Passenger {
    private String PassengerID;
    private String name;
    private String phone;
    private int age;
    private String address;
    private String FlightID;
    private String Seats;

    public Passenger(String PassengerID, String name, String phone, int age, String address, String FlightID, String Seats) {
        this.PassengerID = PassengerID;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.address = address;
        this.FlightID = FlightID;
        this.Seats = Seats;
    }

    public String getPassengerID() {
        return PassengerID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getFlightID() {
        return FlightID;
    }

    public String getSeats() {
        return Seats;
    }

    public void setPassengerID(String PassengerID) {
        this.PassengerID = PassengerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFlightID(String FlightID) {
        this.FlightID = FlightID;
    }

    public void setSeats(String Seats) {
        this.Seats = Seats;
    }

    @Override
    public String toString() {
        return  PassengerID + ", " + name + ", " + phone + ", " + age + ", " + address + ", " + FlightID + ", " + Seats ;
    }
    
    
    
}
