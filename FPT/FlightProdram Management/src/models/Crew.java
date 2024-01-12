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
public class Crew {
    
    private String FlightID;
    private String captain;
    private String firstOfficer;
    private String flightAttendant1;
    private String flightAttendant2;
    private String flightAttendant3;

    public Crew(String FlightID,String captain, String firstOfficer, String flightAttendant1, String flightAttendant2, String flightAttendant3) {
       this.FlightID=FlightID;
        this.captain = captain;
        this.firstOfficer = firstOfficer;
        this.flightAttendant1 = flightAttendant1;
        this.flightAttendant2 = flightAttendant2;
        this.flightAttendant3 = flightAttendant3;
    }

    public String getFlightID() {
        return FlightID;
    }

    public void setFlightID(String FlightID) {
        this.FlightID = FlightID;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getFirstOfficer() {
        return firstOfficer;
    }

    public void setFirstOfficer(String firstOfficer) {
        this.firstOfficer = firstOfficer;
    }

    public String getFlightAttendant1() {
        return flightAttendant1;
    }

    public void setFlightAttendant1(String flightAttendant1) {
        this.flightAttendant1 = flightAttendant1;
    }

    public String getFlightAttendant2() {
        return flightAttendant2;
    }

    public void setFlightAttendant2(String flightAttendant2) {
        this.flightAttendant2 = flightAttendant2;
    }

    public String getFlightAttendant3() {
        return flightAttendant3;
    }

    public void setFlightAttendant3(String flightAttendant3) {
        this.flightAttendant3 = flightAttendant3;
    }

    @Override
    public String toString() {
        return FlightID+", "+captain + ", " + firstOfficer + ", " + flightAttendant1 + ", " + flightAttendant2 + ", " + flightAttendant3 ;
    }

    
}
