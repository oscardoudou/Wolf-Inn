import java.sql.*;
import java.util.Scanner;

/**
 * Acknowledgments: This example is a modification of code provided
 * by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
 * Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)
 * <p>
 * This class manages all Staff information for the database
 * <p>
 * Operations:
 * (1) Enter / Update / Delete Staff Information
 * (2) Assign/deassign a staff member to a particular room (room service)
 * (3) View individual or all Staff members and information
 *
 * Source https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html
 *
 * @author Cosmo Pernie
 */

public class StaffOperations {

    /**
     * Enter a new Staff member into the database
     *
     * New Staff ID's are automatically assigned after the database
     * has been Seeded
     *
     * Staff ID's are permanent once assigned
     *
     *
     */
    private static void enterStaff() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Enter New Staff Information ------");

        System.out.print("\nName: ");
        String staffName = in.nextLine();

        System.out.print("\nAge: ");
        int age = in.nextInt();
        in.nextLine();

        System.out.print("\nTitle: ");
        String jobTitle = in.nextLine();

        System.out.print("\nHotel ID: ");
        int hotelId = in.nextInt();
        in.nextLine();

        System.out.print("\nDepartment: ");
        String department = in.nextLine();

        System.out.print("\nPhone Number: ");
        int phone = in.nextInt();
        in.nextLine();

        System.out.print("\nAddress: ");
        String address = in.nextLine();

        // NOTE: Staff ID is automatically assigned in database (auto_increment)
        String sql = "INSERT INTO Staff(name, age, title, hotelId, department, phone, address) " +
                "VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, staffName);
            ps.setInt(2, age);
            ps.setString(3, jobTitle);
            ps.setInt(4, hotelId);
            ps.setString(5, department);
            ps.setInt(6, phone);
            ps.setString(7, address);

            ps.execute();

            System.out.println("Staff member: " + staffName + " has been added to the database.");

            //conn.close();
            //ps.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Update current Staff member information
     *
     * Staff ID's are permanent once assigned, and can therefore
     * not be updated.
     */
    private static void updateStaff() {

        Scanner in = new Scanner(System.in);
        String sql = "UPDATE Staff ";
        System.out.println("------ Update Staff Information ------\n");

        System.out.print("Enter Staff ID: ");
        int staffId = in.nextInt();
        in.nextLine();

        System.out.println("\n--- Choose Attribute to Update ---");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Title");
        System.out.println("4. Hotel");
        System.out.println("5. Department");
        System.out.println("6. Phone");
        System.out.println("7. Address\n");

        System.out.print("Attribute: ");
        String choice = in.nextLine();

        System.out.print("New Value: ");
        String entry = in.nextLine();

        try {
            switch (choice) {
                case "1":
                    sql += "SET name";
                    break;
                case "2":
                    sql += "SET age";
                    break;
                case "3":
                    sql += "SET title";
                    break;
                case "4":
                    sql += "SET hotelId";
                    break;
                case "5":
                    sql += "SET department";
                    break;
                case "6":
                    sql += "SET phone";
                    break;
                case "7":
                    sql += "SET address";
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            sql += " = ? WHERE id = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, entry);
            ptmt.setInt(2, staffId);
            ptmt.execute();
            System.out.println("Staff ID " + staffId + "has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a Staff member from the database
     */
    private static void deleteStaff() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Delete Staff Information ------");
        System.out.println("Please enter Staff ID to delete.");
        System.out.print("\nStaff ID: ");

        int staffId = in.nextInt();
        in.nextLine();

        String sql = "DELETE FROM Staff WHERE id = " + staffId;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            System.out.println("Staff Member " + staffId + " has been deleted from the database.");

            //conn.close();
            //ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Assign a Staff member to a particular room
     */
    private static void assignStaff() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Assign Staff to Room ------");
        System.out.print("\nEnter Staff ID: ");
        int staffId = in.nextInt();
        in.nextLine();
        System.out.print("\nEnter Room ID: ");
        int roomId = in.nextInt();
        in.nextLine();

        String sql = ("UPDATE Staff SET assignedRoomId = " + roomId + " WHERE id = " + staffId);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("Staff ID" + staffId + " has been assigned to Room " + roomId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deassign a Staff member from a particular room
     */
    private static void deassignStaff() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Deassign Staff to Room ------");
        System.out.print("\nEnter Staff ID: ");
        int staffId = in.nextInt();
        System.out.print("\nEnter Room ID: ");
        int roomId = in.nextInt();

        String sql = ("UPDATE Staff SET assignedRoomId = NULL WHERE assignedRoomId = " +
                roomId + " AND id = " + staffId);
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            System.out.println("Staff ID " + staffId + " has been deassigned from Room " + roomId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display a specified Staff members information
     */
    private static void viewStaff() {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter Staff ID to View: ");
        int staffId = in.nextInt();
        in.nextLine();
        System.out.println("\nid | name | age | title | hotel | department | phone | address | room assignment");

        String sql = ("SELECT * FROM Staff WHERE id=" + staffId);
        try {

            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String staffName = rs.getString("name");
                int age = rs.getInt("age");
                String jobTitle = rs.getString("title");
                int hotelId = rs.getInt("hotelId");
                String department = rs.getString("department");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");
                int room = rs.getInt("assignedRoomId");

                System.out.println(id + " | " + staffName + " | " + age + " | " + jobTitle +
                        " | " + hotelId + " | " + department + " | " + phone + " | " + address +
                        " | " + room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display all Staff members and information
     */
    private static void viewAllStaff() {

        System.out.println("------ View All Staff Information ------");

        System.out.println("\nid | name | age | title | hotel | department | phone | address | room assignment");

        String sql = "SELECT * FROM Staff";
        try {

            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String staffName = rs.getString("name");
                int age = rs.getInt("age");
                String jobTitle = rs.getString("title");
                int hotelId = rs.getInt("hotelId");
                String department = rs.getString("department");
                int phone = rs.getInt("phone");
                String address = rs.getString("address");
                int room = rs.getInt("assignedRoomId");

                System.out.println(id + " | " + staffName + " | " + age + " | " + jobTitle +
                        " | " + hotelId + " | " + department + " | " + phone + " | " + address +
                        " | " + room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the Staff operations Menu
     */
    private static void printMenu() {

        System.out.println("\n====== Staff Menu ======\n");
        System.out.println("1. Enter New Staff Member");
        System.out.println("2. Update Staff Information");
        System.out.println("3. Delete Staff Member");
        System.out.println("4. View Staff Member Information");
        System.out.println("5. View All Staff Information");
        System.out.println("6. Assign Staff to Room");
        System.out.println("7. Deassign Staff from Room");
        System.out.println("8. Return to Main Menu");
    }

    /**
     * Staff menu which is opened from the main menu in Menu.java.
     * Gives user access to all staff functions, and communicates
     * with the database based on user input.
     */
    public static void openStaffMenu() {

        Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("\nEnter Selection: ");
            String choice = in.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    enterStaff();
                    break;
                case "2":
                    updateStaff();
                    break;
                case "3":
                    deleteStaff();
                    break;
                case "4":
                    viewStaff();
                    break;
                case "5":
                    viewAllStaff();
                    break;
                case "6":
                    assignStaff();
                    break;
                case "7":
                    deassignStaff();
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
