package cotrollers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tool.Inputter;

public class ReportList implements IReport {

    ProductList list = new ProductList();
    WarehouseList ware = new WarehouseList();
    Set<Products> productSet = new HashSet<>();

    public int searchProduct(String fCode) {
        list.loadFile();
        for (int i = 0; i <= list.getProductsList().size() - 1; i++) {
            if (list.getProductsList().get(i).getProductCode().equals(fCode)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void showProductsExpired() {
        list.loadFile();
        List<Products> listEx = new ArrayList<>();
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        try {
            Date nowTime = sdf.parse(time);
            for (Products item : list.getProductsList()) {
                if (item instanceof LongLifeProduct) {
                    LongLifeProduct pl = (LongLifeProduct) item;
                    Date endTime = sdf.parse(pl.getExpirationDate());
                    if (nowTime.compareTo(endTime) > 0) {
                        if (!productSet.contains(item)) {
                            listEx.add(item);
                            productSet.add(item);
                        }
                    }
                }
            }
        } catch (ParseException ex) {
            System.out.println("Lỗi Bà bá");
        }
        for (Products product : listEx) {
            System.out.println(product);
        }
    }

    @Override
    public void showProductsSelling() {
        List<Products> listEx = new ArrayList<>();
        list.loadFile();
        List<Products> listProduct = list.getProductsList();
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        try {
            Date nowTime = sdf.parse(time);
            for (Products item : listProduct) {
                if (item instanceof LongLifeProduct) {
                    LongLifeProduct pl = (LongLifeProduct) item;
                    Date endTime = sdf.parse(pl.getExpirationDate());
                    if (nowTime.compareTo(endTime) < 1) {
                        if (!productSet.contains(item)) {
                            listEx.add(item);
                            productSet.add(item);
                        }
                    }
                } else if (item instanceof DailyUseProduct) {
                    DailyUseProduct dp = (DailyUseProduct) item;
                    if (dp.getQuantity() < 3) {
                        if (!productSet.contains(item)) {
                            listEx.add(item);
                            productSet.add(item);
                        }
                    }
                }
            }
        } catch (ParseException ex) {
            System.out.println("Lỗi Bà bá");
        }
        for (Products product : listEx) {
            System.out.println(product);
        }

    }

    @Override
    public void showProductsRunningOut() {
        List<Products> listEx = new ArrayList<>();
        list.loadFile();
        List<Products> listProduct = list.getProductsList();
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        try {
            Date nowTime = sdf.parse(time);
            for (Products item : listProduct) {
                if (item instanceof LongLifeProduct) {
                    LongLifeProduct pl = (LongLifeProduct) item;
                    Date endTime = sdf.parse(pl.getExpirationDate());
                    if (nowTime.compareTo(endTime) < 1 && item.getQuantity() < 3) {
                        if (!productSet.contains(item)) {
                            listEx.add(item);
                            productSet.add(item);
                        }
                    }
                } else if (item instanceof DailyUseProduct) {
                    DailyUseProduct dp = (DailyUseProduct) item;
                    if (dp.getQuantity() < 3) {
                        if (!productSet.contains(item)) {
                            listEx.add(item);
                            productSet.add(item);
                        }
                    }
                }
            }
        } catch (ParseException ex) {
            System.out.println("Lỗi Bà bá");
        }
        Comparator<Products> c = new Comparator<Products>() {
            @Override
            public int compare(Products o1, Products o2) {
                if (o1.getQuantity() > o2.getQuantity()) {
                    return 1;
                } else if (o1.getQuantity() < o2.getQuantity()) {
                    return -1;
                }
                return 0;
            }
        };
        Collections.sort(listEx, c);

        // Show result list
        System.out.println("List of products running out:");
        System.out.println("| Product code | Product name | Quantity | Production date | Expiration date | ");
        for (Products product : listEx) {
            System.out.println(product);
        }
        System.out.println("Total products: " + listEx.size());
    }

    @Override
    public void showReceiptProduct() {
        WarehouseList warehouse = new WarehouseList();
        warehouse.loadFileI();
        list.loadFile();
        System.out.println(warehouse.getListImport());
        String codeProduct = Inputter.getString("Enter Product Code:(Pxxxx)", "That filed is required", "[pP]\\d{4}");
        int pos = searchProduct(codeProduct);
        if (pos == -1) {
            System.out.println("Product does not exist. Please enter a valid product code:");
            return;
        }
        System.out.println("Receipt of product " + codeProduct + ": ");

        // Danh sách hóa đơn nhập
        List<Warehouse> importReceipts = new ArrayList<>();
        for (Warehouse item : warehouse.getListImport()) {
            for (Products product : item.getListProducts()) {
                if (product.getProductCode().equals(codeProduct)) {
                    importReceipts.add(item);
                }
            }
        }

        // In hóa đơn nhập
        if (!importReceipts.isEmpty()) {
            System.out.println("Hóa đơn nhập: ");
            for (Warehouse item : importReceipts) {
                System.out.println(item);
            }
        } else {
            System.out.println("Không tìm thấy hóa đơn nhập cho sản phẩm này.");
        }

        // Danh sách hóa đơn xuất
        List<Warehouse> exportReceipts = new ArrayList<>();
        for (Warehouse item : warehouse.getListExport()) {
            for (Products product : item.getListProducts()) {
                if (product.getProductCode().equals(codeProduct)) {
                    exportReceipts.add(item);
                }
            }
        }

        // In hóa đơn xuất
        if (!exportReceipts.isEmpty()) {
            System.out.println("Hóa đơn xuất: ");
            for (Warehouse item : exportReceipts) {
                System.out.println(item);
            }
        } else {
            System.out.println("Không tìm thấy hóa đơn xuất cho sản phẩm này.");
        }
    }
}
