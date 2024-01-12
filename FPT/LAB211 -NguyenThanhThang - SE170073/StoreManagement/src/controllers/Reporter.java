package controllers;

import dto.A_Receipt;
import dto.I_Report;
import dto.Product;
import java.util.ArrayList;
import java.util.HashMap;
import utils.Utils;

public class Reporter implements I_Report{

    @Override
    public void showProductsExpired(ProductList productList) {
        if (productList.isEmpty())
        {
            System.out.println("Error: The product list is empty!");
            return;
        }
        
        ArrayList<Product> expiredList = new ArrayList<>();
        for (Product p: productList)
        {
            if (Utils.dateGreaterThan(p.getManufacturingDate(), p.getExpirationDate(), ProductList.DATE_FORMAT))
            {
                expiredList.add(p);
            }
        }
        if (expiredList.isEmpty())
        {
            System.out.println("No product has expired!");
        }else{
            System.out.println("---> " +expiredList.size()+" products have been expired: ");
            productList.printHeaderWithNum();
            for (int i=0;i<expiredList.size();i++)
            {
                System.out.printf(" %3d", (i+1));
                System.out.println(expiredList.get(i));
            }
        }
        
        System.out.println("");
    }

    @Override
    public void showProductsSelling(ProductList productList) {
        if (productList.isEmpty())
        {
            System.out.println("Error: The product list is empty!");
            return;
        }
        
        ArrayList<Product> sellingList = new ArrayList<>();
        for (Product p: productList)
        {
            if (Utils.dateLOET(p.getManufacturingDate(), p.getExpirationDate(), ProductList.DATE_FORMAT))
            {
                sellingList.add(p);
            }
        }
        if (sellingList.isEmpty())
        {
            System.out.println("No product is selling!");
        }else{
            System.out.println("");
            System.out.println("---> "+sellingList.size() + " products are selling: ");
            productList.printHeaderWithNum();
            for (int i=0;i<sellingList.size();i++)
            {
                System.out.printf(" %3d", (i+1));
                System.out.println(sellingList.get(i));
            }
        }
        
        System.out.println("");
    }

    @Override
    public void showProductsROS(ProductList productList) {
        if (productList.isEmpty())
        {
            System.out.println("Error: The product list is empty!");
            return;
        }
        System.out.println("");
        productList.sortByQuantity();
        
        ArrayList<Product> productROS = new ArrayList<>();
        
        for (Product p: productList)
        {
            if (Utils.dateLOET(p.getManufacturingDate(), p.getExpirationDate(), ProductList.DATE_FORMAT))
            {
                productROS.add(p);
            }
        }
        
        if (productROS.isEmpty())
        {
            System.out.println("No product is selling!");
        }else{
            System.out.println("---> "+productROS.size() + " products that are running out of stock: ");
            productList.printHeaderWithNum();
            for (int i=0;i<productROS.size();i++)
            {
                System.out.printf(" %3d", (i+1));
                System.out.println(productROS.get(i));
            }
        }
        
        System.out.println("");
        
    }

    @Override
    public void showReceiptOfAProduct(ProductList productList, Warehouse warehouse) {
        if (productList.isEmpty())
        {
            System.out.println("Error: The product list is empty!");
            return;
        }
        String productCode = Utils.getSringPattern("Enter code for report: ", ProductList.CODE_FORMAT, "Invalid code!!!");
        if (productList.find(productCode)<0)
        {
            System.out.println("Product does not exist!");
        }else{
            Product reportProduct = productList.get(productList.find(productCode));
            if (warehouse.isEmpty() || !reportProduct.isGenerated())
            {
                System.out.println("No import/export receipt has been generated!");
            }else{
                
                ArrayList<String> receiptType = new ArrayList<>();
                ArrayList<Integer> quantityState = new ArrayList<>();
                ArrayList<String> createTime = new ArrayList<>();
                ArrayList<Integer> receiptCode = new ArrayList<>();
                for (HashMap.Entry<Integer, A_Receipt> entry : warehouse.entrySet()) {
                    A_Receipt receipt = entry.getValue();
                    int intReCode = entry.getKey() % 10000000;
                    if (receipt instanceof ImportReceipt)
                    {
                        for ( String p: ((ImportReceipt) receipt).getProducts().keySet())
                        {
                            if (productCode.equalsIgnoreCase(p))
                            {
                                receiptCode.add(intReCode);
                                receiptType.add("Import");
                                quantityState.add(((ImportReceipt) receipt).getProducts().get(p));
                                createTime.add(((ImportReceipt) receipt).getCreateTime());
                                break;
                            }
                        }
                    }else{
                        for ( String p: ((ExportReceipt) receipt).getProducts().keySet())
                        {
                            if (productCode.equalsIgnoreCase(p))
                            {
                                receiptCode.add(intReCode);
                                receiptType.add("Export");
                                quantityState.add(((ExportReceipt) receipt).getProducts().get(p));
                                createTime.add(((ExportReceipt) receipt).getCreateTime());
                                break;
                            }
                        }
                    }
                    
                }
                
                System.out.println("");
                System.out.println("Receipt of product "+ productCode + " - " + reportProduct.getName() +" : ");
                System.out.printf("| %3s | %-12s | %-12s | %8s | %11s |\n","Num","Receipt Code","Receipt Type","Quantity","Create Time");
                for (int i=0;i<receiptType.size();i++)
                {
                    System.out.printf("| %3s |    %07d   |    %-9s | %8s | %11s |\n", (i+1),receiptCode.get(i), receiptType.get(i), quantityState.get(i), createTime.get(i));
                }
                
            }
        }
        System.out.println("");
        
    }

}
