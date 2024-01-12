package dto;

/* Interface for a group of objects */
public interface I_List {
    
    // Find the position of element which has code equal parameter code
    public int find(String code);
    
    // Print out the table header without num
    public void printHeader();
    
    // Print out the table with num
    public void printHeaderWithNum();
    
    // Add new element( input from scanner) to I_List
    public void add();
    
    // Input the code wanna remove
    public void remove();
    
    // Input the code want to update, after that update other information--> use set method
    public void update();
    
    // Sort list use Collections.sort, sort based price
    public void sortByPrice();
    
    // Sort list use Collections.sort, sort based quantity
    public void sortByQuantity();
    
    // Show detail of each element of List
    public void show();

    // Load data from file
    public void loadDataFromFile(String fileName);
    
    // Save product list information to file (product.dat)
    public void saveDataToFile(String fileName);
        
}
