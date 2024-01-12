/*
Menu: là 1 cái khuôn chuyên đúc ra 1 anh quản lý, chuyên quản lý các option
các lựa chọn, này này có 1 cái mảng chứa các option
method để addNewOption
method để show ra Menu (show ra các option)
method lấy lwak chọn của em
*/
package iu;

import java.util.ArrayList;
import tool.Inputter;

public class Menu {
    //mảng lưu các chuỗi lựa chọn
    public ArrayList<String> optionList = new ArrayList<>();
    public String title;
    
    //constructor
    public Menu(String title) {
        this.title = title;
    }
    
    //addNewOption
    public void addNewOption(String newOption){
        optionList.add(newOption);
    }
    
    //print: int danh sách menu(các option)
    public void print(){
        int count =1;
        System.out.println("---------"+title+"----------");
        for (String item : optionList) {
            System.out.println(count + ". "+ item);
            count++;
        }
    }
    //method getChoice
    public int getChoice(){
        int choice = Inputter.getAnInteger("Input your choice:",
                  "your choice must between 1 and "+ optionList.size(),
                  1, optionList.size());
        return choice;
    }
    
}
