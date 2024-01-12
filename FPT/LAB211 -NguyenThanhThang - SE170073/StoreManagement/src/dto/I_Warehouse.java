package dto;

import controllers.ProductList;

/**
 *
 * @author DAI_ADMIN
 */

/* Interface for Warehouse */
public interface I_Warehouse {
    /**Create an import receipt
     * @param productList */
    public void createImportReceipt(ProductList productList);
    
    // Create an export receipt
    public void createExportReceipt(ProductList productList);
    
    // Load the warehouse information from file
    public void loadDataFromFile(String filePath);
    
    // Save the warehouse information to file 
    public void saveDataToFile(String filePath);
}
