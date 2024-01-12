package cotrollers;

import java.util.ArrayList;
import tool.Inputter;

public class ProductList implements IProductList {

    //productsList: thằng quản lí danh sách sản phẩm
    ArrayList<Products> productsList = new ArrayList<>();

    public ArrayList<Products> getProductsList() {
        return productsList;
    }

    @Override
    public void listProducts() {
        if (productsList.isEmpty()) {
            System.out.println("Products List nothing to print");
            return;//ngừng luôn
        }
        System.out.println("__Products List__");
        for (Products item : productsList) {
            System.out.println(item);//sout mặc định gọi toString không cần ghi
        }
    }

    public void savetoFile() {
        Inputter.saveListToFile(productsList, "src/output/product.dat");
    }

    public void loadFile() {
        Inputter.loadListFromFile(productsList, "src/output/product.dat");
    }

    //hàm thêm
    @Override
    public void addProduct() {
        //thu thập dữ liệu
        //id
        boolean check = false;
        do {
            boolean isDup = false;
            String id;
            do {
                //nhập 
                isDup = false;//reset
                id = Inputter.getString("Input Products code: ",
                        "That field is requied", "[pP]\\d{4}");
                //check trùng
                for (Products item : productsList) {
                    if (item.getProductCode().equals(id)) {
                        isDup = true;
                        System.out.println("Id is duplicate");
                    }
                }
            } while (isDup);
            //productName,quantity
            String productName = Inputter.getString("Input Product Name: ",
                    "That field is requied ");
            //làm đến đây là đủ nguyên liệu để tạo Product
            boolean isDaily = true;
            isDaily = Inputter.readBool("Is daily product? ");
            if (isDaily == false) {
                //enter manufacture
                String manufactureDate = Inputter.checkBeforeDate("Enter date Manufacture (dd/mm/yyyy)");
                //enter expirationDate
                String expirationDate = Inputter.checkAfterDate("Enter date ExpirationDate (dd/mm/yyyy)", manufactureDate);
                productsList.add(new LongLifeProduct(id.substring(0).toUpperCase(), productName, manufactureDate, expirationDate));
            } else {
                String size = Inputter.checkSize("Input SIZE ('Small' or 'Medium' or 'Large' !:) ");
                DailyUseProduct dp = new DailyUseProduct(id.substring(0).toUpperCase(), productName, size);
                productsList.add(dp);
            }
            System.out.println("Produts adding is successful");

            check = Inputter.readBool("Do you want more products?");
        } while (check);
    }

    //hàm tiềm id thô
    public int searchCode(String fCode) {
        for (int i = 0; i <= productsList.size() - 1; i++) {
            if (productsList.get(i).getProductCode().equals(fCode)) {
                return i;
            }
        }
        return -1;
    }
    public Products searchCodeF() {

            return productsList.get(0);

    }

    //update
    @Override
    public boolean updateProduct() {

        String keyId = Inputter.getString("Input Code Product you wanna update",
                "That filed is required");
        int pos = this.searchCode(keyId.substring(0).toUpperCase());
        if (pos == -1) {
            System.out.println("Product does not exist");
            return false;
        } else {
            System.out.println("Product information before updating");
            System.out.println(productsList.get(pos));
            System.out.println("Uptading...");
            //thu thập
            //Name
            //productName,manufacturingDate,expirationDate,price
            String productName = Inputter.getString("Input Product Name: ",
                    "That field is requied");
            int quantity;
            do {
                quantity = Inputter.getAnInteger("Input Quantity: ",
                        "That field is requied");
                if (quantity < 0) {
                    System.out.println("Quantity must to positive real number");
                }
            } while (quantity < 0);
            boolean isUpdate = false;
            boolean isDaily = productsList.get(pos) instanceof DailyUseProduct;
            if (isDaily == false) {
                //enter manufacture
                LongLifeProduct lp = (LongLifeProduct) productsList.get(pos);
                String manufactureDateOld = lp.getManufacturingDate();
                String expirationDateOld = lp.getExpirationDate();
                String manufactureDate = Inputter.checkBeforeDate("Enter date Manufacture (dd/mm/yyyy)");
                //enter expirationDate
                String expirationDate = Inputter.checkAfterDate("Enter date ExpirationDate (dd/mm/yyyy)", manufactureDate);
                isUpdate = Inputter.readBool("Are you sure you want to update");
                if (isUpdate == false) {
                    System.out.println("Update is failed");
                } else {
                    //cập nhật setter
                    productsList.get(pos).setProductName(productName);
                    productsList.get(pos).setQuantity(quantity);
                    lp.setManufacturingDate(manufactureDate);
                    lp.setExpirationDate(expirationDate);
                    System.out.println(productsList.get(pos));
                    System.out.println("Updating is successfull");
                }
            } else {
                DailyUseProduct dp = (DailyUseProduct) productsList.get(pos);
                String sizeOld = dp.getSize();
                String size = Inputter.checkSize("Input SIZE ('Small' or 'Medium' or 'Large' !:)");
                isUpdate = Inputter.readBool("Are you sure you want to update");
                if (isUpdate == false) {
                    System.out.println("Update is failed");
                } else {
                    //cập nhật setter
                    productsList.get(pos).setProductName(productName);
                    productsList.get(pos).setQuantity(quantity);
                    dp.setSize(size);
                    System.out.println(productsList.get(pos));
                    System.out.println("Updating is successfull");
                }
            }
        }
        return true;
    }

    //remove
    @Override
    public boolean removeProduct() {
        String keyId = Inputter.getString("Input Product id you wanna remove",
                "That fied is required");
        //từ cái keyID tìm vị trí 
        int pos = this.searchCode(keyId.substring(0).toUpperCase());
        if (pos == -1) {
            System.out.println("Not found");
            return false;
        } else {
            System.out.println("Product information before removing");
            System.out.println(productsList.get(pos));
            boolean isRemove = true;
            isRemove = Inputter.readBool("Are you sure you want to Remove");
            if (isRemove == false) {
                System.out.println("Remove is failed");
            } else {
                productsList.remove(pos);
                System.out.println("Product remove is successful");
            }

        }
        return true;
    }

    @Override
    public void searchF() {
        
        System.out.println(searchCodeF());
    }

}
