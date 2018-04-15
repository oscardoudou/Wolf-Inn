// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)

import java.sql.*;
import java.util.Scanner;

public class RoomOperation {
    public static void createRoom() {
        Scanner sc = null;
        System.out.println("Please enter a room number:");
        sc = new Scanner(System.in);
        int room_no = sc.nextInt();
        System.out.println("Please enter the room's hotel ID:");
        sc = new Scanner(System.in);
        int hotel_id = sc.nextInt();
        System.out.println("Please enter the room category:");
        sc = new Scanner(System.in);
        String category = sc.nextLine();
        System.out.println("Please enter the max occupancy:");
        sc = new Scanner(System.in);
        int occ = sc.nextInt();
        System.out.println("Please enter the nightly rate:");
        sc = new Scanner(System.in);
        double rate = sc.nextDouble();
        System.out.println("Please enter if the room is available: (1 for true and 0 for false)");
        sc = new Scanner(System.in);
        int avai = sc.nextInt();
        createRoom(room_no, hotel_id, category, occ, rate, avai);
    }

    public static void createRoom(int room_no, int hotel_id, String category, int occupa, double rate, int avail) {
        String sql = "insert into Room(room_no, hotel_id, category, max_occu, rate, avai) values(?,?,?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, room_no);
            ptmt.setInt(2, hotel_id);
            ptmt.setString(3, category);
            ptmt.setInt(4, occupa);
            ptmt.setDouble(5, rate);
            ptmt.setInt(6, avail);
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
        System.out.println("3.availability (enter 1 for true and 0 for false)");
        System.out.println("4.category");
        sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("Please input the new value for item " + choice + ":");
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
                case 4:
                    sql += "set category";
                    break;
                default:
                    System.out.println("!!!!! illegal input !!!!!!!!!");
                    break;
            }
            sql += " = ? where hotel_id = ? and room_no = ? ";
            //attention: belew statement shouldn't put before switch,
            // preparedstatement should create only after sql finished, or compile run ptmt have no index which lead to run time error
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, attr);
            ptmt.setInt(2, hotelID);
            ptmt.setInt(3, roomID);
            ptmt.execute();
            System.out.println("=== " + hotelID + "'s room " + roomID + " has been update!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRoom() {
        //leave input as ?
        String sql = "delete from Room where hotel_id = ? and room_no = ? ";
        System.out.println("\n=== Tell me the hotel id for which you are looking at: (e.g. 0");
        HotelOperation.showHotels();
        Scanner sc = new Scanner(System.in);
        int hotelID = sc.nextInt();
        System.out.println("\n=== Tell me which room (id) should be torn down:(e.g. 1)");
        sc = new Scanner(System.in);
        int roomID = sc.nextInt();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //use ?'s index to assign value
            ptmt.setInt(1, hotelID);
            ptmt.setInt(2, roomID);
            ptmt.execute();
            System.out.println("\n=== A room has been deleted! ===");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isRoomAvailable() {
        int count = 0;
        String sql = "where hotel_id = "; //? and avai = true ";
        System.out.println("\n=== Tell me the hotel id for which you are looking at: (e.g. 1");
        HotelOperation.showHotels();
        Scanner sc = new Scanner(System.in);
        int hotelID = sc.nextInt();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();;
            sql += hotelID + " and avai = 1";
            count = showRooms(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    public static boolean isRoomTypeAvailable() {
        int count = 0;
        String sql = "where hotel_id = "; //? and avai = true ";
        System.out.println("\n=== Tell me the hotel id for which you are looking at: (e.g. 1");
        HotelOperation.showHotels();
        Scanner sc = new Scanner(System.in);
        int hotelID = sc.nextInt();
        System.out.println("\n=== Tell me the room type for which you are looking at: (e.g. Deluxe");
        sc = new Scanner(System.in);
        String type = sc.nextLine();
        try {
            Connection conn = DBConnection.getConnection();
            Statement st = conn.createStatement();;
            sql += hotelID + " and avai = 1 and category = \"";
            sql += type + "\"";
            count = showRooms(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count > 0;
    }

    public static int showRooms(String tail) {
        ResultSet rs = null;
        int c = 0;
        Connection conn = DBConnection.getConnection();
        try {
            Statement  stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from Room "+tail);
            while (rs.next()) {
                //Retrieve by column name
                int room_id = rs.getInt("room_no");
                int hotel_name = rs.getInt("hotel_id");
                String type = rs.getString("category");
                int max_occu = rs.getInt("max_occu");
                double rate = rs.getDouble("rate");
                int avai = rs.getInt("avai");
                //Display values
                System.out.print("room_id: "+ room_id);
                System.out.print(", hotel_id: " + hotel_name);
                System.out.print(", category: " + type);
                System.out.print(", max_occu: " + max_occu);
                System.out.print(", rate: " + rate);
                System.out.println(", avai: " + avai);
                c ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
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

    public static void printMenu() {
        System.out.println("\n====== Room Menu ======\n");
        System.out.println("1. Create New Room");
        System.out.println("2. Update Room Information");
        System.out.println("3. Delete Room");
        System.out.println("4. View All Room Information");
        System.out.println("5. Check Room Availability");
        System.out.println("6. Check Room Type Availability");
        System.out.println("7. Deassign Staff from Room");
        System.out.println("8. Return to Main Menu");
    }

    public static void openRoomMenu() {

        Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("\nEnter Selection: ");
            String choice = in.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    createRoom();
                    break;
                case "2":
                    updateRoom();
                    break;
                case "3":
                    deleteRoom();
                    break;
                case "4":
                    String sql = "";
                    showRooms(sql);
                    break;
                case "5":
                    isRoomAvailable();
                    break;
                case "7":
                    isRoomTypeAvailable();
                    break;
                case "8":
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid Entry");
                    break;
            }
        }
        //in.close();
    }
}
