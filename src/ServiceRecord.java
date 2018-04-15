import java.sql.*;
import java.util.Scanner;

/**
 * Acknowledgments: This example is a modification of code provided
 * by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
 * Relpace all yzhan222 with your unity id and jlp^zcl* with your 9 \d or updated password (if changed)
 * <p>
 * This class manages all Service Record information for the database
 * <p>
 * Operations:
 * (1) Create / Update Service Record Information
 * (2)
 * (3)
 *
 * Source https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html
 *
 * @author Yichi Zhang
 */

public class ServiceRecord {
    public static int service_record_id = 0;
    public static void enterServiceRec(){
        Scanner sc = new Scanner(System.in);

        System.out.println("-----Enter Service Record Information-------");
        System.out.println("ServiceRecord:" +  service_record_id++);
        System.out.println("type:(gyms, phone, dry cleaning, room service)");
        String type = sc.nextLine();
        System.out.println("CheckinID:");
        int checkin_id = sc.nextInt();
        System.out.println("HotelID:");
        int hotel_id = sc.nextInt();
        System.out.println("Room No.:");
        int room_id = sc.nextInt();
        System.out.println("CustomerID:");
        int customer_id = sc.nextInt();
        System.out.println("SubmitterID");
        int submitter_id = sc.nextInt();
        System.out.print("Date:");
        String date = sc.nextLine();
        System.out.println("\nCost:");
        float cost = sc.nextFloat();
        boolean complete = false;

        String sql = "insert into Service_Record(service_record_id, type, checkin_id, hotel_id, room_id, customer_id, submitter_id, date, cost, complete) value(?,?,?,?,?,?,?,?,?,?)";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt =  conn.prepareStatement(sql);
            ptmt.setInt(1,service_record_id);
            ptmt.setString(2,type);
            //to do
            ptmt.setInt(3,checkin_id);
            ptmt.setInt(4,hotel_id);
            ptmt.setInt(5,room_id);
            ptmt.setInt(6,customer_id);
            ptmt.setInt(7,submitter_id);
            ptmt.setString(8,date);
            ptmt.setFloat(9,cost);
            ptmt.setBoolean(10, complete);
            ptmt.execute();
            System.out.println("ServiceRecord:"+service_record_id + "has been requested");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void retrieveServiceRec(){
        System.out.println("Input CustomerID:");
        Scanner sc = new Scanner(System.in);
        int customer_id = sc.nextInt();
        System.out.println("Input RoomID:");
        int room_id = sc.nextInt();
        //"CREATE TABLE Service_Record (service_record_id integer NOT NULL PRIMARY KEY auto_increment, checkin_id integer NOT NULL, room_id integer NOT NULL, hotel_id integer NOT NULL, submitter_id integer NOT NULL, customer_id integer NOT NULL, type varchar(10) NOT NULL, complete boolean NOT NULL, date varchar(40) NOT NULL, cost float NOT NULL)");

        ResultSet rs = null;
        String sql = "select * from Service_Record where customer_id = ? and room_id = ?";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1,customer_id);
            ptmt.setInt(2,room_id);
            rs = ptmt.executeQuery();
            //rs.next()是针对多行结果的判断
            while(rs.next()){
                int service_record_id = rs.getInt("service_record_id");
                int checkin_id = rs.getInt("checkin_id");
                String type = rs.getString("type");
                int hotel_id = rs.getInt("hotel_id");
//                int room_id = rs.getInt("room_id");
//                int customer_id = rs.getInt("customer_id");
                int submitter_id = rs.getInt("submitter_id");
                String date = rs.getString("date");
                float cost = rs.getFloat("cost");
                boolean complete = rs.getBoolean("complete");

                System.out.println("Retrieveing result from ServiceRecord for Room: "+room_id+" Customer: "+customer_id);
                System.out.println("service_record_id:"+service_record_id);
                System.out.println("checkin_id:"+ checkin_id);
                System.out.println("hotel_id:"+hotel_id);
                System.out.println("submitter_id:"+submitter_id);
                System.out.println("date:"+date);
                System.out.println("cost:"+cost);
                System.out.println("complete:"+complete);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
