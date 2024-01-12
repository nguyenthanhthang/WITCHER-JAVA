package controllers;

import dto.A_Receipt;
import java.util.HashMap;
import utils.Utils;

public class ExportReceipt extends A_Receipt{
    
    private String createTime;
    private HashMap<String, Integer> products;

    public ExportReceipt(String createTime, HashMap<String, Integer> products) {
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

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
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
            if (find(productCode, products)>=0)
            {
                System.out.println("    - The product already exists in this receipt!");
            }else {
                if (Utils.dateLOET(this.getCreateTime(), productList.get(userChoice).getExpirationDate(), DATE_FORMAT))
                {
                    int currentQuality = productList.get(userChoice).getQuantity();
                    if (currentQuality>0)
                    {
                        quantity = Utils.getInt("    - Enter quantity [1.."+currentQuality+"]: ", 1, currentQuality);
                        products.put(productCode, quantity);
                        int newQuantity = productList.get(userChoice).getQuantity() - quantity;
                        productList.get(userChoice).setQuantity(newQuantity);
                        if (!productList.get(userChoice).isGenerated()) productList.get(userChoice).setGenerated(true);
                        count++;
                    } else System.out.println("    - This product is out of stock!");
                }else System.out.println("    - The product has expired!");
                
            }
            
            cont = Utils.confirmYesNo("    - Continue add product? (y/n): ");
            if (cont && count==listSize) System.out.println("    - All product has been add!");
        } while (cont && count<listSize);
    }

}
