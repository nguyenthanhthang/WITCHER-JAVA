package controllers;

import dto.A_Receipt;
import java.util.HashMap;
import utils.Utils;

public class ImportReceipt extends A_Receipt{

    static boolean isInstance(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String createTime;
    private HashMap<String, Integer> products;

    public ImportReceipt(String createTime, HashMap<String, Integer> products) {
        this.createTime = createTime;
        this.products = products;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Integer> productList) {
        this.products = productList;
    }

    @Override
    public void addProduct(ProductList productList) {
        boolean cont;
        int userChoice;
        int quantity, count = 0;
        int listSize = productList.size();
        do {            
            userChoice = Utils.getInt("    - Select products to add [1.." + listSize +"]: ", 1, listSize) - 1;
            String productCode = productList.get(userChoice).getCode();    
            if (find(productCode, products)<0)
            {
                quantity = Utils.getInt("    - Enter quantity: ", 1, ProductList.MAX_QUANTITY);
                products.put(productCode, quantity);
                int newQuantity = productList.get(userChoice).getQuantity() + quantity;
                productList.get(userChoice).setQuantity(newQuantity);
                if (!productList.get(userChoice).isGenerated()) productList.get(userChoice).setGenerated(true);
                count++;
            }else
            {
                System.out.println("    - Error: The product already exists in this receipt!");
            }
            
            cont = Utils.confirmYesNo("    - Continue add product? (y/n): ");
            
            if (cont && count==listSize) System.out.println("    - All product has been add!");
            
        } while (cont && count<listSize);
    }
    
}
