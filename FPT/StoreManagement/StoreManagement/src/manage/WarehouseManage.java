package manage;

import cotrollers.WarehouseList;
import iu.Menu;
import tool.Inputter;

public class WarehouseManage {

    public void showMenuWarehouse() {
        Menu menu = new Menu("Menu");
        WarehouseList warehouse = new WarehouseList();
        warehouse.loadFileI();
        menu.addNewOption("Create an import receipt");
        menu.addNewOption("Create an export receipt");
        menu.addNewOption("Exit.");
        int choice;
        while (true) {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1: {
                    warehouse.createImportReceipt();
                    break;
                }
                case 2: {
                    warehouse.createExportReceipt();
                    break;
                }
                case 3: {
                    warehouse.savetoFileI();
                    System.out.println("Bye!");
                    return;
                }
            }
        }
    }
}
