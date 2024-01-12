package runtime;

import manage.ProductsManage;
import manage.ReportManage;
import manage.WarehouseManage;
import iu.Menu;

public class Program {

    public static void main(String[] args) {
        ProductsManage productsManage = new ProductsManage();
        WarehouseManage warehouseManage = new WarehouseManage();
        ReportManage reportManage = new ReportManage();
        //Dựng menu
        Menu menu = new Menu("Menu");
        menu.addNewOption("Manage products");
        menu.addNewOption("Manage Warehouse");
        menu.addNewOption("Report");
        menu.addNewOption("Exit.");
        int choice;
        while (true) {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    productsManage.showMenu();
                    break;
                }
                case 2: {
                    warehouseManage.showMenuWarehouse();
                    break;
                }
                case 3: {
                    reportManage.showMenu();
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
