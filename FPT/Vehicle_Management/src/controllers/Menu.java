/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import model.I_Menu;
import utils.Inputter;

/**
 *
 * @author Admin
 */
public class Menu extends ArrayList<String> implements I_Menu {

    public String title;

    public Menu() {
        super();
    }

    //constructor
    public Menu(String title) {
        this.title = title;
    }

    @Override
    public void addNewOption(String newOption) {
        this.add(newOption);
    }

    @Override
    public void print() {
        int count = 1;
        System.out.println("---------" + title + "----------");
        for (String item : this) {
            System.out.println(count + ". " + item);
            count++;
        }
    }

    @Override
    public int getChoice() {
        int choice = Inputter.getAnInteger("Input your choice:",
                "your choice must between 1 and " + this.size(),
                1, this.size());
        return choice;
    }

}
