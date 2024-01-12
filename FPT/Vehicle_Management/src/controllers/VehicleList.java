package controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.I_VehicleList;
import model.Vehicle;
import utils.Inputter;

/**
 *
 * @author Admin
 */
public class VehicleList extends ArrayList<Vehicle> implements I_VehicleList {

//Code form: Pxxx (x is in [0..9]
    public static final String CODE_FORMAT = "[Vv]\\d{3}";

    @Override
    public int findById(String code) {
        for (int i = 0; i <= this.size() - 1; i++) {
            if (this.get(i).getId().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add() {
        System.out.println("Add Vehicle to room: ");
        while (true) {
            int pos;
            String newCode;
            do {
                newCode = Inputter.getString("Input Vehicle code: ",
                        "That field is requied", CODE_FORMAT);
                pos = findById(newCode.substring(0).toUpperCase());
                if (pos < 0) {
                    String newName = Inputter.getString("Input Vehicle Name: ",
                            "That field is requied");
                    String newColor = Inputter.getString("Input Vehicle Color: ",
                            "That field is requied");
                    double newPrice = Inputter.getADouble("Input Vehicle Price: ",
                            "That field is requied", 1.0, 999999999.0);
                    String newBrand = Inputter.getString("Input Vehicle Brand: ",
                            "That field is requied");
                    String newType = Inputter.getString("Input Vehicle Type: ",
                            "That field is requied");
                    int newProductYear = Inputter.getAnInteger("Input Vehicle ProductYear: ",
                            "That field is requied", 1000, 10000);
                    this.add(new Vehicle(newCode.substring(0).toUpperCase(), newName, newColor, newPrice, newBrand, newType, newProductYear));
                    System.out.println("    --> Vehicle " + newCode + " has been added!");
                } else {
                    System.out.println("    - Error: The code was duplicated!");
                }
            } while (pos >= 0);
            if (!Inputter.readBool("Continuous create new Vehicle?")) {
                System.out.println("");
                return;
            }
        }
    }

    @Override
    public int checkingExits() {
        String id = Inputter.getString("Input Products code: ",
                "That field is requied", CODE_FORMAT);
        //check trùng
        for (Vehicle item : this) {
            if (item.getId().equals(id.substring(0).toUpperCase())) {
                System.out.println("Exist Vehicle");
                return -1;
            }
        }
        System.out.println("No Vehicle Found!");
        return 0;
    }

    @Override
    public void update() {
        if (this.isEmpty()) {
            System.out.println("Error: Vehicle list is empty!");
            return;
        }
        int pos;
        String newCode;
        do {
            newCode = Inputter.getString("Input Vehicle code: ",
                    "That field is requied", CODE_FORMAT);
            pos = findById(newCode.substring(0).toUpperCase());
            System.out.println("Vehicle information before updating");
            System.out.println(this.get(pos));
            System.out.println("Uptading...");

            if (pos > 0) {
                String newName = Inputter.getStringO("Input Vehicle Name: ",
                        this.get(pos).getName());

                String newColor = Inputter.getStringO("Input Vehicle Color: ",
                        this.get(pos).getColor());

                double newPrice = Inputter.getDoubleO("Input Vehicle Price: ",
                        1, 999999999, this.get(pos).getPrice());
                String newBrand = Inputter.getString("Input Vehicle Brand: ",
                        this.get(pos).getBrand());

                String newType = Inputter.getStringO("Input Vehicle Type: ",
                        this.get(pos).getType());

                int newProductYear = Inputter.getIntO("Input Vehicle newProductYear: ",
                        1000, 10000, this.get(pos).getProductYear());
                this.get(pos).setName(newName);
                this.get(pos).setColor(newColor);
                this.get(pos).setPrice(newPrice);
                this.get(pos).setBrand(newBrand);
                this.get(pos).setType(newType);
                this.get(pos).setProductYear(newProductYear);
                System.out.println(this.get(pos));
                System.out.println("Updating is successfull");
            } else {
                System.out.println("Vehicle does not exist!!");
            }
        } while (pos < 0);

    }

    @Override
    public boolean remove() {
        String keyId = Inputter.getString("Input Vehicle id you wanna remove",
                "That fied is required");
        //từ cái keyID tìm vị trí 
        int pos = this.findById(keyId.substring(0).toUpperCase());
        if (pos == -1) {
            System.out.println("Not found");
            return false;
        } else {
            System.out.println("Vehicle information before removing");
            System.out.println(this.get(pos));
            boolean isRemove = true;
            isRemove = Inputter.readBool("Are you sure you want to Remove");
            if (isRemove == false) {
                System.out.println("Remove is failed");
            } else {
                this.remove(pos);
                System.out.println("Vehicle remove is successful");
            }
        }
        return true;
    }

    @Override
    public void searchByName() {
        String searchString = Inputter.getString("Input Vehicle Name you wanna find",
                "That filed is required");
        // Convert the search string to lowercase to make the search case-insensitive.
        searchString = searchString.toLowerCase();
        ArrayList<Vehicle> searchResults = new ArrayList<>();
        for (Vehicle item : this) {
            if(item.getName().contains(searchString)){
            searchResults.add(item);
            }
        }
        // Sort the vehicles by Name
        Collections.sort(this, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return v1.getName().compareTo(v2.getName());
            }
        });
        if (searchResults.isEmpty()) {
            System.out.println("Not Found");
        } else {
            System.out.println("Vehicle information");
            for (Vehicle vehicle : searchResults) {
                System.out.println(vehicle);
            }
        }
    }

    @Override
    public void searchById() {
        String keyId = Inputter.getString("Input Vehicle id you wanna find",
                "That filed is required", CODE_FORMAT);
        int pos = this.findById(keyId.substring(0).toUpperCase());
        if (pos == -1) {
            System.out.println("Not Found");
        } else {
            System.out.println("Vehicle information");
            System.out.println(this.get(pos));
        }
    }

    @Override
    public void displayAll() {
        if (this.isEmpty()) {
            System.out.println("Vehicle List nothing to display");
            return;//ngừng luôn
        }
        System.out.println("__Vehicle List__");
        for (Vehicle item : this) {
            System.out.println(item);
        }
    }

    @Override
    public void displayAllByPrice() {
        if (this.isEmpty()) {
            System.out.println("Vehicle List nothing to display");
            return;//ngừng luôn
        }
        System.out.println("__Vehicle List__");
        // Sort the vehicles by price
        Collections.sort(this, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return Double.compare(v2.getPrice(), v1.getPrice());
            }
        });

        // Print the sorted list of vehicles
        for (Vehicle item : this) {
            System.out.println(item);
        }
    }

    @Override
    public void printAll() {
        if (this.isEmpty()) {
            System.out.println("Vehicle List nothing to print");
            return;//ngừng luôn
        }
        System.out.println("__Vehicle List__");
        for (Vehicle item : this) {
            System.out.println(item);
        }
    }

    @Override
    public void printAllByType() {
        if (this.isEmpty()) {
            System.out.println("Vehicle List nothing to print");
            return;//ngừng luôn
        }
        System.out.println("__Vehicle List__");
        // Sort the vehicles by price
        Collections.sort(this, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return Double.compare(v2.getPrice(), v1.getPrice());
            }
        });
        // Nhóm danh sách xe theo loại nếu được yêu cầu.

        Map<String, List<Vehicle>> vehicleGroups = new HashMap<>();
        for (Vehicle vehicle : this) {
            String vehicleType = vehicle.getType();
            if (!vehicleGroups.containsKey(vehicleType)) {
                vehicleGroups.put(vehicleType, new ArrayList<>());
            }
            vehicleGroups.get(vehicleType).add(vehicle);
        }

        // In danh sách xe được nhóm theo loại.
        for (String vehicleType : vehicleGroups.keySet()) {
            System.out.println("Loại xe: " + vehicleType);
            for (Vehicle vehicle : vehicleGroups.get(vehicleType)) {
                System.out.println(vehicle);
            }
        }
//        // Print the sorted list of vehicles
//        for (Vehicle item : this) {
//            System.out.println(item);
//        }
    }

    @Override
    public void saveToFile(String path) {
        try {
            FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            if (this.isEmpty()) {
                fo.writeChars("");
            } else {
                for (Vehicle pr : this) {
                    fo.writeObject(pr);
                }
            }
            fo.close();
            f.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void loadToFile(String path) {
        try {
            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Vehicle pr;
            while ((pr = (Vehicle) (fo.readObject())) != null) {
                this.add(pr);
            }
            fo.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
        }
    }

}
