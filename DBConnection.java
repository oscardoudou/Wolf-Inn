import java.sql.*;

public class DBConnection {
    private static final String user = "yzhan222";
    private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/" + user;
    private static String passwd = "jlp^zcl*";
    private static Connection conn = null;

    public static void initialize(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                conn = DriverManager.getConnection(jdbcURL, user, passwd);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            }
    }
    public static void close(){
        if(conn != null){
            try{
                conn.close();
            }
            catch(Throwable whatever){}
        }
    }
}
