import java.sql.*;
import java.util.Scanner;

/**
 * Acknowledgments: This example is a modification of code provided
 * by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
 * Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)
 * <p>
 * This class manages all OccupancyReport information for the database
 * <p>
 * Operations:
 * (1) Get Occupancy by Hotel
 * (2) Get Occupancy by Room Category
 * (3) Get Occupancy by City
 * (4) Get Occupancy by DateRange
 *
 * Source https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html
 *
 * @author Yichi Zhang
 */

public class OccupancyReport {

    public static void printMenu(){
        System.out.println("1.Get Occupancy by Hotel");
        System.out.println("2.Get Occupancy by Room Category");
        System.out.println("3.Get Occupancy by City");
        System.out.println("4.Get Occupancy by DateRange");
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
                    getHotelOccupancy();
                    break;
                case "2":
                    getRoomTypeOccupancy();
                    break;
                case "3":
                    getCityOccupancy();
                    break;
                case "4":
                    getDataRangeOccupancy();
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

    public static void getHotelOccupancy(){
        String sql = "select hotel_id, count(*) as occupancy from Room where avai = 0 group by hotel_id";
        Connection conn =  DBConnection.getConnection();
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("----------------------------------");
            while(rs.next()){
                int id = rs.getInt("hotel_id");
                int occupancy = rs.getInt(2);
                System.out.println("Hotel ID: " + id + ", occupied rooms #: " + occupancy);
            }
            System.out.println("----------------------------------");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void getRoomTypeOccupancy(){
        String sql = "select category, count(*) as occupancy from Room where avai = 0 group by category";
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("-----------------------------------");
            while(rs.next()){
                String category = rs.getString(1);
                int occupancy = rs.getInt("occupancy");
                System.out.println("Category: "+ category + ", occupied room #: " + occupancy);
            }
            System.out.println("----------------------------------");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void getCityOccupancy(){
        String sql = "select city, count(*) as occupancy from Room, Hotel where Room.hotel_id = Hotel.id and avai = 0 group by city";
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("----------------------------------");
            while(rs.next()){
                String city = rs.getString(1);
                int occupancy = rs.getInt("occupancy");
                System.out.println("City: "+ city + ", occupied room #: " + occupancy);
            }
            System.out.println("----------------------------------");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void getDataRangeOccupancy(){
        Scanner sc = new Scanner(System.in);
        String choice = null;
        while(true){
            System.out.println("Please input start date(YYYY-MM-DD)");
        }

    }
}

