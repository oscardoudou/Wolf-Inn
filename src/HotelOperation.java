import java.sql.*;
import java.util.Scanner;

/**
 * Acknowledgments: This example is a modification of code provided
 * by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
 * Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)
 * <p>
 * This class manages all Hotel information for the database
 * <p>
 * Operations:
 * (1) Create / Update / Delete / Show Hotel Information
 * (2)
 * (3)
 *
 * Source https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html
 *
 * @author Yichi Zhang
 */

public class HotelOperation {
//    private static String user = "yzhan222";
//    private static String passwd = "jlp^zcl*";
//    static private final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/yzhan222";


    public static void printMenu(){
        System.out.println("1.Create hotel");
        System.out.println("2.Delete hotel");
        System.out.println("3.Show hotel");
        System.out.println("4.Update hotel");
        System.out.println("5.Return to Main Menu");
    }
    public static void openMenu(){
        Scanner sc = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("\nEnter Selection: ");
            String choice = sc.nextLine();

            switch (choice){
                case "1":
                    createHotel();
                    break;
                case "2":
                    deleteHotel();
                    break;
                case "3":
                    showHotels();
                    break;
                case "4":
                    updateHotel();
                    break;
                case "5":
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid Entry");
                    break;

            }
        }

    }


    public static void createHotel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----Enter Hotel Information-------");
        System.out.println("name:");
        String name = sc.nextLine();
        System.out.println("address:");
        String address = sc.nextLine();
        System.out.println("city");
        String city = sc.nextLine();
        System.out.println("phone");
        long phone = sc.nextLong();
        System.out.println("manager_id");
        int manager_id = sc.nextInt();

        String sql = "insert into Hotel(name, address, city, phone, manager_id) values(?,?,?,?,?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, address);
            ptmt.setString(3, city);
            ptmt.setLong(4, phone);
            ptmt.setInt(5, manager_id);
            ptmt.execute();
            System.out.println("A new hotel has been created!");
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    public static void deleteHotel() {
        //leave input as ?
        String sql = "delete from Hotel where name = ? ";
        System.out.println("Tell me which hotel you hate:(eg.hotel0)");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //use ?'s index to assign value
            ptmt.setString(1, input);
            //uncomment follow statement would throw exception
            //ptmt.setString(2,name);
            ptmt.execute();
            System.out.println("A hotel has been deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateHotel() {
        String sql = "update Hotel ";
//                "name = ? where name = ?";
        System.out.println("Please input which hotel you want to update:(eg.hotel0)");
        Scanner sc = new Scanner(System.in);
        String hotelname = sc.nextLine();
        System.out.println("Please select the attribute you want to  update from 4 choice below:");
        System.out.println("1.name");
        System.out.println("2.address");
        System.out.println("3.city");
        System.out.println("4.phone");
        System.out.println("5.managerid");
        String choice = sc.nextLine();
        System.out.println("Please input the new attribtue you want to give:");
        String attr = sc.nextLine();
        try {
            Connection conn = DBConnection.getConnection();
            switch (choice) {
                case "1":
                    sql += "set name";
                    break;
                case "2":
                    sql += "set address";
                    break;
                case "3":
                    sql += "set city";
                    break;
                case "4":
                    sql += "set phone";
                    break;
                case "5":
                    sql += "set manager_id";
                    break;
                default:
                    System.out.println("illegal input");
                    break;
            }
            sql += " = ? where name = ? ";
            //attention: below statement shouldn't put before switch,
            //preparedStatement should create only after sql finished, or compile run ptmt have no index which lead to run time error
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, attr);
            ptmt.setString(2, hotelname);
            ptmt.execute();
            System.out.println(hotelname + " has been update!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showHotels() {
        ResultSet rs = null;
        Connection conn = DBConnection.getConnection();
        try {
            Statement  stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from Hotel");
            while (rs.next()) {
                //Retrieve by column name
                int hotel_id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String City = rs.getString("city");
                String phone = rs.getString("phone");
                int manager_id = rs.getInt("manager_id");
                //Display values
                System.out.print("ID: " + hotel_id);
                System.out.print(", name: " + name);
                System.out.print(", address: " + address);
                System.out.print(", city: " + City);
                System.out.print(", phone: " + phone);
                System.out.println(", manager_id: " + manager_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
