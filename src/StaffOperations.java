import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Acknowledgments: This example is a modification of code provided
 * by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
 * Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)
 * <p>
 * This class manages all Staff information for the Hotel database
 * <p>
 * Necessary Operation:
 * (1) Enter / Update / Delete Staff Information
 * (2) Assign/deassign a staff member to a particular room (room service)
 *
 * @author Cosmo Pernie
 */

public class StaffOperations {

    /**
     * Enter a new Staff member into the database
     */
    private static void enterStaff() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Enter New Staff Information ------");

        System.out.print("\nName: ");
        String staffName = in.nextLine();

        System.out.print("\nAge: ");
        int age = in.nextInt();

        System.out.print("\nTitle: ");
        String jobTitle = in.nextLine();

        System.out.print("\nHotel ID: ");
        int hotelId = in.nextInt();

        System.out.print("\nDepartment: ");
        String department = in.nextLine();

        System.out.print("\nPhone Number: ");
        int phone = in.nextInt();

        System.out.print("\nAddress: ");
        String address = in.nextLine();

        // NOTE: Staff ID is automatically assigned in database (auto_increment)
        String sql = "INSERT INTO Staff(name, age, title, hotelId, department, phone, address) " +
                "VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);

            ptmt.setString(1, staffName);
            ptmt.setInt(2, age);
            ptmt.setString(3, jobTitle);
            ptmt.setInt(4, hotelId);
            ptmt.setString(5, department);
            ptmt.setInt(6, phone);
            ptmt.setString(7, address);

            ptmt.execute();

            System.out.println("Staff member: " + staffName + "has been added to the database.");

            conn.close();
            ptmt.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }

        in.close();
    }

    /**
     * Update current Staff member information
     */
    private static void updateStaff() {

        System.out.println("------ Update Staff Information ------");


        System.out.print("Staff ID #: ");
        //int staffId = in.nextInt();


    }

    /**
     * Delete a Staff member from the database
     */
    private static void deleteStaff() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Delete Staff Information ------");
        System.out.println("Please enter Staff ID to delete.");
        System.out.print("\nStaff ID: ");

        String staffId = in.nextLine();

        String sql = "DELETE FROM Staff WHERE id = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, staffId);
            ptmt.execute();

            System.out.println("Staff Member " + staffId + " has been deleted from the database.");

            conn.close();
            ptmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        in.close();
    }

    /**
     * Assign a Staff member to a particular room
     */
    private static void assignStaff() {

    }

    /**
     * Deassign a Staff member from a particular room
     */
    private static void deassignStaff() {

    }

    /**
     * Display a specified Staff members information
     */
    private static void viewStaff() {

    }

    /**
     * Display all Staff members and information
     */
    private static void viewAllStaff() {

    }

    /**
     * Staff menu which is opened from the main menu in Menu.java.
     * Gives user access to all staff functions, and communicates
     * with the database based on user input.
     */
    public static void openStaffMenu() {

        Scanner in = new Scanner(System.in);

        System.out.println("\n====== Staff Menu ======");
        System.out.println("1. Enter New Staff Member");
        System.out.println("2. Update Staff Information");
        System.out.println("3. Delete Staff Member");
        System.out.println("4. View Staff Member Information");
        System.out.println("5. View All Staff Information");
        System.out.println("6. Assign Staff to Room");
        System.out.println("7. Deassign Staff from Room");
        System.out.println("8. Return to Main Menu");

        while (true) {

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
                    return;
                case "7":
                    deassignStaff();
                    return;
                case "8":
                    in.close();
                    return;
                default:
                    System.out.println("Invalid Entry.");
                    break;
            }
        }
    }
}
