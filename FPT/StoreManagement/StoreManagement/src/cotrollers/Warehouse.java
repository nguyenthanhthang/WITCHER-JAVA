
package cotrollers;

import java.io.Serializable;
import java.util.List;

public class Warehouse implements Serializable{
    private String code;
    private String time;
    private List<Products> listProducts;

    public Warehouse() {
    }
    
    public Warehouse(String code, String time, List<Products> listProducts) { 
        this.code = code;
        this.time = time;
        this.listProducts = listProducts;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Products> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Products> listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public String toString() {
        String result = code+","+time;
        for (Products item : listProducts) {
            String pCode = item.getProductCode();
            result +=("," + pCode);
        }
        return result;
    }
    
}
