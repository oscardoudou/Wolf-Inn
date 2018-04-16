import java.util.Scanner;
import java.sql.*;

public class OccupancyReport {
    public static void getHotelOccupancy(){
        String sql = "select hotel_id, count(*) from Room where avai = 0 group by hotel_id";
        Connection conn =  DBConnection.getConnection();
        try{
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            System.out.println("---------------------");
            while(rs.next()){
                int id = rs.getInt("hotel_id");
                int occupancy = rs.getInt("2");
                System.out.println("Hotel ID: " + id + ", occupied rooms #: " + occupancy);
            }
            System.out.println("---------------------");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
