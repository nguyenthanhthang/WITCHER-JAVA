package model;

/**
 *
 * @author Admin
 */
public interface I_VehicleList {

    int findById(String code);
    // add new element( input from scanner) to I_List

    void add();
    //Checking exits Vehicle.

    int checkingExits();
    // input the code want to update, after that update other information--> use set method

    void update();
    // Input the code wanna remove

    boolean remove();
    //

    void searchByName();
    //

    void searchById();
    //

    void displayAll();
    //

    void displayAllByPrice();
    //

    void saveToFile(String path);
    void loadToFile(String path);
    //

    void printAll();
    //

    void printAllByType();
}
