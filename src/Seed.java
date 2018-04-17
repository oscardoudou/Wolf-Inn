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
                // change Service_Request to Service_Record;
                stmt.executeUpdate("Drop TABLE IF EXISTS Service_Record");
                stmt.executeUpdate("Drop TABLE IF EXISTS Check_in");
                stmt.executeUpdate("Drop TABLE IF EXISTS Room");
                stmt.executeUpdate("Drop TABLE IF EXISTS Hotel");
                stmt.executeUpdate("Drop TABLE IF EXISTS Customer");
                stmt.executeUpdate("Drop TABLE IF EXISTS Staff");
                stmt.executeUpdate("Drop TABLE IF EXISTS Billing");

                // Create and Populate the Tables

                stmt.executeUpdate("CREATE TABLE Hotel (id INTEGER NOT NULL PRIMARY KEY auto_increment," +
                        "name VARCHAR(20) NOT NULL," +
                        "address VARCHAR(50) NOT NULL," +
                        "city VARCHAR(10) NOT NULL," +
                        "phone INTEGER UNIQUE," +
                        "manager_id INTEGER NOT NULL)");

                stmt.executeUpdate("CREATE TABLE Room (room_no INTEGER not null, " +
                        "hotel_id integer NOT NULL, " +
                        "category VARCHAR(20) NOT NULL, " +
                        "max_occu integer NOT NULL, " +
                        "rate float NOT NULL, " +
                        "avai INTEGER NOT NULL)");
//                stmt.executeUpdate("CREATE TABLE Check_in(id INTEGER PRIMARY KEY NOT NULL auto_increment," +
//                        "start_date DATE NOT NULL,end_date DATE NOT NULL,guestCnt INTEGER NOT NULL," +
//                        "customer_id INTEGER NOT NULL,room_no INTEGER NOT NULL,foreign key(room_no) REFERENCES Room(room_no))");
                //------------------------------------------------------------------------------------------------------
                //Create Service_Record Table @author Yichi Zhang
                stmt.executeUpdate("CREATE TABLE Service_Record (service_record_id integer NOT NULL PRIMARY KEY auto_increment, " +
                        "checkin_id integer NOT NULL, " +
                        "staff_id integer NOT NULL, " +
                        "service_name varchar(20) NOT NULL, " +
                        "fee integer NOT NULL)");
                stmt.execute("insert into Service_Record(checkin_id, staff_id, service_name, fee)" + "values(1,101,'phone',5)");
                stmt.execute("insert into Service_Record(checkin_id, staff_id, service_name, fee)" + "values(2,101,'gyms',20)");
                stmt.execute("insert into Service_Record(checkin_id, staff_id, service_name, fee)" + "values(3,102,'dry cleaning',25)");
                stmt.execute("insert into Service_Record(checkin_id, staff_id, service_name, fee)" + "values(4,103,'room service',30)");
                stmt.execute("insert into Service_Record(checkin_id, staff_id, service_name, fee)" + "values(5,104,'gyms',50)");

                //------------------------------------------------------------------------------------------------------
                // Create Staff Table @author Cosmo Pernie
                // assignedRoomId can be NULL if not Staff not assigned
                stmt.executeUpdate("CREATE TABLE Staff (id INTEGER NOT NULL PRIMARY KEY auto_increment, " +
                        "name VARCHAR(20) NOT NULL, " +
                        "age INTEGER NOT NULL, " +
                        "title VARCHAR(20) NOT NULL, " +
                        "hotelId INTEGER NOT NULL, " +
                        "department VARCHAR(20) NOT NULL, " +
                        "phone INTEGER NOT NULL, " +
                        "address VARCHAR(50) NOT NULL, " +
                        "assignedRoomId INTEGER)");

                // Create Customer Table @author Cosmo Pernie
                stmt.executeUpdate("CREATE TABLE Customer (id INTEGER NOT NULL PRIMARY KEY auto_increment, " +
                        "name VARCHAR(20) NOT NULL, " +
                        "dob DATE NOT NULL, " +
                        "phone INTEGER NOT NULL, " +
                        "email VARCHAR(20) NOT NULL)");
                        

                // Create Billing Table @author Cosmo Pernie
                // cardNumber can be NULL through cash option
                stmt.executeUpdate("CREATE TABLE Billing (id INTEGER NOT NULL PRIMARY KEY auto_increment, " +
                        "customerId INTEGER NOT NULL, " +
                        "ssn VARCHAR(20) NOT NULL, " +
                        "billingAddress VARCHAR(50) NOT NULL, " +
                        "paymentMethod VARCHAR(20) NOT NULL, " +
                        "cardNumber INTEGER)");

stmt.executeUpdate("CREATE TABLE Check_in(id INTEGER PRIMARY KEY NOT NULL auto_increment,start_date DATE NOT NULL,end_date DATE NOT NULL,number_of_guests INTEGER NOT NULL,customer_id INTEGER NOT NULL,hotel_id INTEGER NOT NULL,room_number INTEGER NOT NULL,check_in_time TIME NOT NULL,check_out_time TIME NOT NULL,services_offered VARCHAR(40) NOT NULL)");

                // Enter Staff Data from Demo Data @author Cosmo Pernie
                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (100, 'Mary', 40, 'Manager', 0001, 'Management', 654, " +
                        "'90 ABC St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (101, 'John', 45, 'Manager', 0002, 'Management', 564, " +
                        "'798 XYZ St , Rochester NY 54')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (102, 'Carol', 55, 'Manager', 0003, 'Management', 546, " +
                        "'351 MH St , Greensboro NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (103, 'Emma', 55, 'Front Desk Staff', 0001, 'Management', 546, " +
                        "'49 ABC St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (104, 'Ava', 55, 'Catering Staff', 0001, 'Catering', 777, " +
                        "'425 RG St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (105, 'Peter', 52, 'Manager', 0004, 'Management', 724, " +
                        "'475 RG St , Raleigh NC 27')");

                stmt.executeUpdate("INSERT INTO Staff (id, name, age, title, hotelId, department, phone, " +
                        "address) VALUES (106, 'Olivia', 27, 'Front Desk Staff', 0004, 'Management', 799, " +
                        "'325 PD St , Raleigh NC 27')");

                // Enter Customer Data from Demo Data @author Cosmo Pernie
                stmt.executeUpdate("INSERT INTO Customer (id, name, dob, phone, email) VALUES (1001, 'David', " +
                        "'1980-01-30', 123, 'david@gmail.com')");

                stmt.executeUpdate("INSERT INTO Customer (id, name, dob, phone, email) VALUES (1002, 'Sarah', " +
                        "'1971-01-30', 456, 'sarah@gmail.com')");

                stmt.executeUpdate("INSERT INTO Customer (id, name, dob, phone, email) VALUES (1003, 'Joseph', " +
                        "'1987-01-30', 789, 'joseph@gmail.com')");

                stmt.executeUpdate("INSERT INTO Customer (id, name, dob, phone, email) VALUES (1004, 'Lucy', " +
                        "'1985-01-30', 213, 'lucy@gmail.com')");

                // Enter Billing Data from Demo Data @author Cosmo Pernie
                stmt.executeUpdate("INSERT INTO Billing (customerId, ssn, billingAddress, paymentMethod, " +
                        "cardNumber) VALUES (1001, '593-9846', '980 TRT St , Raleigh NC', 'credit', 1052)");

                stmt.executeUpdate("INSERT INTO Billing (customerId, ssn, billingAddress, paymentMethod, " +
                        "cardNumber) VALUES (1002, '777-8352', '7720 MHT St , Greensboro NC', 'hotel credit', 3020)");

                stmt.executeUpdate("INSERT INTO Billing (customerId, ssn, billingAddress, paymentMethod, " +
                        "cardNumber) VALUES (1003, '858-9430', '231 DRY St , Rochester NY 78', 'credit', 2497)");

                stmt.executeUpdate("INSERT INTO Billing (customerId, ssn, billingAddress, paymentMethod) " +
                        "VALUES (1004, '440-9328', '24 BST Dr , Dallas TX 14', 'cash')");

                //------------------------------------------------------------------------------------------------------

                stmt.executeUpdate("insert into Hotel(name, address, city, phone, manager_id) " +
                        "values('Hotel A', '21 ABC St Raleigh NC 27', 'Raleigh', 919, 100)");
                stmt.executeUpdate("insert into Hotel(name, address, city,  phone, manager_id) " +
                        "values('Hotel B', '25 XYZ St Rochester NY 54', 'Rochester', 718, 101)");
                stmt.executeUpdate("insert into Hotel(name, address, city, phone, manager_id) " +
                        "values('Hotel C', '29 PQR St Greensboro NC 27', 'Greensboro', 984, 102)");
                stmt.executeUpdate("insert into Hotel(name, address, city, phone, manager_id) " +
                        "values('Hotel D', '28 GHW St Raleigh NC 32', 'Greensboro', 920, 105)");

                stmt.executeUpdate("insert into Room (room_no, hotel_id, category, max_occu, rate, avai) " +
                        "values(1, 1, 'Economy', 1, 100, 1)");
                stmt.executeUpdate("insert into Room(room_no, hotel_id, category, max_occu, rate, avai) " +
                        "values(2, 1, 'Deluxe', 2, 200, 1)");
                stmt.executeUpdate("insert into Room(room_no, hotel_id, category, max_occu, rate, avai) " +
                        "values(3, 2, 'Economy', 1, 100, 1)");
                stmt.executeUpdate("insert into Room(room_no, hotel_id, category, max_occu, rate, avai) " +
                        "values(2, 3, 'Executive', 3, 1000, 0)");
                stmt.executeUpdate("insert into Room(room_no, hotel_id, category, max_occu, rate, avai) " +
                        "values(1, 4, 'Presidential', 4, 5000, 1)");
                stmt.executeUpdate("insert into Room(room_no, hotel_id, category, max_occu, rate, avai) " +
                        "values(5, 1, 'Deluxe', 2, 200, 1)");
  //  ------------------------------------------------------------------------------
           stmt.executeUpdate("INSERT INTO Check_in (customer_id, hotel_id, room_number, number_of_guests, start_date, " +
                        "end_date,check_in_time,check_out_time,services_offered) VALUES (1001, 1, 1, 1, '2017-05-10', '2017-05-13', '3:17:00', " +
                        " '10:22:23','dry cleaning, gyms')");

                stmt.executeUpdate("INSERT INTO Check_in (customer_id, hotel_id, room_number,  number_of_guests, start_date, " +
                        "end_date,check_in_time,check_out_time,services_offered) VALUES (1002, 1, 2, 2, '2017-05-10', '2017-05-13', '3:17:00', " +
                        " '10:22:23','gyms')");
                stmt.executeUpdate("INSERT INTO Check_in (customer_id, hotel_id, room_number, number_of_guests, start_date, " +
                        "end_date,check_in_time,check_out_time,services_offered) VALUES (1003, 2, 3, 1, '2016-05-10', '2016-05-14', '3:17:00', " +
                        " '10:22:23','room service')");

                stmt.executeUpdate("INSERT INTO Check_in (customer_id, hotel_id, room_number,  number_of_guests, start_date, " +
                        "end_date,check_in_time,check_out_time,services_offered) VALUES (1004, 3, 3, 2, '2018-05-10', '2018-05-12', '3:17:00', " +
                        " '10:22:23','phone bills')");


            } finally {
                close(rs);
                close(stmt);
                // close(conn);
            }
        } catch (Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Throwable whatever) {
            }
        }
    }

    static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Throwable whatever) {
            }
        }
    }
}
