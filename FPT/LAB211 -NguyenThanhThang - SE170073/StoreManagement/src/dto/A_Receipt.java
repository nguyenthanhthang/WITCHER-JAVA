package dto;

import controllers.ProductList;
import java.io.Serializable;
import java.util.HashMap;

public abstract class A_Receipt implements Serializable{    
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    
    // Find the position of element which has code equal parameter code
    public int find(String code, HashMap<String, Integer> products){
        int pos = -1, count = 0;
        if (!products.isEmpty()){
            for (String s : products.keySet())
            {
                if (s.equalsIgnoreCase(code)) pos = count; 
                count++;
            }
        } 
        return pos;
    }
    
    // Add a new product to the receipt
    public void addProduct(ProductList productList){ }
}
