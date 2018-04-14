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

// TODO: Figure out Date format in enterCustomer() and viewCustomer()

public class CustomerOperations {

    /**
     * Enter a new Customer member into the database
     *
     * New Customer ID's are automatically assigned after the database
     * has been Seeded
     *
     * Customer ID's are permanent once assigned
     */
    private static void enterCustomer() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Enter New Customer Information ------");

        System.out.print("\nName: ");
        String customerName = in.nextLine();

        System.out.print("\nDOB (YYYY-MM-DD): ");
        String dob = in.nextLine();

        System.out.print("\nPhone: ");
        String phone = in.nextLine();

        System.out.print("\nEmail: ");
        String email = in.nextLine();

        // NOTE: Staff ID is automatically assigned in database (auto_increment)
        String sql = "INSERT INTO Customer(name, dob, phone, email) VALUES(?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, customerName);
           // ps.setDate(2, dob);
            ps.setString(3, phone);
            ps.setString(4, email);

            ps.execute();

            System.out.println("Customer: " + customerName + " has been added to the database.");

            //conn.close();
            //ps.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Update current Customer information
     *
     * Customer ID's are permanent once assigned, and can therefore
     * not be updated.
     */
    private static void updateCustomer() {

        Scanner in = new Scanner(System.in);
        String sql = "UPDATE Customer ";
        System.out.println("------ Update Customer Information ------\n");

        System.out.print("Enter Customer ID: ");
        int customerId = in.nextInt();
        in.nextLine();

        System.out.println("\n--- Choose Attribute to Update ---");
        System.out.println("1. Name");
        System.out.println("2. DOB");
        System.out.println("3. Phone");
        System.out.println("4. Email");

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
                    sql += "SET dob";
                    break;
                case "3":
                    sql += "SET phone";
                    break;
                case "4":
                    sql += "SET email";
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            sql += " = ? WHERE id = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, entry);
            ptmt.setInt(2, customerId);
            ptmt.execute();
            System.out.println("Customer ID " + customerId + "has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a Customer member from the database
     */
    private static void deleteCustomer() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Delete Customer Information ------");
        System.out.println("Please enter Customer ID to delete.");
        System.out.print("\nCustomer ID: ");

        int customerId = in.nextInt();
        in.nextLine();

        String sql = "DELETE FROM Customer WHERE id = " + customerId;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            System.out.println("Customer Member " + customerId + " has been deleted from the database.");

            //conn.close();
            //ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display a specific Customer information
     */
    private static void viewCustomer() {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter Customer ID to View: ");
        int staffId = in.nextInt();
        in.nextLine();
        System.out.println("\nid | name | dob | phone | email");

        String sql = ("SELECT * FROM Customer WHERE id = " + staffId);
        try {

            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String customerName = rs.getString("name");
                int phone = rs.getInt("phone");
                Date age = rs.getDate("dob");
                String email = rs.getString("email");

                System.out.println(id + " | " + customerName + " | " + phone + " | " + age +
                        " | " + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the Customer operations Menu
     */
    private static void printMenu() {

        System.out.println("\n====== Customer Menu ======\n");
        System.out.println("1. Enter New Customer");
        System.out.println("2. Update Customer Information");
        System.out.println("3. Delete Customer");
        System.out.println("4. View Customer Information");
        System.out.println("5. Return to Main Menu");
    }

    /**
     * Customer menu which is opened from the main menu in Menu.java.
     * Gives user access to all Customer functions, and communicates
     * with the database based on user input.
     */
    public static void openCustomerMenu() {

        Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("\nEnter Selection: ");
            String choice = in.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    enterCustomer();
                    break;
                case "2":
                    updateCustomer();
                    break;
                case "3":
                    deleteCustomer();
                    break;
                case "4":
                    viewCustomer();
                    break;
                case "5":
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
