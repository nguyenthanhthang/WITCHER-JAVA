package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {

    //Get a string is not empty
    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    //Get a string, if it is empty, return oldData argument
    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    //Get an integer in range min to max
    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number! ["+min+".."+max+"]");
            }
        } while (check || number > max || number < min);
        return number;
    }

    //Get an integer in range min to max, if it is empty, return oldData argument
    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    //Get an double number int range min to max
    public static double getDouble(String welcome, double min, double max) {
        boolean check = true;
        double number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.out.println("Input number! ["+min+".."+max+"]");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    //Get an double number int range min to max, if it is empty, return oldData argument
    public static double getDouble(String welcome, double min, double max, double oldData) {
        boolean check = true;
        double number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    //Get confirm yes or no from user, could not be blank
    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
    
    // Get a string with pattern
    public static String getSringPattern(String welcome, String pattern, String error)
    {
        String aString = null;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print(welcome);
                aString = sc.nextLine().trim();
                if (!aString.matches(pattern)) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println(error);
                count = true;
            }
        } while (count);
        return aString;
    }
    
    // Get a string with pattern, return oldData argument if input is empty
    public static String getSringPattern(String welcome, String pattern, String error, String oldData)
    {
        String aString = oldData;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print(welcome);
                String s = sc.nextLine().trim();
                if (s.isEmpty())
                {
                    count=false;
                }else{
                    if (!s.matches(pattern)) throw new Exception();
                    aString = s;
                    count = false;
                }
            } catch (Exception e) {
                System.out.println(error);
                count = true;
            }
        } while (count);
        return aString;
    }
    
    //Checking valid of a date with a format argument
    private static boolean isValidDate(String date, String format)
    {
        try 
        {
            LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
            return true;
        }
        catch (Exception e )
        {
            return false;
        }
    }
    
    // Return true if start date less than or equal to end date
    public static boolean dateLOET(String startDate, String endDate, String format)
    {
        boolean result=false;
        if (startDate.equals(endDate)) return true;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            result = sdf.parse(startDate).before(sdf.parse(endDate));
        } catch (ParseException e) {
            System.out.println("Can not compare 2 dates!");
        }
        return result;
    }
    
    // Return true if start date greather end date
    public static boolean dateGreaterThan(String startDate, String endDate, String format)
    {
        boolean result=true;
        if (startDate.equals(endDate)) return false;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            result = sdf.parse(startDate).before(sdf.parse(endDate));
        } catch (ParseException e) {
            System.out.println("Can not compare 2 dates!");
        }
        return !result;
    }
    
    // Get a valid date
    public static String getDate(String welcome, String format)
    {
        String date = null;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print(welcome);
                date = sc.nextLine().trim();
                if (!isValidDate(date, format)) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("Invalid date!!! " +format);
                count = true;
            }
        } while (count);
        return date;
    }
    
    // Get a valid date
    public static String getDate(String welcome, String format, String oldData)
    {
        String date = oldData;
        boolean count = false;
        do {            
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print(welcome);
                date = sc.nextLine().trim();
                if (date.isEmpty()) return oldData;
                if (!isValidDate(date, format)) throw new Exception();
                count = false;
            } catch (Exception e) {
                System.out.println("Invalid date!!! " +format);
                count = true;
            }
        } while (count);
        return date;
    }
    
}
