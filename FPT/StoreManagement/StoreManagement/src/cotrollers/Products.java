package cotrollers;

import java.io.Serializable;

public class Products implements Serializable{

    private String productCode;
    private String productName;
    private int quantity;

    //contrucstor
    public Products() {}
    public Products(String productCode, String productName) {
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = 0;
    }
    

    //getter setter
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Override
    public String toString() {
        return String.format("%s, %s, %d", productCode, productName, quantity);
    }

}
