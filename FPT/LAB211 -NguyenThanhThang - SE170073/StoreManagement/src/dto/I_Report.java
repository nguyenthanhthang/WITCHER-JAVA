package dto;

import controllers.ProductList;
import controllers.Warehouse;

/**
 *
 * @author DAI_ADMIN
 */

/* Interface for report store's information */
public interface I_Report {
    
    // Show all products that have expired
    public void showProductsExpired(ProductList productList);
    
    // Show all products that the store is selling
    public void showProductsSelling(ProductList productList);
    
    // Show all products that are running out of stock
    public void showProductsROS(ProductList productList);
    
    // Show import/export receipt of a product
    public void showReceiptOfAProduct(ProductList productList, Warehouse warehouse);
}
