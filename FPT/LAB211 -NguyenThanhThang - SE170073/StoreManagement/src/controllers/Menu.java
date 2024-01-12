package controllers;

import java.util.ArrayList;
import dto.I_Menu;
import utils.Utils;

public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        this.forEach((s) -> {
            System.out.println(s);
        });
        System.out.println("");
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }

    @Override
    public int getChoice() {
        String welcome = "Enter your choice [1.." + this.size() + "]: ";
        int choice = Utils.getInt(welcome, 1 , this.size());
        return choice;
    }

}
