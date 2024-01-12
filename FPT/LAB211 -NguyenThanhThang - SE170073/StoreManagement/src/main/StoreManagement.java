package main;

import dto.I_Menu;
import controllers.Menu;
import controllers.ProductList;
import controllers.Reporter;
import controllers.Warehouse;
import utils.Utils;

public class StoreManagement {
    
    public static void main(String args[]) {
        
        final String productFilePath = "src\\data\\product.dat";
        final String warehouseFilePath = "src\\data\\warehouse.dat";
        
        I_Menu menu = new Menu();
        menu.addItem("1. Manage product of the store.");
        menu.addItem("2. Warehouse management.");
        menu.addItem("3. Report.");
        menu.addItem("4. Store data to file.");
        menu.addItem("5. Quit");
        
        ProductList productList = new ProductList();
        Warehouse warehouse = new Warehouse();
        Reporter reporter = new Reporter();
        productList.loadDataFromFile(productFilePath);
        warehouse.loadDataFromFile(warehouseFilePath);
        
        I_Menu subMenu;
        int subUserChoice;  
        int userChoice;
        boolean saveFlag = false;
        boolean cont = false;
        do {
            System.out.println("================ STORE MANAGE ================");
            menu.showMenu();
            userChoice = menu.getChoice();
            switch (userChoice) {
                
                // Manage products of the store
                case 1:
                    subMenu = new Menu();
                    subMenu.addItem("   1.1. Add a product.");
                    subMenu.addItem("   1.2. Update product information.");
                    subMenu.addItem("   1.3. Delete product.");
                    subMenu.addItem("   1.4. Show all product.");
                    subMenu.addItem("   1.5. Back to main menu.");
                
                    System.out.println("-------------- Product Manage --------------");
                    subMenu.showMenu();
                    subUserChoice = subMenu.getChoice();
                    
                    switch (subUserChoice){

                        // Add products to the list
                        case 1:
                            productList.add();
                            saveFlag = true;
                            break;  

                        // Update product information
                        case 2:
                            productList.update();
                            saveFlag = true;
                            break;

                        // Delete a product from the list
                        case 3:
                            productList.remove();
                            saveFlag = true;
                            break;

                        // Show all product
                        case 4:
                            productList.show();
                            break;
                            
                        case 5:
                            break;
                    }
 
                    break;
                    
                // Warehouse management
                case 2:
                    subMenu = new Menu();
                    subMenu.addItem("   2.1. Create an import receipt.");
                    subMenu.addItem("   2.2. Create an export receipt.");
                    subMenu.addItem("   2.3. Back to main menu.");
                    
                    System.out.println("-------------- Warehouse Manage --------------");
                    subMenu.showMenu();
                    subUserChoice = subMenu.getChoice();
                    
                    switch (subUserChoice){

                        // Create import receipt
                        case 1:
                            warehouse.createImportReceipt(productList);
                            saveFlag = true;
                            break;  

                        // Create export receipt
                        case 2:
                            warehouse.createExportReceipt(productList);
                            saveFlag = true;
                            break;
                            
                        case 3:
                            break;
                            
                    }
                    
                    break;
                    
                // Report store's information
                case 3:
                    subMenu = new Menu();
                    subMenu.addItem("   3.1. Products that have expired.");
                    subMenu.addItem("   3.2. The products that the store is selling.");
                    subMenu.addItem("   3.3. Products that are running out of stock");
                    subMenu.addItem("   3.4. Import/export receipt of a product.");
                    subMenu.addItem("   3.5. Back to main manu.");
                  
                    System.out.println("------------------ Report ------------------");
                    subMenu.showMenu();
                    subUserChoice = subMenu.getChoice();
                    switch (subUserChoice){
                        
                        // Show products that have expired
                        case 1:
                            reporter.showProductsExpired(productList);
                            break;
                            
                        // Show all products that the store is selling
                        case 2:
                            reporter.showProductsSelling(productList);
                            break;
                            
                        // Show all products that are running out of stock
                        case 3:
                            reporter.showProductsROS(productList);
                            break;
                            
                        // Show import/export receipt of a product
                        case 4:
                            reporter.showReceiptOfAProduct(productList, warehouse);
                            break;
                        case 5:
                            break;
                    }
                    break;
                    
                // Store data to file
                case 4:
                    productList.saveDataToFile(productFilePath);
                    warehouse.saveDataToFile(warehouseFilePath);
                    saveFlag = false;
                    break;
                    
                // Exit program
                case 5:
                    if (saveFlag)
                    {
                        if(Utils.confirmYesNo("Save data and exit? (y/n): "))
                        {
                            productList.saveDataToFile(productFilePath);
                            warehouse.saveDataToFile(warehouseFilePath);
                            cont = true;
                        }
                    }else{
                        cont = Utils.confirmYesNo("Exit now? (y/n): ");
                    }
                    break;
            }
        } while (userChoice >= 0 && userChoice <= 5 && !cont);
    }
}
