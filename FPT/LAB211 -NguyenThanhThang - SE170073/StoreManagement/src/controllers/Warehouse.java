package controllers;

import dto.A_Receipt;
import java.util.HashMap;
import dto.I_Warehouse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.Utils;

public class Warehouse extends HashMap<Integer, A_Receipt> implements I_Warehouse{
    
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    
    @Override
    public void createImportReceipt(ProductList productList) {
        if(productList.isEmpty())
        {
            System.out.println("    - Error: Product list is empty!");
            if (Utils.confirmYesNo("    - Add product information to store? (y/n): "))
            {
                productList.add();
            }else return;
        }
        
        String createTime = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        ImportReceipt importReceipt = new ImportReceipt(createTime, new HashMap<>());
        
        
        productList.show();
        System.out.println("Create an import receipt: ");
        
        importReceipt.addProduct(productList);
        
        if (importReceipt.getProducts().isEmpty())
        {
            System.out.println("    ----> Create the import receipt fail!");
        }
        else{
            this.put((this.size()+1), importReceipt);
            System.out.println("    ----> Create the import receipt successful!");
        }
        System.out.println("");
    }

    @Override
    public void createExportReceipt(ProductList productList) {
        if(productList.isEmpty())
        {
            System.out.println("    - Error: Product list is empty!");
            if (Utils.confirmYesNo("    - Add product information to store? (y/n): "))
            {
                productList.add();
            }else return;
        }
        
        String createTime = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        ExportReceipt exportReceipt = new ExportReceipt(createTime, new HashMap<>());
        
        productList.show();
        System.out.println("Create an export receipt: ");
        exportReceipt.addProduct(productList);
        if (exportReceipt.getProducts().isEmpty())
        {
            System.out.println("    ----> Create the export receipt fail!");
        }
        else{
            this.put((this.size()+1), exportReceipt);
            System.out.println("    ----> Create the export receipt successful!");
        }
        System.out.println("");
    }

    @Override
    public void loadDataFromFile(String path) {
        try {
            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream fo = new ObjectInputStream(fi);
            A_Receipt receipt;
            while ((receipt = (A_Receipt) (fo.readObject()) ) != null)
            {
                this.put((this.size()+1), receipt);
            }
            fo.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
        }
    }
    
    @Override
    public void saveDataToFile(String fileName) {
        if (this.isEmpty()){
            return;
        }
        
        try {
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (A_Receipt receipt : this.values())
            {
                fo.writeObject(receipt);
            }
            fo.close();
            f.close();
        } catch (IOException e)   
        {
            System.out.println(e);
        }
    } 
   

}
