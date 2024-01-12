package cotrollers;

import java.io.Serializable;
public class LongLifeProduct extends Products implements Serializable{

    private String manufacturingDate;
    private String expirationDate;
    private String type;

    public LongLifeProduct() {
    }

    public LongLifeProduct(String productCode, String productName, String manufacturingDate, String expirationDate) {
        super(productCode, productName);
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.type = "Long Life Product";;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return "Long";
    }

    public void setType(String type) {
        this.type = "Long";
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s, %s, %s", getProductCode(),
                getProductName(), getQuantity(),manufacturingDate, expirationDate, type);
    }

}
