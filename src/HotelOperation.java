// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)

import java.sql.*;

public class HotelOperation {
    private static String user = "yzhan222";
    private static String passwd = "jlp^zcl*";
    static private final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/yzhan222";

    public static void createHotel(String name, String address, String city, long phone, int manager_id) {
        String sql = "insert into Hotel(name, address, city, phone, manager_id) values(?,?,?,?,?)";
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, user, passwd);
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, address);
            ptmt.setString(3, city);
            ptmt.setLong(4, phone);
            ptmt.setInt(5, manager_id);
            ptmt.execute();
            System.out.println("A new hotel has been created!");
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }
    public static void run() {
        try {

            // Load the driver. This creates an instance of the driver
            // and calls the registerDriver method to make MariaDB Thin
            // driver, available to clients.

           // Class.forName("org.mariadb.jdbc.Driver");
            Connection conn = DBConnection.getConnection();
           Statement stmt = null;
           ResultSet rs = null;

            try {

                // Get a connection from the first driver in the
                // DriverManager list that recognizes the URL jdbcURL

                conn = DriverManager.getConnection(jdbcURL, user, passwd);

                // Create a statement object that will be sending your
                // SQL statements to the DBMS

                stmt = conn.createStatement();

                // Drop existed tables
                stmt.executeUpdate("Drop TABLE IF EXISTS Service_Request");
                stmt.executeUpdate("Drop TABLE IF EXISTS Billing_info");
                stmt.executeUpdate("Drop TABLE IF EXISTS Check_in");
                stmt.executeUpdate("Drop TABLE IF EXISTS Room");
                stmt.executeUpdate("Drop TABLE IF EXISTS Hotel");
                stmt.executeUpdate("Drop TABLE IF EXISTS Customer");
                stmt.executeUpdate("Drop TABLE IF EXISTS Staff");
                // Create the tables
//
                stmt.executeUpdate("CREATE TABLE Staff (id   INTEGER NOT NULL PRIMARY KEY,name    VARCHAR(20) NOT NULL,rank     VARCHAR(20) NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Hotel (id INTEGER NOT NULL PRIMARY KEY auto_increment,name VARCHAR(20) NOT NULL,address VARCHAR(50) NOT NULL,city VARCHAR(10) NOT NULL,phone INTEGER UNIQUE,manager_id INTEGER NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Customer (id  INTEGER NOT NULL PRIMARY KEY,name VARCHAR(20) NOT NULL,dob  DATE NOT NULL,phone INTEGER,email VARCHAR(20) )");
                stmt.executeUpdate("CREATE TABLE Room (room_id integer PRIMARY KEY, hotel_id integer NOT NULL, max_occu integer(40) NOT NULL, rate float NOT NULL, avai boolean NOT NULL, foreign key(hotel_id) REFERENCES Hotel(id))");
                stmt.executeUpdate("CREATE TABLE Billing_info(id INTEGER NOT NULL PRIMARY KEY,ssn INTEGER NOT NULL,payment_type VARCHAR(20) NOT NULL,card_number  INTEGER NOT NULL,hotel_card BOOLEAN NOT NULL,check_in BOOLEAN NOT NULL,room_id INTEGER NOT NULL,customer_id INTEGER NOT NULL,FOREIGN KEY(room_id)REFERENCES Room(room_id),FOREIGN KEY(customer_id)REFERENCES Customer(id)    )");
                stmt.executeUpdate("CREATE TABLE Check_in(id INTEGER PRIMARY KEY NOT NULL,start_date DATE NOT NULL,end_date DATE NOT NULL,guestCnt INTEGER NOT NULL,customer_id INTEGER NOT NULL,room_id INTEGER NOT NULL,foreign key(room_id) REFERENCES Room(room_id),foreign key(customer_id) REFERENCES Customer(id))");
                stmt.executeUpdate("CREATE TABLE Service_Request (service_request_id integer NOT NULL PRIMARY KEY, room_id integer NOT NULL, submitter_id integer NOT NULL, customer_id integer NOT NULL, type varchar(10) NOT NULL, complete boolean NOT NULL, date varchar(40) NOT NULL, cost float NOT NULL, FOREIGN KEY(room_id)REFERENCES Room(room_id),FOREIGN KEY (submitter_id )REFERENCES Staff(id),FOREIGN KEY (customer_id)REFERENCES Customer (id))");
//                // Populate the tables
//
                createHotel("hotel1","1343 Huston Dr.","Cary",54556421, 1010);
//                //stmt.executeUpdate("INSERT INTO Room VALUES ('161', 6, 120.00, False)");
//
//                stmt.executeUpdate("INSERT INTO Customer values('24235667', 'James Harrison', '1992-01-21', '435234114', 'james24@ro.com')");
//                stmt.executeUpdate("INSERT INTO Customer values('45345511', 'Ford Rex', '1987-04-30', '923211432', 'fox@fox.com')");
//                stmt.executeUpdate("INSERT INTO Customer values('87543123', 'Bill Fredson', '1987-11-28', '902343451', 'soing@haha.com')");
//                stmt.executeUpdate("INSERT INTO Customer values('23467572', 'Henry Lacon', '1965-12-03', '245351120', 'urf23@aol.com')");
//
//                stmt.executeUpdate("INSERT INTO Staff values('10021', 'Jon Doe', 'manager')");
//                stmt.executeUpdate("INSERT INTO Staff values('23251', 'Jack Chris', 'manager')");
//                stmt.executeUpdate("INSERT INTO Staff values('18007', 'Jim Blarr', 'front desk')");
//                stmt.executeUpdate("INSERT INTO Staff values('23212', 'Mary Lynch', 'support')");
//                stmt.executeUpdate("INSERT INTO Staff values('43231', 'Jane DeVoe', 'billing')");
//
//                stmt.executeUpdate("INSERT INTO Hotel values('2342112', 'hotel1', '1010 billing st', 'Cary', '324231311', '10021')");
//                stmt.executeUpdate("INSERT INTO Hotel values('2127686', 'hotel2', '221 Caroll Dr', 'Memphis', '345423652', '23251')");
//                stmt.executeUpdate("INSERT INTO Hotel values('6574222', 'hotel3', '5542 Dell St.', 'Austin', '234531763', '23251')");
//                stmt.executeUpdate("INSERT INTO Hotel values('7677832', 'hotel4', '1121 Portland Ave', 'Seattle', '645654112', '10021')");
//
//                stmt.executeUpdate("INSERT INTO Room VALUES ('101', '7677832', 4, 100.00, TRUE)");
//                stmt.executeUpdate("INSERT INTO Room VALUES ('102', '7677832', 2, 50.00, TRUE )");
//                stmt.executeUpdate("INSERT INTO Room VALUES ('201', '2127686', 4, 100.00, FALSE)");
//                stmt.executeUpdate("INSERT INTO Room VALUES ('202', '6574222', 2, 50.00, FALSE)");
//                stmt.executeUpdate("INSERT INTO Room VALUES ('301', '6574222', 1, 35.00, FALSE)");
//
//                stmt.executeUpdate("INSERT INTO Billing_info values('2334235','283434', 'visa', '4533454', 0, 1, '101', '24235667')");
//                stmt.executeUpdate("INSERT INTO Billing_info values('3224345', '213434', 'master', '5644543', 1, 1, '102', '45345511')");
//                stmt.executeUpdate("INSERT INTO Billing_info values('2343454', '372132', 'visa', '5675545', 0, 1, '201','87543123')");
//                stmt.executeUpdate("INSERT INTO Billing_info values('2454233', '452134', 'master', '5434231', 1, 0, '301', '23467572')");
//
//                stmt.executeUpdate("INSERT INTO Service_Request VALUES ('001','101','10021','24235667','room_ser', '1', '23', '54.5')");
//                stmt.executeUpdate("INSERT INTO Check_in values('13142345', '2019-01-03', '2019-01-05', 2, '23467572', '201')");
//                stmt.executeUpdate("INSERT INTO Check_in values('53645243', '2019-02-09', '2019-02-12', 3, '23467572', '202')");
//                stmt.executeUpdate("INSERT INTO Check_in values('24315564', '2017-09-12', '2017-09-13', 1, '87543123', '102')");
//                stmt.executeUpdate("INSERT INTO Check_in values('78653235', '2017-11-02', '2017-11-07', 1, '45345511', '101')");
            } finally {
                close(rs);
                close(stmt);
                close(conn);
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