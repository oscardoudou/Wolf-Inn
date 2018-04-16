import java.util.Scanner;
import java.sql.*;

public class OccupancyReport {
    public static void getHotelOccupancy(){
        String sql = "select hotel_id, count(*) as occupancy from Room where avai = 0 group by hotel_id";
        Connection conn =  DBConnection.getConnection();
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("----------------------------------");
            while(rs.next()){
                int id = rs.getInt("hotel_id");
                int occupancy = rs.getInt(2);
                System.out.println("Hotel ID: " + id + ", occupied rooms #: " + occupancy);
            }
            System.out.println("----------------------------------");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void getRoomTypeOccupancy(){
        String sql = "select category, count(*) as occupancy from Room where avai = 0 group by category";
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("-----------------------------------");
            while(rs.next()){
                String category = rs.getString(1);
                int occupancy = rs.getInt("occupancy");
                System.out.println("Category: "+ category + ", occupied room #: " + occupancy);
            }
            System.out.println("----------------------------------");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void getCityOccupancy(){
        String sql = "select city, count(*) as occupancy from Room, Hotel where Room.hotel_id = Hotel.id and avai = 0 group by city";
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("----------------------------------");
            while(rs.next()){
                String city = rs.getString(1);
                int occupancy = rs.getInt("occupancy");
                System.out.println("City: "+ city + ", occupied room #: " + occupancy);
            }
            System.out.println("----------------------------------");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

