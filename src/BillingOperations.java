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
 * (1) Enter / Update / BillingInformation
 * (3) View Billing Information based on Billing ID or Customer ID
 *
 * Source https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html
 *
 * NOTE: Billing ID's are automatically assigned when created associated
 * with a Customer ID
 *
 * @author Cosmo Pernie
 */

public class BillingOperations {

    /**
     * Enter a new Billing Information into the database
     *
     * New Billing ID's are automatically assigned after the database
     * has been Seeded
     *
     * Billing ID's are permanent once assigned
     */
    private static void enterBillingInformation() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Enter New Billing Information ------");

        System.out.print("\nCustomer ID: ");
        int customerId = in.nextInt();
        in.nextLine();

        System.out.print("\nSSN: ");
        int ssn = in.nextInt();
        in.nextLine();

        System.out.print("\nBilling Address: ");
        String billingAddress = in.nextLine();

        System.out.print("\nPayment Method: ");
        String paymentMethod = in.nextLine();

        System.out.println("Card Number: ");
        int cardNumber = in.nextInt();
        in.nextLine();

        // NOTE: Staff ID is automatically assigned in database (auto_increment)
        String sql = "INSERT INTO Billing(customerId, ssn, billingAddress, paymentMethod, cardNumber)" +
                " VALUES(?,?,?,?,?)";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.setInt(2, ssn);
            ps.setString(3, billingAddress);
            ps.setString(4, paymentMethod);
            ps.setInt(5, cardNumber);

            ps.execute();

            System.out.println("Billing record for Customer ID" + customerId + " has been added to the database.");

            //conn.close();
            //ps.close();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Update current Billing Information information
     *
     * Billing ID's are permanent once assigned, and can therefore
     * not be updated.
     */
    private static void updateBillingInformation() {

        Scanner in = new Scanner(System.in);
        String sql = "UPDATE Billing ";
        System.out.println("------ Update Billing Information ------\n");

        System.out.print("Enter Billing ID: ");
        int billingId = in.nextInt();
        in.nextLine();

        System.out.println("\n--- Choose Attribute to Update ---");
        System.out.println("1. Customer ID");
        System.out.println("2. SSN");
        System.out.println("3. Billing Address");
        System.out.println("4. Payment Method");
        System.out.println("4. Card Number");

        System.out.print("Attribute: ");
        String choice = in.nextLine();

        System.out.print("New Value: ");
        String entry = in.nextLine();

        try {
            switch (choice) {
                case "1":
                    sql += "SET customerId";
                    break;
                case "2":
                    sql += "SET ssn";
                    break;
                case "3":
                    sql += "SET billingAddress";
                    break;
                case "4":
                    sql += "SET paymentMethod";
                case "5":
                    sql += "SET cardNumber";
                default:
                    System.out.println("Invalid Input");
                    break;
            }
            sql += " = ? WHERE id = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, entry);
            ptmt.setInt(2, billingId);
            ptmt.execute();
            System.out.println("Billing ID " + billingId + " has been updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a Billing Information from the database
     */
    private static void deleteBillingInformation() {

        Scanner in = new Scanner(System.in);

        System.out.println("------ Delete Billing Information ------");
        System.out.println("Please enter Billing ID to delete.");
        System.out.print("\nBilling ID: ");

        int billingId = in.nextInt();
        in.nextLine();

        String sql = "DELETE FROM Billing WHERE id = " + billingId;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();

            System.out.println("Information associated with Billing ID  " + billingId + " has been " +
                    "deleted from the database.");

            //conn.close();
            //ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Display a specific Billing information by either Billing ID
     * or Customer ID
     */
    private static void viewBillingInformation() {

        System.out.println("------ View Billing Information ------");

        Scanner in = new Scanner(System.in);

        System.out.print("\nView Billing Information by (1) Customer ID or (2) Billing ID: ");
        int choice = in.nextInt();
        in.nextLine();

        String sql;
        if (choice == 1) {
            System.out.println("Enter Customer ID to select by: ");
            int customerId = in.nextInt();
            in.nextLine();
            sql = ("SELECT * FROM Billing WHERE customerId = " + customerId);
        } else {
            // choice == 2
            System.out.println("Enter Billing ID to select by: ");
            int billingId = in.nextInt();
            in.nextLine();
            sql = ("SELECT * FROM Billing WHERE id = " + billingId);
        }

        System.out.println("\nid | customerId | ssn | billingAddress | paymentMethod | cardNumber");

        try {

            Connection conn = DBConnection.getConnection();
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customerId");
                String ssn = rs.getString("ssn");
                String billingAddress = rs.getString("billingAddress");
                String paymentMethod = rs.getString("paymentMethod");
                int cardNumber = rs.getInt("cardNumber");

                System.out.println(id + " | " + customerId + " | " + ssn + " | " + billingAddress +
                        " | " + paymentMethod  + " | " + cardNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the Customer operations Menu
     */
    private static void printMenu() {

        System.out.println("\n====== Billing Menu ======\n");
        System.out.println("1. Enter New Billing Information");
        System.out.println("2. Update Billing Information");
        System.out.println("3. Delete Billing Information");
        System.out.println("4. View Billing Information");
        System.out.println("5. Return to Main Menu");
    }

    /**
     * Customer menu which is opened from the main menu in Menu.java.
     * Gives user access to all Customer functions, and communicates
     * with the database based on user input.
     */
    public static void openBillingMenu() {

        Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("\nEnter Selection: ");
            String choice = in.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    enterBillingInformation();
                    break;
                case "2":
                    updateBillingInformation();
                    break;
                case "3":
                    deleteBillingInformation();
                    break;
                case "4":
                    viewBillingInformation();
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
