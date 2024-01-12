package dto;

/* Interface for menu */
 public interface I_Menu {
     
   // Add a menu item--> add text to menu
   void addItem(String s);
   
   // Get user choice( user input their choice)
   int getChoice();
   
   // Show menu for user choice
   void showMenu();
   
   // Confirm yes/ no (Y/N)
   boolean confirmYesNo(String welcome);
   
}
