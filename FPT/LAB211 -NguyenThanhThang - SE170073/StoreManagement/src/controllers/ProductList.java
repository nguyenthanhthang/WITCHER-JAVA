package controllers;

import utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import dto.I_List;
import dto.Product;
import java.io.*;
public class ProductList extends ArrayList<Product> implements I_List {
    
    public static final double MIN_PRICE = 0;
    public static final double MAX_PRICE = 1000;
    public static final int MAX_QUANTITY = 1000;
   
    //Code form: Pxxx (x is in [0..9])
    public static final String CODE_FORMAT = "^[Pp]{1}[0-9]{3}$"; 
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String SIZE_FORMAT = "[a-zA-Z0-9]{0,4}";
    
    @Override
    public int find(String code) {
        int pos = -1;
        for (int i=0;i<this.size();i++)
        {
            if (code.equalsIgnoreCase(this.get(i).getCode())) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    @Override
    public void printHeader() {
        String format = " | %4s |         %-13s | %4s | %15s | %15s | %9s | %8s |\n";
        System.out.printf(format,"Code","Name","Size","Production Date", "Expiration date", "Quantity", "Price");
    }

    @Override
    public void printHeaderWithNum() {
        String format = " %3s | %4s |         %-13s | %4s | %15s | %15s | %9s | %8s |\n";
        System.out.printf(format,"Num","Code","Name","Size","Production Date", "Expiration date", "Quantity", "Price");
    }
    
    @Override
    public void add() {
        System.out.println("Add product to store: ");
        while (true)
        {
            int pos;
            String newCode;
            do{
                newCode = Utils.getSringPattern("    - Enter code: ", CODE_FORMAT , "    - Invalid code!");
                pos = find(newCode);
                if (pos<0)
                {
                    String newName = Utils.getString("    - Enter name: ");
                    String newSize = Utils.getSringPattern("    - Enter size: ", SIZE_FORMAT, "- Invalid size!", "N/A");
                    String newManuDate = Utils.getDate("    - Enter manufacturing date: ", DATE_FORMAT);
                    String newExrDate = Utils.getDate("    - Enter expiration date: ", DATE_FORMAT);
                    double newPrice = Utils.getDouble("    - Enter price: ", MIN_PRICE, MAX_PRICE);
                    this.add(new Product(newCode, newName, newSize, newManuDate, newExrDate, newPrice));
                    System.out.println("    --> Product " + newCode + " has been added!");
                }else System.out.println("    - Error: The code was duplicated!");
            }while(pos>=0);
            if (!Utils.confirmYesNo("    - Continuous create new product? (y/n)")) 
            {
                System.out.println("");
                return;
            }
        }      
    }

    @Override
    public void update() {
        if (this.isEmpty()){
            System.out.println("Error: Product list is empty!");
            return;
        }
        int pos;
        String updateCode;
        do{
            updateCode = Utils.getSringPattern("Enter code: ", CODE_FORMAT , "Invalid code!");
            pos = find(updateCode);
            if (pos>=0)
            {
                System.out.println("FOUD: ");
                printHeader();
                System.out.println(this.get(pos));
                String newName = Utils.getString("Enter new name: ", this.get(pos).getName());
                String newSize = Utils.getSringPattern("Enter new size: ", SIZE_FORMAT, "Invalid size!", this.get(pos).getSize());
                String newManuDate = Utils.getDate("Enter manufacturing date: ", DATE_FORMAT, this.get(pos).getManufacturingDate());
                String newExrDate = Utils.getDate("Enter expiration date: ", DATE_FORMAT, this.get(pos).getExpirationDate());
                double newPrice = Utils.getDouble("Enter price: ", MIN_PRICE, MAX_PRICE, this.get(pos).getPrice());
                this.get(pos).setName(newName);
                this.get(pos).setSize(newSize);
                this.get(pos).setManufacturingDate(newManuDate);
                this.get(pos).setExpirationDate(newExrDate);
                this.get(pos).setPrice(newPrice);

                System.out.println("The product " + updateCode + " has been updated!");
            }else System.out.println("Product does not exist!");
        }while(pos<0);
    }
    
    @Override
    public void remove() {
        if (this.isEmpty()){
            System.out.println("Error: Product list is empty!");
            return;
        }
        int pos;
        do{
            String removeCode = Utils.getSringPattern("Enter remove code: ", CODE_FORMAT , "Invalid code!");
            pos = find(removeCode);
            if (pos>=0)
            {
                // Make sure the product has not been generated
                if (!this.get(pos).isGenerated()){
                    System.out.println("FOUND: ");
                    printHeader();
                    System.out.println(this.get(pos));
                    if (Utils.confirmYesNo("Remove? (y/n): "))
                    {
                        this.remove(pos);
                        System.out.println("Remove successful!");
                    }else System.out.println("---> No product has been removed!");
                }else System.out.println("Error: Can not remove, this product has been generated in import/export receipt!");         
            }else System.out.println("The code does not exist!");
        }while(pos<0);     
    }

    @Override
    public void sortByPrice() {
        Collections.sort(this, (Product o1, Product o2) -> {
            double result = o1.getPrice()-o2.getPrice();
            if (result>0)
            {
                return 1;
            }else if (result<0)
            {
                return -1;
            }else return 0;         
        });
    }

    @Override
    public void sortByQuantity() {
        Collections.sort(this, new Comparator<Product>(){
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getQuantity() - o2.getQuantity();
            }         
        });
    } 


    @Override
    public void show() {
        if (this.isEmpty()){
            System.out.println("Error: Product list is empty!");
            return;
        }
        
        System.out.println("Product list: ");
        printHeaderWithNum();
        for (int i=0;i<this.size();i++){
            System.out.printf(" %3d", i+1);
            System.out.println(this.get(i));
        }
        System.out.println("");
    }

    @Override
    public void loadDataFromFile(String path) {
        try {
            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Product pr;
            while ((pr = (Product) (fo.readObject())) != null)
            {
                this.add(pr);
            }
            fo.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
        }

    }
    
    
    @Override
    public void saveDataToFile(String path) {
        try {
            FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            if (this.isEmpty())
            {
                fo.writeChars("");
            }else{
                for (Product pr : this)
                {
                    fo.writeObject(pr);
                }
            }           
            fo.close();
            f.close();
        } catch (IOException e)   
        {
            System.out.println(e);
        }
    }  
    
}
