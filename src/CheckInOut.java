import java.sql.*;
import java.util.Scanner;

public class CheckInOut {


    public static void CheckIn()
    {



        try{
 //Create check-in record
            String sql = "insert into Check_in(customer_id, hotel_id, room_number, number_of_guests, start_date, end_date, check_in_time, check_out_time, services_offered) values(?,?,?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter following information to create a check_in/out record for customer");
            System.out.println("----------------------------------");
            System.out.println("Enter customer ID:");
            ptmt.setInt(1, sc.nextInt());
            System.out.println("Enter hotel ID:");
            ptmt.setInt(2, sc.nextInt());
            System.out.println("Enter room number:");
            ptmt.setInt(3, sc.nextInt());
            System.out.println("Enter number of guests:");
            ptmt.setInt(4, sc.nextInt());
             sc = new Scanner(System.in);
            System.out.println("Enter start date (format:2018-04-12):");
            ptmt.setString(5,sc.nextLine() );
            System.out.println("Enter end date (format:2018-04-12):");
            ptmt.setString(6,sc.nextLine() );
            System.out.println("Enter check-in time (format:08:23:12 ):");
            ptmt.setString(7,sc.nextLine() );
            System.out.println("Enter check-out time (format: 21:23:12 ):");
            ptmt.setString(8,sc.nextLine() );
            System.out.println("Enter services offered:");
            ptmt.setString(9,sc.nextLine() );

// print out created check-in record
            if(ptmt.executeUpdate()>0) {
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * from Check_in");
                ResultSetMetaData rsmd = resultSet.getMetaData();
                resultSet.last();
                int columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " " + columnValue);
                }
                System.out.println("");

            }




        }

        catch (SQLException e) {
            e.printStackTrace();
        }



        }






    public static void updateCheckIn()


    {

    try
    {

        int hotelID;
        int roomNum;
        String checkInid;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ptmt;
        String updateCheckIn = "update Check_in set ";
        String selectCheckIn="SELECT * from Check_in";
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter hotel id and room number to find CheckIn information (eg: 001,203) "); // select the check-in information we want to update
        String hrnum = sc.nextLine();
        sc=new Scanner(hrnum).useDelimiter("[^0-9]+");
        hotelID=sc.nextInt();
        roomNum=sc.nextInt();
        selectCheckIn +=" where hotel_id= ? and room_number= ? ";
        ptmt=conn.prepareStatement(selectCheckIn);
        ptmt.setInt(1,hotelID);
        ptmt.setInt(2,roomNum);
        ResultSet resultSet =  ptmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        resultSet.last();
        checkInid=resultSet.getString(1);
        int columnsNumber = rsmd.getColumnCount();
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) System.out.print(",  ");
            String columnValue = resultSet.getString(i);
            System.out.print(rsmd.getColumnName(i) + " " + columnValue);
        }
        System.out.println("");

        System.out.println("Please select one or more items you want to update(format: 1,2,4) ");
        System.out.println("1.customer_id");
        System.out.println("2.hotel_id");
        System.out.println("3.room_number");
        System.out.println("4.number_of_guests");
        System.out.println("5.start_date");
        System.out.println("6.end_date");
        System.out.println("7.checkIn_time");
        System.out.println("8.checkOut_time");
        System.out.println("9.service_offered");
        
        // choose attributes to update and enter new values

        ptmt=conn.prepareStatement(updateCheckIn);
        Scanner scnum=new Scanner(System.in);
        String chosenNumber=scnum.nextLine();
        scnum=new Scanner(chosenNumber).useDelimiter("[^0-9]+");

        String updateEelement="";
        while(scnum.hasNextInt())
        {

            sc=new Scanner(System.in);


              switch (scnum.nextInt())

              {

                  case 1:
                      updateCheckIn+=" customer_id= ?,";
                      System.out.println("enter new customer_id:");
                      updateEelement+=sc.nextLine();
                      updateEelement+=",";
                      break;

                  case 2:
                      updateCheckIn+="  hotel_id= ?,";
                      System.out.println("enter new hotel_id:");
                      updateEelement+=sc.nextLine();
                      updateEelement+=",";
                      break;

                  case 3:
                      updateCheckIn+="  room_number= ?,";
                      System.out.println("enter new room_number:");
                     updateEelement+=sc.nextLine();
                      updateEelement+=",";
                      break;

                  case 4:
                  updateCheckIn+="  number_of_guests= ?,";
                  System.out.println("enter new number_of_guests:");
                  updateEelement+=sc.nextLine();
                 updateEelement+=",";
                  break;
                 
                  case 5:
                  updateCheckIn+="  start_date= ?,";
                  System.out.println("enter new start_date:");
                  updateEelement+=sc.nextLine();
                   updateEelement+=",";
                  break;

                  case 6:
                  updateCheckIn+="  end_date= ?,";
                  System.out.println("enter new end_date:");
                  updateEelement+=sc.nextLine();
                  updateEelement+=",";
                  break;

                  case 7:
                  updateCheckIn+="  check_in_time= ?,";
                  System.out.println("enter new check_in_time:");
                  updateEelement+=sc.nextLine();
                  updateEelement+=",";
                  break;

                  case 8:
                  updateCheckIn+=" check_out_time= ?,";
                  System.out.println("enter new check_out_time:");
                  updateEelement+=sc.nextLine();
                  updateEelement+=",";
                  break;

                  case 9:
                  updateCheckIn+="services_offered= ?,";
                  System.out.println("add new services_offered:");
                  updateEelement+=sc.nextLine();
                  updateEelement+=",";
                  break;

               
                }




          


        }


              updateCheckIn=updateCheckIn.substring(0, updateCheckIn.lastIndexOf(","));
              updateCheckIn+=" where hotel_id = ? and room_number = ? ";
              ptmt=conn.prepareStatement(updateCheckIn);
              scnum=new Scanner(updateEelement).useDelimiter(", *");
              int j=0;
              while(scnum.hasNext())
             {
                 j++;
                ptmt.setString(j,scnum.next());



              }

              ptmt.setInt(++j,hotelID);
              ptmt.setInt(++j,roomNum);


  // print out updated check-in information

            if(ptmt.executeUpdate()>0) {
                System.out.println("udate successfully");
                String updatedCheckIn="SELECT * from Check_in where id=?";
                PreparedStatement pt=conn.prepareStatement(updatedCheckIn);
                pt.setString(1,checkInid);
                resultSet = pt.executeQuery();
                resultSet.next();
                 rsmd = resultSet.getMetaData();
                 columnsNumber = rsmd.getColumnCount();
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " " + columnValue);
                }
                System.out.println("");

            }






    } catch (SQLException e) {
        e.printStackTrace();
    }




    }



  public static void checkOut()
  
  {


 

  





  }


  
    
  














        public static void showCheckIn()
    {



        try{
            Connection conn = DBConnection.getConnection();
            Statement  stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from Check_in");
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " " +columnValue  );
                }
                System.out.println("");
            }




        }


        catch (SQLException e) {
            e.printStackTrace();
        }




    }


    public static void selfcreatecheckin()
    {

        try {
            String sql = "insert into Check_in(customer_id, hotel_id, room_number, number_of_guests, start_date, end_date, check_in_time, check_out_time, services_offered) values(?,?,?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,"23");
            ptmt.setString(2,"21");
            ptmt.setString(3,"203");
            ptmt.setString(4,"2");
            ptmt.setString(5,"2018-03-21");
            ptmt.setString(6,"2018-03-21");
            ptmt.setString(7,"21:21:21");
            ptmt.setString(8,"21:21:21");
            ptmt.setString(9,"gym");
            ptmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
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
