package cotrollers;

import java.io.Serializable;

public class DailyUseProduct extends Products implements Serializable {

    private String type;
    private String size;

    public DailyUseProduct() {
    }

    public DailyUseProduct(String productCode, String productName, String size) {
        super(productCode, productName);
        this.type = "Daily use Product";
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s, %s", getProductCode(), getProductName(), getQuantity(),
                size, type);
    }

}
