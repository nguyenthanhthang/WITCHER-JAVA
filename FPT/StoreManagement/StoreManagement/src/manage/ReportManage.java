
package manage;

import cotrollers.ProductList;
import cotrollers.ReportList;
import iu.Menu;

public class ReportManage {
    public void showMenu() {
        ReportList reportManage = new ReportList();
        Menu menu = new Menu("Menu");
        menu.addNewOption("Products that have expired");
        menu.addNewOption("The products that the store is selling");
        menu.addNewOption("Products that are running out of stock (sorted in ascending order).");
        menu.addNewOption("Import/export receipt of a product.");
        menu.addNewOption("Exit.");
        int choice;
        while (true) {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    reportManage.showProductsExpired();
                    break;
                }
                case 2: {
                    reportManage.showProductsSelling();
                    break;
                }
                case 3: {
                    reportManage.showProductsRunningOut();
                    break;
                }
                case 4: {
                    reportManage.showReceiptProduct();
                    break;
                }
                case 5: {
                    System.out.println("Bye!");
                    return;
                }
            }
        }
    }
}
