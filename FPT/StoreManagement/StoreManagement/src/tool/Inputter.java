/*
Inputter: là 1 cái khuôn 
nhưng anh không dùng nó để đúc object
anh dùng Inputter như 1 người đứng ra lưu trữ cái method 
phục vụ cho việc nhập và trách nhiệm cho các method đó

vì k đùng để đúc object nên các method, prop đều dùng static
 */
package tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Inputter {

    //props
    public static Scanner sc = new Scanner(System.in);

    //method phục vụ việc nhập chuẩn
    //method ép nhập số nguyên
    public static boolean getBoolean(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        boolean value = false;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = sc.nextLine();
                validInput = input.toLowerCase().matches("true|false");
                if (validInput) {
                    value = Boolean.parseBoolean(input);
                }
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }

        return value;
    }

    public static int getAnInteger(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //method ép nhập số nguyên trong khoảng
    public static int getAnInteger(String inpMsg, String errMsg,
            int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.println(inpMsg);
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine());
                if (number < lowerBound || number > upperBound) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //Viết hàm nhập vào số thực
    public static double getADouble(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        while (true) {
            try {
                double number = Double.parseDouble(sc.nextLine());
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //Viết hàm nhập số thực trong khoảng
    public static double getADouble(String inpMsg, String errMsg,
            Double lowerBound, Double upperBound) {
        if (lowerBound > upperBound) {
            Double tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        System.out.println(inpMsg);
        while (true) {
            try {
                double number = Double.parseDouble(sc.nextLine());
                if (number < lowerBound || number > upperBound) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //viết hàm nhập chuỗi, cấm rỗng 
    public static String getString(String inpMsg, String errMsg) {
        System.out.println(inpMsg);
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.isEmpty()) {//isEmpty==> không phải là null
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    //Viết hàm nhập chuỗi cấm rỗng mà phải theo yêu cầu
    public static String getString(String inpMsg, String errMsg, String regex) {
        System.out.println(inpMsg);
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.isEmpty() || !str.matches(regex)) {//isEmpty==> không phải là null
                    //nếu mày là rỗng hoặc không khớp regex thì xuống thow và chữi Ngu 
                    //chấm thang(!) có nghĩa là ngược lại từ khớp sang không khớp
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.out.println(errMsg);
            }
        }
    }

    public static boolean readBool(String msg) {
        String answer;
        System.out.println(msg + " [Y/N-1/0-T/F]: ");
        answer = sc.nextLine().trim();
        if (answer.isEmpty()) {
            return true;
        }
        Character c = answer.toUpperCase().charAt(0);
        return (c == 'Y' || c == '1' || c == 'T');
    }

    public static String checkBeforeDate(String msg) {
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        while (true) {
            String dateStr = getString(msg, msg);
            try {
                sdf.parse(dateStr);
            } catch (ParseException e) {
                System.err.println("Incorrect date ! Please enter again !");
                continue;
            }
            return dateStr;
        }

    }

    public static String checkAfterDate(String msg, String pd) {
        // Khởi tạo các biến
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        // Vòng lặp vô hạn
        while (true) {
            // Nhận ngày hết hạn từ người dùng
            String initDate = checkBeforeDate(msg);

            // Chuyển đổi ngày hết hạn thành đối tượng Date
            try {
                Date d1 = sdf.parse(initDate);

                // Chuyển đổi ngày sản xuất thành đối tượng Date
                Date d2 = sdf.parse(pd);

                // So sánh ngày hết hạn và ngày sản xuất
                if (d1.compareTo(d2) >= 0) {
                    // Ngày hết hạn lớn hơn hoặc bằng ngày sản xuất, kết thúc vòng lặp
                    return initDate;
                } else {
                    // Ngày hết hạn nhỏ hơn ngày sản xuất, hiển thị thông báo lỗi và nhắc người dùng nhập lại
                    System.out.println("Expiration date must large than production date ! Please enter again !");
                }
            } catch (ParseException ex) {
                // Ngày hết hạn không hợp lệ, hiển thị thông báo lỗi và nhắc người dùng nhập lại
                System.out.println("Incorrect date ! Please enter again !");
            }
        }
    }

    public static String checkSize(String inpMsg) {
        while (true) {
            String type = getString(inpMsg,"");

            if (!type.equals("Small") && !type.equals("Medium") && !type.equals("Large")) {
                System.err.println("Must input 1 in 3 size product is 'Small' or 'Medium' or 'Large' ! Please input again !");
                continue;
            }
            return type;
        }
    }
    
    public static <T> void saveListToFile(List<T> list, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(list);
            System.out.println("List saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving list to file: " + e.getMessage());
        }
    }

    /**
     * This method is used for loading binary file(list)
     *
     * @param <T>
     * @param fileName
     * @return
     */
    public static <T> void loadListFromFile(List<T> list, String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            List<T> loadedList = (List<T>) inputStream.readObject();
            list.addAll(loadedList);
            System.out.println("List loaded from file successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    

}
