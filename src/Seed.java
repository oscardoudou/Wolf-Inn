import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Seed {
    public static void seed() {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = null;
            ResultSet rs = null;
            try {
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
                stmt.executeUpdate("CREATE TABLE Staff (id   INTEGER NOT NULL PRIMARY KEY auto_increment, name    VARCHAR(20) NOT NULL,rank     VARCHAR(20) NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Hotel (id INTEGER NOT NULL PRIMARY KEY auto_increment,name VARCHAR(20) NOT NULL,address VARCHAR(50) NOT NULL,city VARCHAR(10) NOT NULL,phone INTEGER UNIQUE,manager_id INTEGER NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Customer (id  INTEGER NOT NULL PRIMARY KEY auto_increment,name VARCHAR(20) NOT NULL,dob  DATE NOT NULL,phone INTEGER,email VARCHAR(20) )");
                stmt.executeUpdate("CREATE TABLE Room (room_id integer PRIMARY KEY auto_increment, hotel_id integer NOT NULL, category VARCHAR(20) NOT NULL, max_occu integer(40) NOT NULL, rate float NOT NULL, avai boolean NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Billing_info(id INTEGER PRIMARY KEY auto_increment,ssn INTEGER NOT NULL,payment_type VARCHAR(20) NOT NULL, card_number INTEGER NOT NULL, hotel_card BOOLEAN NOT NULL,check_in BOOLEAN NOT NULL,room_id INTEGER NOT NULL,customer_id INTEGER NOT NULL)");
                stmt.executeUpdate("CREATE TABLE Check_in(id INTEGER PRIMARY KEY NOT NULL auto_increment,start_date DATE NOT NULL,end_date DATE NOT NULL,guestCnt INTEGER NOT NULL,customer_id INTEGER NOT NULL,room_id INTEGER NOT NULL,foreign key(room_id) REFERENCES Room(room_id))");
                stmt.executeUpdate("CREATE TABLE Service_Request (service_request_id integer NOT NULL PRIMARY KEY auto_increment, room_id integer NOT NULL, submitter_id integer NOT NULL, customer_id integer NOT NULL, type varchar(10) NOT NULL, complete boolean NOT NULL, date varchar(40) NOT NULL, cost float NOT NULL)");

                // Enter table information based on demo data





            } finally {
                close(rs);
                close(stmt);
                // close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
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
