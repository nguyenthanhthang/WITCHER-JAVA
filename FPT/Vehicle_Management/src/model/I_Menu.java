/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public interface I_Menu {
    
    //addNewOptio
    void addNewOption(String newOption);
    //print: int danh sách menu(các option)
    void print();
     //method getChoice
    public int getChoice();
}
