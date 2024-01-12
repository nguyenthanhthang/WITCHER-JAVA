package manage;

import cotrollers.ProductList;
import iu.Menu;

public class ProductsManage {

    public void showMenu() {
        ProductList product = new ProductList();
        product.loadFile();
        Menu menu = new Menu("Menu");
        menu.addNewOption("Add a product");
        menu.addNewOption("Update product information");
        menu.addNewOption("Delete product");
        menu.addNewOption("Show all product");
        menu.addNewOption("Show all product 1");
        menu.addNewOption("Exit.");
        int choice;
        while (true) {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    product.addProduct();
                    break;
                }
                case 2: {
                    product.updateProduct();
                    break;
                }
                case 3: {
                    product.removeProduct();
                    break;
                }
                case 4: {
                    product.listProducts();             
                    break;
                }
                case 5: {
                    product.searchF();
                    break;
                }
                case 6: {
                    product.savetoFile();
                    System.out.println("Comeback Main Menu!");
                    return;
                }
            }
        }
    }
}
