package dto;

import java.io.Serializable;

public class Product implements Serializable {
    
    private String code, name, size;
    private int quantity;
    private double price;
    private boolean generated;
    private String manufacturingDate, expirationDate;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
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

    public Product() {
        generated = false;
    }

    public Product(String code, String name, String size, String manufacturingDate, String expirationDate, double price) {
        this();
        this.code = code.toUpperCase();
        this.name = name;
        this.size = size;
        this.manufacturingDate = manufacturingDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }

    @Override
    public String toString() {
        String formatOut = " | %4s | %-20s  | %-4s | %15s | %15s | %9d | %8.2f |";
        return String.format(formatOut, getCode(), getName(), getSize(), getManufacturingDate(),
                getExpirationDate(), getQuantity(), getPrice());
    }
}
