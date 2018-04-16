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
    //public static int service_record_id = 0;
    public static void enterServiceRec(){
        Scanner sc = new Scanner(System.in);

        System.out.println("-----Enter Service Record Information-------");
        //System.out.println("ServiceRecord:" +  service_record_id++);
        System.out.println("Service Name:(gyms, phone, dry cleaning, room service)");
        String service_name = sc.nextLine();
        System.out.println("CheckinID:");
        int checkin_id = sc.nextInt();
        System.out.println("StaffID");
        int staff_id = sc.nextInt();
        System.out.println("Fee:");
        float fee = sc.nextFloat();
        //boolean complete = false;

        String sql = "insert into Service_Record(service_name, checkin_id, staff_id, fee) value(?,?,?,?)";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt =  conn.prepareStatement(sql);
            //ptmt.setInt(1,service_record_id);
            ptmt.setString(1, service_name);
            //to do
            ptmt.setInt(2,checkin_id);
            ptmt.setInt(3,staff_id);
            ptmt.setFloat(4,fee);
            ptmt.execute();
            //System.out.println("ServiceRecord:"+service_record_id + "has been requested");
            System.out.println("ServiceRecord has been requested");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateServiceRec(){
        System.out.println("Input CustomerID:");
        Scanner sc = new Scanner(System.in);
        int customer_id = sc.nextInt();
        System.out.println("Input RoomID:");
        int room_id = sc.nextInt();
        String sql = "update Service_Record ";
        //Again why we use loop to update?
        //Because we don't know which several attr you want to update, for those you dont wanna change, you need give default value
        //which is impossible when use sql string mechanism like (att1, attr2, att3) (?,?,?)
        System.out.println("Please select the attribute you want to  update from 3 choice, below:(only input number)");
        System.out.println("1.Date");
        System.out.println("2.Cost");
        System.out.println("3.Complete");
        //probably has unexpected scan here if last sc = sc.nextInt();
        //if last readin is sc.nextLine() as well, probably has no issue here, refer to hotel update operation
        sc = new Scanner(System.in);
        String choice = sc.nextLine();
        System.out.println(choice);
        sc = new Scanner(System.in);
        String attr_s = null;
        float attr_f = 0;
        Boolean attr_b = false;
        System.out.print("Please input the new attribtue you want to give:(complete should be true or fasle)");
        switch (choice) {
            case "1":
                sql += "set date";
                attr_s = sc.nextLine();
                break;
            case "2":
                sql += "set cost";
                attr_f = sc.nextFloat();
                break;
            case "3":
                sql += "set complete";
                attr_b = sc.nextBoolean();
                break;
            default:
                System.out.println("illegal input");
                break;
        }

        try {
            Connection conn = DBConnection.getConnection();

            sql += " = ? where customer_id = ? and room_id = ? ";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            switch (choice) {
                case "1":
                    ptmt.setString(1,attr_s);
                    break;
                case "2":
                    ptmt.setFloat(1,attr_f);
                    break;
                case "3":
                    ptmt.setBoolean(1,attr_b);
                    break;
                default:
                    System.out.println("choice doestn't make sense");
                    break;
            }
            //ptmt.setString(1,attr);
            ptmt.setInt(2,customer_id);
            ptmt.setInt(3,room_id);
            ptmt.execute();
        System.out.println("Service Record has been updated!");
        } catch(SQLException e){
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
