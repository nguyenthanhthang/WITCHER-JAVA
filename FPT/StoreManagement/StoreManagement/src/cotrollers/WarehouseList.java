/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotrollers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import tool.Inputter;

/**
 *
 * @author Admin
 */
public class WarehouseList implements IWarehouse {

    //warehouseList: thằng quản lí danh sách sản phẩm
    ArrayList<Warehouse> listImport = new ArrayList<>();
    ArrayList<Warehouse> listExport = new ArrayList<>();
    //tạo biến lưu trữ danh sách sản phẩm đã thêm vào hóa đơn
    ProductList list = new ProductList();

    public void savetoFileI() {
        Inputter.saveListToFile(listImport, "src/output/warehouse.dat");
        Inputter.saveListToFile(listExport, "src/output/warehouse.dat");
        Inputter.saveListToFile(listImport, "src/output/listImport.dat");
        Inputter.saveListToFile(listExport, "src/output/listExport.dat");
    }

    public void loadFileI() {
        Inputter.loadListFromFile(listImport, "src/output/warehouse.dat");
        Inputter.loadListFromFile(listExport, "src/output/warehouse.dat");
        Inputter.loadListFromFile(listImport, "src/output/listImport.dat");
        Inputter.loadListFromFile(listExport, "src/output/listExport.dat");
    }
    //hàm tiềm id thô

    public ArrayList<Warehouse> getListImport() {
        return listImport;
    }

    public ArrayList<Warehouse> getListExport() {
        return listExport;
    }

    public int searchProduct(String fCode) {
        list.loadFile();
        for (int i = 0; i <= list.getProductsList().size() - 1; i++) {
            if (list.getProductsList().get(i).getProductCode().equals(fCode)) {
                return i;
            }
        }
        return -1;
    }

    public int searchProductInReceipt(String codeProduct, List<Products> listProductsInReceipt) {
        for (Products product : listProductsInReceipt) {
            if (product.getProductCode().equals(codeProduct)) {
                return listProductsInReceipt.indexOf(product);
            }
        }
        return -1;
    }

    @Override
    public void createImportReceipt() {
        //tọa ra code hoá đơn 
        String code = "I";
        int endCode = listImport.size() + 1;
        if (endCode > 999999) {
            System.out.println("Warehoure Full");
        }
        int numberZero = 7 - String.valueOf(endCode).length();
        String zero = "";
        for (int i = 1; i <= numberZero; i++) {
            zero += "0";
        }
        code += (zero + endCode);
        //tạo ra ngày hiện tại
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        //tạo danh sách sản phẩm trong hóa đơn nhập
        List<Products> listProductsInReceipt = new ArrayList<>();
        boolean isContinue = true;
        int quantity = 0;
        while (isContinue) {
            //nhập mã sản phẩm
            String codeProduct = Inputter.getString("Enter Product Code:(Pxxxx)", "That filed is required", "[pP]\\d{4}");
            int pos = this.searchProduct(codeProduct.substring(0).toUpperCase());
            int posI = this.searchProductInReceipt(codeProduct.substring(0).toUpperCase(), listProductsInReceipt);
            //kiểm tra mã sản phẩm có tồn tại không
            if (pos == -1) {
                System.out.println("Product does not exist. Please enter a valid product code:");

            } else if (posI >= 0) {
                System.out.println("Product already exists in import receipt");
            } else {
                do {
                    quantity = Inputter.getAnInteger("Input quantity: ",
                            "That field is requied");
                    if (quantity < 0) {
                        System.out.println("Quantity must to positive real number");
                    }
                } while (quantity < 0);
                // Add the product to the export receipt.
                listProductsInReceipt.add(list.getProductsList().get(pos));
                // Update the quantity of the product in the warehouse.
                list.getProductsList().get(pos).setQuantity(list.getProductsList().get(pos).getQuantity() + quantity);
                
            }
            //hỏi người dùng có muốn thêm sản phẩm nào nữa không
            isContinue = Inputter.readBool("Do you want to add another product? ");
        }
        //tạo hóa đơn nhập
        Warehouse importReceipt = new Warehouse(code, time, listProductsInReceipt);
        //thêm hóa đơn nhập vào danh sách hóa đơn nhập
        listImport.add(importReceipt);
        for (Warehouse warehouse : listImport) {
            System.out.println(warehouse);
        }
        System.out.println("Import receipt created successfully");
  
    }

    @Override
    public void createExportReceipt() {
        // Generate a new export receipt code.
        String code = "E";
        int endCode = listExport.size() + 1;
        if (endCode > 999999) {
            System.out.println("Warehouse full");
            return;
        }
        int numberZero = 7 - String.valueOf(endCode).length();
        String zero = "";
        for (int i = 1; i <= numberZero; i++) {
            zero += "0";
        }
        code += (zero + endCode);

        // Get the current time.
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        // Create a new empty list of products.
        List<Products> listProductsInReceiptE = new ArrayList<>();

        // Add products to the export receipt.
        boolean isContinue = true;
        while (isContinue) {
            // Get the product code and quantity from the user.
            String codeProduct = Inputter.getString("Enter Product Code:(Pxxxx)", "That filed is required", "[pP]\\d{4}");
            int posI = this.searchProductInReceipt(codeProduct, listProductsInReceiptE);
            // Check if the product code exists in the product list.
            int pos = this.searchProduct(codeProduct);
            int quantityInWarehouse = list.getProductsList().get(pos).getQuantity();
            if (pos == -1) {
                System.out.println("Product does not exist. Please enter a valid product code:");
            } else if (posI >= 0) {
                System.out.println("Product already exists in import receipt");
            } else {
                int quantity;
                do {
                    quantity = Inputter.getAnInteger("Input quantity: ",
                            "That field is requied");
                    if (quantity < 0) {
                        System.out.println("Quantity must to positive real number");
                    }
                } while (quantity < 0);
                // Check if the quantity in the warehouse is enough.
                if (quantity > quantityInWarehouse) {
                    System.out.println("Not enough quantity in the warehouse.");
                } else {
                    // Add the product to the export receipt.
                    listProductsInReceiptE.add(list.getProductsList().get(pos));
                    // Update the quantity of the product in the warehouse.
                    list.getProductsList().get(pos).setQuantity(list.getProductsList().get(pos).getQuantity() - quantity);
                    
                }
            }
            //hỏi người dùng có muốn thêm sản phẩm nào nữa không
            isContinue = Inputter.readBool("Do you want to add another product? ");
        }
        // Create a new export receipt.
        Warehouse exportReceipt = new Warehouse(code, time, listProductsInReceiptE);
        // Add the export receipt to the list of export receipts.
        listExport.add(exportReceipt);
        for (Warehouse warehouse : listExport) {
            System.out.println(warehouse);
        }
        System.out.println("Export receipt created successfully.");
    }

}
