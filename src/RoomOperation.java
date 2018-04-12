// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)

import java.sql.*;
import java.util.Scanner;

public class RoomOperation {

    public static void createRoom(int hotel_name, String category, int occupa, double rate, boolean avail) {
        String sql = "insert into Room(hotel_name, category, max_occu, rate, avai) values(?,?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, hotel_name);
            ptmt.setString(2, category);
            ptmt.setInt(3, occupa);
            ptmt.setDouble(4, rate);
            ptmt.setBoolean(5, avail);
            ptmt.execute();
            System.out.println("A new room has been created!");
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }
    public static void updateRoom() {
        String sql = "update Room ";
        System.out.println("Please input which hotel you want to update:(eg. 0)");
        Scanner sc = new Scanner(System.in);
        int hotelID = sc.nextInt();
        System.out.println("Please input which room you want to update:(eg. 0)");
        sc = new Scanner(System.in);
        int roomID = sc.nextInt();
        System.out.println("Please select an item you want to update:");
        System.out.println("1.max occupancy");
        System.out.println("2.rate");
        System.out.println("3.availability");
        sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("Please input the new value for " + choice + ":");
        sc = new Scanner(System.in);
        String attr = sc.nextLine();
        try {
            Connection conn = DBConnection.getConnection();
            switch (choice) {
                case 1:
                    sql += "set max_occu";
                    break;
                case 2:
                    sql += "set rate";
                    break;
                case 3:
                    sql += "set avai";
                    break;
                default:
                    System.out.println("illegal input");
                    break;
            }
            sql += " = ? where hotel_name = ? and room_id = ? ";
            //attention: belew statement shouldn't put before switch,
            // preparedstatement should create only after sql finished, or compile run ptmt have no index which lead to run time error
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, attr);
            ptmt.setInt(2, hotelID);
            ptmt.setInt(3, roomID);
            ptmt.execute();
            System.out.println(hotelID + "'s room " + roomID + " has been update!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRoom() {
        //leave input as ?
        String sql = "delete from Room where hotel_name = ? and room_id = ? ";
        System.out.println("Tell me the hotel id for which you are looking at: (e.g. 0");
        HotelOperation.showHotels();
        Scanner sc = new Scanner(System.in);
        int hotelID = sc.nextInt();
        System.out.println("Tell me which room (id) should be torn down:(e.g. 1)");
        sc = new Scanner(System.in);
        int roomID = sc.nextInt();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //use ?'s index to assign value
            ptmt.setInt(1, hotelID);
            ptmt.setInt(2, roomID);
            ptmt.execute();
            System.out.println("A room has been deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showRooms() {
        ResultSet rs = null;
        Connection conn = DBConnection.getConnection();
        try {
            Statement  stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from Room");
            while (rs.next()) {
                //Retrieve by column name
                int room_id = rs.getInt("room_id");
                int hotel_name = rs.getInt("hotel_name");
                String type = rs.getString("category");
                int max_occu = rs.getInt("max_occu");
                double rate = rs.getDouble("rate");
                boolean avai = rs.getBoolean("avai");
                //Display values
                System.out.print("room_id: "+ room_id);
                System.out.print(", hotel_name: " + hotel_name);
                System.out.print(", category: " + type);
                System.out.print(", max_occu: " + max_occu);
                System.out.print(", rate: " + rate);
                System.out.println(", avai: " + avai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = null;
            ResultSet rs = null;
            try {
                stmt = conn.createStatement();
                createRoom(1, "Deluxe",2, 78.43, true);
                createRoom(1, "Presidential",3, 98.99, false);
            } finally {
                close(rs);
                close(stmt);
                // close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}
