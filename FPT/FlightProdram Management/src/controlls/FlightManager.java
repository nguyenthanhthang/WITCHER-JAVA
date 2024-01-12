/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import models.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 *
 */
public class FlightManager {

    private Utilities ulti = new Utilities();

    List<Flight> flightList = new ArrayList<>(); // List các phương tiện
    List<Passenger> ptList = new ArrayList<>(); // List các phương tiện
    HashMap<String, Crew> crewMap = new HashMap<>();

    // Hàm thêm vào 1 phương tiện , ở đây gọi tạm là add Car 
    /*
     private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;
     */
    public void add() throws IOException {
        int getchoice = 9; // biến này để làm cái thăm dò xem có muốn nhập tiêp car khác ko , nếu nhập 1 thì có , nhập 0 thì thoát ra menu chính luôn

        do {
            String ID = "";
            do {
                ID = ulti.getString("Enter flightNumber (format: Fxxxx) : ", 1, 0, 100);
                // Check trùng và định dạng cho mỗi ID
                if (checkDuplicate(ID, 1)) {
                    System.out.println("FlightNumber cannot be duplicate. Please enter another one !");
                }
                if (!checkVehicleID(ID)) {
                    System.out.println("flightNumber phai co dang Fxxxx (0 <= x < 10) !");
                }
            } while (checkDuplicate(ID, 1) || !checkVehicleID(ID)); // ID phải là duy nhất và đúng định dạng Cxxx

            String departureCity = ulti.getString("Enter departureCity: ", 1, 0, 100);
            String destinationCity = ulti.getString("Enter destinationCity: ", 1, 0, 100);
            String departureTime = ulti.inputDate("Enter departureTime: ", 0);
            String arrivalTime = ulti.inputDate("Enter arrivalTime: ", 0);
            int availableSeats = ulti.getInt("Enter availableSeats ", 0, 2000000);

            Flight f = new Flight(ID, departureCity, destinationCity, departureTime, arrivalTime, availableSeats);
            flightList = loadFlights();// Load data from file before add new a car
            flightList.add(f);
            SaveProfiles(flightList); // Save car to file vehicle.txt 

            getchoice = ulti.getInt("Do you want to add more Flight ? (yes: 1 , no: 0)", 0, 2);
            if (getchoice == 0) {
                System.out.println("----------Back to main menu!!!-------------");
                break;
            }
        } while (getchoice != 0); // Do while o day de kiem tra xem co con muon add them xe ko , neu nhap 1 thi lam tiep , neu nhap 0 thi thoi ^^

    }

    // Ham CHECK id của xe xem có tồn tại hay ko , nếu có thì in thôg tin ra
    public void checkExists(String s, int opt) {
        if (opt == 1) {
            if (checkDuplicate(s, 1) == true) {
                System.out.println("Found!!!");
                for (Flight car : flightList) {

                    if (car.getFlightNumber().equalsIgnoreCase(s)) {
                        System.out.println(car.toString());
                    }
                }
            } else {
                System.out.println("No Flight Found!");

            }
        }

    }

    // Ham tìm kiếm xe xem có tồn tại hay ko , nếu có thì in thôg tin ra
    public void bookingFlight(String go, String to, String start, int opt) throws IOException {
        boolean found = false;
        int count = 0;
        String fID = "";
        if (opt == 1) {
            flightList = loadFlights();
            System.out.println("Available flights based on your output: ");
            for (Flight f : flightList) {
                if (f.getDepartureCity().equalsIgnoreCase(go) && f.getDestinationCity().equalsIgnoreCase(to) && f.getDepartureTime().equalsIgnoreCase(start)) {
                    count++;
                    fID = f.getFlightNumber();
                    System.out.println("---------------------------");
                    System.out.println(count + ". " + f.toString());
                    System.out.println("---------------------------");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No Flight Found!!!");
                return;
            }

            String s = "";
            do {
                s = ulti.getString("Do you want to reservations flight: (yes/no) ", 1, 0, 100000);
            } while (!s.equalsIgnoreCase("yes") && !s.equalsIgnoreCase("no"));

            if (s.equalsIgnoreCase("no")) {
                return;
            }
            //        return  PassengerID + ", " + name + ", " + phone + ", " + age + ", " + address + ", " + FlightID + ", " + Seats ;

            System.out.println("<-------------Passenger Reservations and Booking: -------------->");
            System.out.println("Please provide your information: ");

            String ID = "";

            do {
                ID = ulti.getString("Enter Flight ID that you chosen (format: Fxxxx) : ", 1, 0, 100); // hoi xem muon di chuyen bay nao , truong hop co nhieu hon 1 chuyen bay dc in ra
                // Check trùng và định dạng cho mỗi ID
                if (!checkDuplicate(ID, 1)) {
                    System.out.println("Flight ID does not exist. Please enter another one !");
                }
                if (!checkVehicleID(ID)) {
                    System.out.println("Flight ID phai co dang Fxxxx (0 <= x < 10) !");
                }
            } while (!checkDuplicate(ID, 1) || !checkVehicleID(ID)); // ID phải là duy nhất và đúng định dạng Cxxx

            String id = generatePassengerID();
            System.out.println("Your PassengerID is " + id + ": ");
            String name = ulti.getString("Enter your name: ", 1, 0, 100);
            String phone = ulti.getPhone("Enter your phone number: (9 numbers)");
            int age = ulti.getInt("Enter your age: ", 0, 2000000);

            String address = ulti.getString("Enter your address: ", 1, 0, 100);
            String FlightID = ID;
            String seats = "null";
            Passenger p = new Passenger(id, name, phone, age, address, FlightID, seats);
            ptList = loadPassengerFromFile();
            ptList.add(p);
            SavePassenger(ptList);

        }

    }

    // đặt chỗ
    public void checkinSeat() {
        System.out.println("Check-in: ");
        System.out.println("Please provide your Passenger ID: ");
        String ID = "";
        do {
            ID = ulti.getString("Enter PassID (format: Pxxxxxxx) : ", 1, 0, 100);
            // Check trùng và định dạng cho mỗi ID
            if (!checkDuplicate1(ID, 1)) {
                System.out.println("Car's ID cannot be duplicate. Please enter another one !");
            }
            if (!checkVehicleID1(ID)) {
                System.out.println("flightNumber phai co dang Fxxxx (0 <= x < 10) !");
            }
        } while (!checkDuplicate1(ID, 1) || !checkVehicleID1(ID)); // ID phải là duy nhất và đúng định dạng Cxxx

        flightList = loadFlights();
        ptList = loadPassengerFromFile();
        String s = "";

        for (Passenger passenger : ptList) {
            for (Flight flight : flightList) {
                if (passenger.getPassengerID().equalsIgnoreCase(ID) && passenger.getFlightID().equalsIgnoreCase(flight.getFlightNumber())) {
                    System.out.println(ID + " booked: " + flight.toString());
                    s = flight.getFlightNumber();
                }
            }
        }
        // in ra danh sách
        List<Seats> sList = loadRegistration();
        for (Seats seats : sList) {
            if (s.equalsIgnoreCase(seats.getFlightNumber())) {
                seats.print();

            }
        }
        String choice = "";
        do {
            choice = ulti.getString("Please Chon cho ngồi có còn trống: ", 1, 0, 1000);
        } while (!checkSeat(choice));

        Passenger p = null;
        Passenger a = null;
        ptList = loadPassengerFromFile();
        for (Passenger passenger : ptList) {
            if (passenger.getPassengerID().equalsIgnoreCase(ID)) {
                p = passenger;
                a = passenger;
                p.setSeats(choice);

                System.out.println("Chúc mừng! Bạn đã đặt chỗ thành công. Cảm ơn Quý khách!!!");
            }
        }
        //Ghế nào đã đặt thì chuyển thành Sold
        Seats.choseYourSeat(choice, s);

        ptList.remove(a);
        ptList.add(p);
        SavePassenger(ptList);

    }

    // in ra 1 table 
//    public void listAllCars(List<Crew> list) {
////        System.out.println("-------------------------------------------------------------------------------------------------------------------");
////
////        System.out.println("########################################################################################################################");
////        System.out.println("#              ID#            Name#           Color#           Price#           Brand#            Type#            Year#");
////        System.out.println("########################################################################################################################");
////
////        for (Car car : list) {
////            String id = String.format("%15s", car.getId());
////            String name = String.format("%15s", car.getName());
////            String color = String.format("%15s", car.getColor());
////            String price = String.format("%15.2f", car.getPrice()); // Right-align the price column
////            String brand = String.format("%15s", car.getBrand());
////            String type = String.format("%15s", car.getType());
////            String year = String.format("%15s", car.getYear());
////
////            System.out.println("#" + id + " #" + name + " #" + color + " #" + price + " $#" + brand + " #" + type + " #" + year + " #");
////        }
////
////        System.out.println("########################################################################################################################");
////        System.out.println("#                                                                                             TOTAL: " + list.size() + " vehicle type[s]#");
////        System.out.println("########################################################################################################################");
////
////        System.out.println("");
////        System.out.println("");
//    }

    // Ham save vao file 
    public void savefile() {
        flightList = loadFlights();
        SaveProfiles(flightList);
    }

    public void SaveProfiles(List<Flight> carList1) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/output/flight.txt"))) {
            for (Flight car : carList1) {
                writer.write(car.toString());
                writer.newLine(); // Thêm một dòng trống sau mỗi xe
            }
            System.out.println("Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save flight list.");
        }
    }

    public void SavePassenger(List<Passenger> carList1) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/output/passenger.txt"))) {
            for (Passenger car : carList1) {
                writer.write(car.toString());
                writer.newLine(); // Thêm một dòng trống sau mỗi xe
            }
            System.out.println("Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save list.");
        }
    }

    public static void SaveSeats(List<Seats> carList1) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/output/seat.txt"))) {
            for (Seats car : carList1) {
                writer.write(car.toString());
                writer.newLine(); // Thêm một dòng trống sau mỗi xe
            }
            System.out.println("Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save flight list.");
        }
    }

    // Ham load data tu file
    public List<Flight> loadFlights() {
        List<Flight> fList = new ArrayList<>(); // 
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + "\\src\\output\\flight.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin của xe từ dòng đọc được
                String[] carInfo = line.split(",");
                // Kiểm tra xem dòng có đúng định dạng không
                String ID = carInfo[0].trim();
                String go = carInfo[1].trim();
                String to = carInfo[2].trim();
                String start = carInfo[3].trim();
                String end = carInfo[4].trim();
                int seats = Integer.parseInt(carInfo[5].trim());

                // Tạo đối tượng Car và thêm vào danh sách
                Flight f = new Flight(ID, go, to, start, end, seats);
                fList.add(f);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load car list.");
        }
        return fList;
    }

    public List<Passenger> loadPassengerFromFile() {
        List<Passenger> fList = new ArrayList<>(); // 
        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + "\\src\\output\\passenger.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin của xe từ dòng đọc được
                String[] carInfo = line.split(",");
                // Kiểm tra xem dòng có đúng định dạng không
                String ID = carInfo[0].trim();
                String name = carInfo[1].trim();
                String phone = carInfo[2].trim();
                int age = Integer.parseInt(carInfo[3].trim());

                String address = carInfo[4].trim();
                String FlightID = carInfo[5].trim();
                String seats = carInfo[6].trim();

                // Tạo đối tượng Car và thêm vào danh sách
                Passenger p = new Passenger(ID, name, phone, age, address, FlightID, seats);
                fList.add(p);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load car list.");
        }
        return fList;
    }

    public static List<Seats> loadRegistration() {
        List<Seats> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\output\\seat.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String parts[] = line.split(",");
                String id = parts[0].trim();
                String name = parts[1].trim();
                String major = parts[2].trim();
                String email = parts[3].trim();
                String phone = parts[4].trim();
                String passport = parts[5].trim();
                String address1 = parts[6].trim();
                String address2 = parts[7].trim();
                String address3 = parts[8].trim();
                String address4 = parts[9].trim();
                String address5 = parts[10].trim();
                String address6 = parts[11].trim();
                String address7 = parts[12].trim();
                String address8 = parts[13].trim();
                String address9 = parts[14].trim();
                String address10 = parts[15].trim();
                String address11 = parts[16].trim();
                String address12 = parts[17].trim();
                String address13 = parts[18].trim();
                String address14 = parts[19].trim();
                String address15 = parts[20].trim();
                String address16 = parts[21].trim();
                String address17 = parts[22].trim();
                String address18 = parts[23].trim();
                String address19 = parts[24].trim();

                Seats s = new Seats(id, name, major, email, phone, passport, address1, address2, address3, address4, address5, address6, address7, address8, address9, address10, address11, address12, address13, address14, address15, address16, address17, address18, address19);

                list.add(s);

            }
            br.close();
        } catch (Exception e) {
        }
        return list;
    }

    // Hàm kiểm tra xem id nhập vào có trùng với id của xe khác đã tồn tại trogn file ko . nếu trùng thì hàm trả về true => bắt nhập id khác ^^
    public boolean checkDuplicate(String s, int opt) {
        flightList = loadFlights();

        if (opt == 1) {
            flightList = loadFlights();
            for (Flight c : flightList) {  // check car is exits or not
                if (c.getFlightNumber().equalsIgnoreCase(s)) {
                    return true;
                }
            }
        }
        if (opt == 2) {
//            flightList = loadCarListFromFile();
//            for (Car c : flightList) {  // check car is exits or not
//                if (c.getName().toLowerCase().contains(s.toLowerCase())) {
//                    return true;
//                }
//            }
        }

        return false;
    }

    public static boolean checkVehicleID(String str) { // check format Cxxx cho ID cua xe
        if (str.length() != 5) {
            return false;
        }

        if (str.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkSeat(String str) { // check format Cxxx cho ID cua xe
        if (str.length() != 2) {
            return false;
        }

        if (str.charAt(0) != 'A' && str.charAt(0) != 'B' && str.charAt(0) != 'C' && str.charAt(0) != 'D' && str.charAt(0) != 'E' && str.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean checkDuplicate1(String s, int opt) {
        ptList = loadPassengerFromFile();
        if (opt == 1) {
            for (Passenger c : ptList) {  // check car is exits or not
                if (c.getPassengerID().equalsIgnoreCase(s)) {
                    return true;
                }
            }
        }
        if (opt == 2) {

        }

        return false;
    }

    public static boolean checkVehicleID1(String str) {
        if (str.length() != 7) {
            return false;
        }

        if (str.charAt(0) != 'P') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public String generatePassengerID() throws IOException {
        List<Passenger> orders = loadPassengerFromFile();
        int orderCount = orders.size();
        return "P" + String.format("%06d", orderCount);
    }

    public void crewAssig() {
        System.out.println("Please provide Flight ID: ");

        String ID = "";

        do {
            ID = ulti.getString("Enter Flight ID that you chosen (format: Fxxxx) : ", 1, 0, 100); // hoi xem muon di chuyen bay nao , truong hop co nhieu hon 1 chuyen bay dc in ra
            // Check trùng và định dạng cho mỗi ID
            if (!checkDuplicate(ID, 1)) {
                System.out.println("Flight ID does not exist. Please enter another one !");
            }
            if (!checkVehicleID(ID)) {
                System.out.println("Flight ID phai co dang Fxxxx (0 <= x < 10) !");
            }
        } while (!checkDuplicate(ID, 1) || !checkVehicleID(ID)); // ID phải là duy nhất và đúng định dạng Cxxx

        String captain = ulti.getString("Enter captain: ", 1, 0, 1000);
        String firstOfficer = ulti.getString("Enter firstOfficer: ", 1, 0, 1000);
        String flightAttendant1 = ulti.getString("Enter flightAttendant1: ", 1, 0, 1000);
        String flightAttendant2 = ulti.getString("Enter flightAttendant2: ", 1, 0, 1000);
        String flightAttendant3 = ulti.getString("Enter flightAttendant3: ", 1, 0, 1000);

        Crew c = new Crew(ID, captain, firstOfficer, flightAttendant1, flightAttendant2, flightAttendant3);
        crewMap = loadCrew();
        crewMap.put(ID, c);
        SaveCrew(crewMap);

    }

    public static void SaveCrew(HashMap<String, Crew> crewMap) {
        ArrayList<Crew> productList = new ArrayList<>(crewMap.values());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/output/crew.txt"))) {
            for (Crew car : productList) {
                writer.write(car.toString());
                writer.newLine(); // Thêm một dòng trống sau mỗi xe
            }
            System.out.println("Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save flight list.");
        }
    }

    // Ham load data tu file
    public HashMap<String, Crew> loadCrew() {
        HashMap<String, Crew> crewMap = new HashMap<>();

        String currentDirectory = System.getProperty("user.dir");
        String filePath = currentDirectory + "\\src\\output\\crew.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Tách thông tin của xe từ dòng đọc được
                String[] carInfo = line.split(",");
                // Kiểm tra xem dòng có đúng định dạng không
                String ID = carInfo[0].trim();
                String go = carInfo[1].trim();
                String go1 = carInfo[2].trim();
                String to = carInfo[3].trim();
                String start = carInfo[4].trim();
                String end = carInfo[5].trim();

                // Tạo đối tượng Car và thêm vào danh sách
                Crew c = new Crew(ID, go, go1, to, start, end);
                crewMap.put(ID, c);

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load car list.");
        }
        return crewMap;
    }

    public void displayCrew() {
        HashMap<String, Crew> crewMap = loadCrew();
        for (Crew value : crewMap.values()) {
            System.out.println(value.toString());
        }

    }

    public void update(int opt) {

        if (opt == 0) {
            update();
            System.out.println("");
        }

        if (opt == 1) {
            update1();
            System.out.println("");
        }
    }

    public void update() {
        int choice;
        String id;
        do {
            id = ulti.getString("Enter Flight's ID", 1, 0, 100);
            if (checkDuplicate(id, 1) == false) {
                System.out.println("Flight ID does not exist!!!");

            }
        } while (checkDuplicate(id, 1) == false);// 
        flightList = loadFlights();
        Flight car = null;
        for (Flight c : flightList) {
            if (c.getFlightNumber().equalsIgnoreCase(id)) {   // chay vong for each tim xe nao khop voi id 
                car = c;  // gan xe c vao xe car
            }
        }
        do {
            Menu menu = new Menu("---------------Update Vehicle's information menu--------------");
            menu.addNewOptiont("1. Update Flight's departureCity");
            menu.addNewOptiont("2. Update Flight's destinationCity");
            menu.addNewOptiont("3. Update Flight's departureTime");
            menu.addNewOptiont("4. Update Flight's arrivalTime");
            menu.addNewOptiont("5. Quit");
            menu.printMenu();

            choice = menu.getChoice();

            if (choice == 1) {
                String name = ulti.getString("Enter Flight's departureCity: ", 1, 0, 100);
                car.setDepartureCity(name);
                System.out.println("Successs !!!");
            }
            if (choice == 2) {
                String color;
                color = ulti.getString("Enter vehicle's destinationCity: ", 1, 0, 100);
                car.setDestinationCity(color);
                System.out.println("Successs !!!");
            }

            if (choice == 3) {
                String brand = ulti.getString("Enter new departureTime", 1, 1, 1000);
                car.setDepartureTime(brand);
                System.out.println("Successs !!!");
            }
            if (choice == 4) {
                String brand = ulti.getString("Enter new arrivalTime", 1, 1, 1000);
                car.setArrivalTime(brand);
                System.out.println("Successs !!!");
            }

            if (choice == 5) {
                System.out.println("Good bye!!!");
                break;
            }

        } while (choice != 6);

        System.out.println("---------Information after update!!!----------");
        System.out.println("|  " + car.toString() + "  |");
        flightList.add(car);
        SaveProfiles(flightList); // luu du lieu moi vao file

    }

    public void update1() {
        int choice;
        String id;
        do {
            id = ulti.getString("Enter Flight's ID", 1, 0, 100);
            if (checkDuplicate(id, 1) == false) {
                System.out.println("Flight ID does not exist!!!");

            }
        } while (checkDuplicate(id, 1) == false);// 
        crewMap = loadCrew();
        Crew car = null;
        for (Crew c : crewMap.values()) {
            if (c.getFlightID().equalsIgnoreCase(id)) {   // chay vong for each tim xe nao khop voi id 
                car = c;  // gan xe c vao xe car
            }
        }
        do {

            Menu menu = new Menu("---------------Update Vehicle's information menu--------------");
            menu.addNewOptiont("1. Update Flight's captain");
            menu.addNewOptiont("2. Update Flight's firstOfficer");
            menu.addNewOptiont("3. Update Flight's flightAttendant1");
            menu.addNewOptiont("4. Update Flight's flightAttendant2");
            menu.addNewOptiont("5. Update Flight's flightAttendant3");
            menu.addNewOptiont("6. Quit");
            menu.printMenu();

            choice = menu.getChoice();

            if (choice == 1) {
                String name = ulti.getString("Enter Flight's captain: ", 1, 0, 100);
                car.setCaptain(name);
                System.out.println("Successs !!!");
            }
            if (choice == 2) {
                String color;
                color = ulti.getString("Enter vehicle's firstOfficer: ", 1, 0, 100);
                car.setFirstOfficer(color);
                System.out.println("Successs !!!");
            }

            if (choice == 3) {
                String brand = ulti.getString("Enter new flightAttendant1", 1, 1, 1000);
                car.setFlightAttendant1(brand);
                System.out.println("Successs !!!");
            }
            if (choice == 4) {
                String brand = ulti.getString("Enter new flightAttendant2", 1, 1, 1000);
                car.setFlightAttendant2(brand);
                System.out.println("Successs !!!");
            }

            if (choice == 5) {
                String brand = ulti.getString("Enter new flightAttendant3", 1, 1, 1000);
                car.setFlightAttendant3(brand);
                System.out.println("Successs !!!");
            }
            if (choice == 6) {
                System.out.println("Good bye!!!");
                break;
            }
        } while (choice != 7);

        System.out.println("---------Information after update!!!----------");
        System.out.println("|  " + car.toString() + "  |");
        crewMap.put(id, car);
        SaveCrew(crewMap); // luu du lieu moi vao file

    }

    public void displayDescendingByDate() {
        List<String> flightData = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src//output//flight.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                flightData.add(line);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sắp xếp danh sách theo ngày giảm dần
        Collections.sort(flightData, new Comparator<String>() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            @Override
            public int compare(String flight1, String flight2) {
                try {
                    Date date1 = stringToDate((flight1.split(",")[3].trim()));
                    Date date2 = stringToDate((flight2.split(",")[3].trim()));

                    // Sắp xếp giảm dần theo ngày, nếu muốn tăng dần thì đổi thành date2.compareTo(date1)
                    return date2.compareTo(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        // In ra danh sách đã sắp xếp
        for (String flight : flightData) {
            System.out.println(flight);
        }
    }

    public void prind(){
        ptList = loadPassengerFromFile();
        for (Passenger passenger : ptList) {
            System.out.println(passenger);
        }
    }
    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.parse(dateString);
    }
}
