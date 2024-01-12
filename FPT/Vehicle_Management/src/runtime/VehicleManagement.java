/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtime;

import controllers.Menu;
import controllers.VehicleList;

/**
 *
 * @author Admin
 */
public class VehicleManagement {

    public static void main(String[] args) {
        final String vehicleFilePath = "src\\data\\vehicle.dat";
        VehicleList vehicle = new VehicleList();
        vehicle.loadToFile(vehicleFilePath);
        //Dựng menu
        Menu menu = new Menu("Menu");
        menu.addNewOption("Adding new vehicle");
        menu.addNewOption("Checking exits Vehicle");
        menu.addNewOption("Updating vehicle");
        menu.addNewOption("Deleting vehicle.");
        menu.addNewOption("Searching vehicle");
        menu.addNewOption("Displaying vehicle list.");
        menu.addNewOption("Saving Vehicle to file");
        menu.addNewOption("Printing list Vehicles the file");
        menu.addNewOption("Exit.");
        int choice;
        while (true) {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    vehicle.add();
                    break;
                }
                case 2: {
                    vehicle.checkingExits();
                    break;
                }
                case 3: {
                    vehicle.update();
                    break;
                }
                case 4: {
                    vehicle.remove();
                    break;
                }
                case 5: {
                    System.out.println("1.Searching by id.");
                    System.out.println("2.Searching by name");
                    int choiceS;
                    while (true) {
                        choiceS = menu.getChoice();
                        switch (choiceS) {
                            case 1: {
                                vehicle.searchById();
                                break;
                            }
                            case 2: {
                                vehicle.searchByName();
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
                case 6: {
                    System.out.println("1.Displaying all");
                    System.out.println("2.Displaying all (descending by price)");
                    int choiceD;
                    while (true) {
                        choiceD = menu.getChoice();
                        switch (choiceD) {
                            case 1: {
                                vehicle.displayAll();
                                break;
                            }
                            case 2: {
                                vehicle.displayAllByPrice();
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
                case 7: {
                    vehicle.saveToFile(vehicleFilePath);
                    break;
                }
                case 8: {
                    System.out.println("1.Printing list Vehicles the file. ");
                    System.out.println("2.Printing list Vehicles the file (descending by price) following Type");
                    int choiceD;
                    while (true) {
                        choiceD = menu.getChoice();
                        switch (choiceD) {
                            case 1: {
                                vehicle.printAll();
                                break;
                            }
                            case 2: {
                                vehicle.printAllByType();
                                break;
                            }
                        }
                        break;
                    }
                    break;
                }
                case 9: {
                    System.out.println("Bye!");
                    return;
                }
            }
        }
    }

}
