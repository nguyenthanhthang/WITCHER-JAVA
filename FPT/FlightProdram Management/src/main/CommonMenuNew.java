package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controlls.FlightManager;
import controlls.Utilities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Hp
 */


/*
Develop a Flight Management System with the following key features:
1. Flight schedule management
2. Passenger reservation and booking
3. Passenger check-in and seat allocation
4. Crew management and assignments
5. Administrator access for system management
6. Data storage for flight details, reservations, and assignments
Others- Quit.
*/
public class CommonMenuNew {

    public static void displayCommonMenu() throws IOException, FileNotFoundException, ClassNotFoundException {
        int luaChon;
        FlightManager flights = new FlightManager();
        Utilities ulti = new Utilities();

           


        do {
            System.out.println("");
            System.out.println("============================= MAIN*MENU ================================");
            System.out.println("     1. Flight schedule management                                      ");
            System.out.println("     2. Passenger reservation and booking                               ");
            System.out.println("     3. Passenger check-in and seat allocation                          ");
            System.out.println("     4. Crew management and assignments                                 ");
            System.out.println("     5. Administrator access for system management                      ");
            System.out.println("     6. Data storage for flight details, reservations, and assignments  ");
            System.out.println("     7. Prinf All Passenger ");
            System.out.println("     8. Quit                                                            ");

            Scanner sc = new Scanner(System.in);
            luaChon = Utilities.getInt("Enter your choice: ", 0, 10); //  

            if (luaChon == 1) {
                System.out.println("");
                System.out.println("Add a new Flight's information: ");
                flights.add();
                displayCommonMenu();
            }

            if (luaChon == 2) {
                System.out.println("");
                System.out.println("Search flights based on: ");
                String go = ulti.getString("Enter Departure  city: ", 1, 0, 100);
                String to = ulti.getString("Enter Destination city: ", 1, 0, 100);
                String start = ulti.inputDate("Enter Departure time: ", 0);
                flights.bookingFlight(go, to, start, 1);
                displayCommonMenu();
            }
            if (luaChon == 3) {
                System.out.println("");
                  flights.checkinSeat();
//                car.update();
                displayCommonMenu();
            }
            if (luaChon == 4) {
                System.out.println("<---------------Crew management and assignments : ----------------->");
                flights.crewAssig();
                System.out.println("Những chuyến bay đã có phi hành đoàn");
                flights.displayCrew();
//                car.delete();
                displayCommonMenu();
            }

            if (luaChon == 5) {
                System.out.println("<-----------Administrator access for system management----------->");
                String s = "";
                String content = "";

                do {
                    s = ulti.getString("Do you want to manage flight schedule or crew assignments? (Enter 'flight' or 'crew' only!) ", 1, 0, 100);

                    if (s.equalsIgnoreCase("flight")) {
                        content = ulti.getString("Enter Flight's Id ", 1, 0, 100);
                        flights.update(0);
                    }
                    if (s.equalsIgnoreCase("crew")) {
                        content = ulti.getString("Enter Flight's Id ", 1, 0, 100);
                        flights.update(1);
                    }
                } while (!s.equalsIgnoreCase("flight") && !s.equalsIgnoreCase("crew"));

                displayCommonMenu();
            }

            if (luaChon == 6) {
                System.out.println("");
                System.out.println("Display all data: ");
                String s = "";
                        System.out.println("Display Flights by date descending: ");
                        flights.displayDescendingByDate();
                displayCommonMenu();
                
            }
            if (luaChon == 7) {
                flights.prind();
            }
           
            if (luaChon == 8) {
                System.out.println("Good bye!!!");
                break;
            }
        } while (luaChon == 8);
    }
}
