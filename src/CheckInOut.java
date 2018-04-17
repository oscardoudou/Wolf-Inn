import java.sql.*;
import java.util.Scanner;

public class CheckInOut {




public static void printMenu() {

        System.out.println("\n====== CheckInOut Menu ======\n");
        System.out.println("1. Enter CheckIn Information");
        System.out.println("2. Update CheckIn Information");
        System.out.println("3. process checkout(return total amount and receipt) ");
        System.out.println("4. return hotel revenue");
        System.out.println("5. Staff Serving list");
        System.out.println("6. Show CheckIn list");
        System.out.println("7. Assign Room");
        System.out.println("8. Release Room");
        System.out.println("9. Return to Main Menu");
    }




 
public static void OpenCheckInoutmenu()
{


  Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("\nEnter Selection: ");
            String choice = in.nextLine();
            System.out.println();

            switch (choice) {
                case "1":
                    CheckIn();
                    break;
                case "2":
                    updateCheckIn();
                    break;
                case "3":
                    checkOut();
                    break;
                case "4":
                   revenue();
                    break;
                case "5":
                   staffservinglist();
                    break;
                case "6":
                    showCheckIn();
                    break;
                case "7":
                    AssignRoom();
                    break;
                case "8":
                    ReleaseRoom();
                    break;
                case "9":
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid Entry");
                    break;
            }
        }
        //in.close();
    }



  public static void AssignRoom()
  {
     
        
try{
    Connection conn = DBConnection.getConnection();
    String assgnroom="update Room set avai=0 where hotel_id= ? and room_no= ?";
    PreparedStatement ptmt = conn.prepareStatement(assgnroom);
    System.out.println("Please enter hotel id and room number to assign room (eg: 001,203) ");
    Scanner sc = new Scanner(System.in);
    String hrnum = sc.nextLine();
    sc=new Scanner(hrnum).useDelimiter("[^0-9]+");
     ptmt.setInt(1, sc.nextInt());
      ptmt.setInt(2, sc.nextInt());

      if(ptmt.executeUpdate()>0)
      {

        System.out.println("room has been assigned");
   
      }





 }
        catch (SQLException e) {
            e.printStackTrace();
        }


       
 




  }


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

                System.out.println("created check-in:");

                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT * from Check_in ORDER BY ID DESC LIMIT 1");
                ResultSetMetaData rsmd = resultSet.getMetaData();
                resultSet.next();
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
        String selectCheckIn="SELECT * from Check_in ";
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter hotel id and room number to find CheckIn information (eg: 001,203) "); // select the check-in information we want to update
        String hrnum = sc.nextLine();
        sc=new Scanner(hrnum).useDelimiter("[^0-9]+");
        hotelID=sc.nextInt();
        roomNum=sc.nextInt();
        selectCheckIn +=" where hotel_id= ? and room_number= ? ORDER BY ID DESC LIMIT 1";
        ptmt=conn.prepareStatement(selectCheckIn);
        ptmt.setInt(1,hotelID);
        ptmt.setInt(2,roomNum);
        ResultSet resultSet =  ptmt.executeQuery();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        resultSet.next();
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
                 System.out.println("updated check-in:");
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

      try {

          int hotelID;
          int roomNum;

          Connection conn = DBConnection.getConnection();
          String checkoutdata = " SELECT id, customer_id, DATEDIFF(end_date,start_date) days from Check_in where hotel_id= ? and room_number= ? ORDER BY ID DESC LIMIT 1";

          String roomprice = "SELECT rate from Room where hotel_id= ? and room_no= ?  ";
          String services = "SELECT service_name, fee from Service_Record where checkin_id=?";
          String servicesfee = "SELECT SUM(fee) AS servicestotal from Service_Record where checkin_id=?";
          String paymethod = "SELECT paymentMethod from Billing_info where customerId=? ";
          PreparedStatement ptmt = conn.prepareStatement(checkoutdata);
          System.out.println("Please enter hotel id and room number (eg: 001,203) ");
          Scanner sc = new Scanner(System.in);
          String hrnum = sc.nextLine();
          sc = new Scanner(hrnum).useDelimiter("[^0-9]+");
          hotelID = sc.nextInt();
          ptmt.setInt(1, hotelID);
          roomNum = sc.nextInt();
          ptmt.setInt(2, roomNum);
          ResultSet rs = ptmt.executeQuery();
          rs.next();
          String checkInid = rs.getString(1);
          String customID = rs.getString(2);
          int days = rs.getInt(3);
          //get nightly rate
          ptmt = conn.prepareStatement(roomprice);
          ptmt.setInt(1, hotelID);
          ptmt.setInt(2, roomNum);
          rs = ptmt.executeQuery();
          rs.next();
          int nightlyrate = rs.getInt(1);
          int roomfees = nightlyrate * days;
          //get total service fees
          ptmt = conn.prepareStatement(servicesfee);
          ptmt.setString(1, checkInid);
          rs = ptmt.executeQuery();
          rs.next();
          int servicetotalfees = rs.getInt(1);
          int totalfee = roomfees + servicetotalfees;
          //see whether hotel card
          ptmt = conn.prepareStatement(paymethod);
          ptmt.setString(1, customID);
          rs = ptmt.executeQuery();
          rs.next();
          String payway = rs.getString(1);
          if (new String(payway).equals("hotel credit")) {

              totalfee = (int)(0.95 * totalfee);


          }
          // get all service records
          ptmt = conn.prepareStatement(services);
          ptmt.setString(1, checkInid);
          rs = ptmt.executeQuery();

          

          
          System.out.println("receipt");
          System.out.println("-------------------------");
           System.out.println("items"+"    "+"price");
          System.out.println("room" + " "  + roomfees);


          
          ResultSetMetaData rsmd = rs.getMetaData();
          int columnsNumber = rsmd.getColumnCount();
          while(rs.next())
          {
          for (int i = 1; i <= columnsNumber; i++) {
              
              String columnValue = rs.getString(i);
              System.out.print(columnValue+" ");
          }
          System.out.println("");
         }
           System.out.println("-------------------------");
          System.out.println("Total Fees   " + totalfee);

      }catch (SQLException e) {
          e.printStackTrace();
      }



  }

   
   

 public static void revenue()
 {

   try
   {
         int rev=0;
        
        String roomprice = "SELECT rate from Room where hotel_id= ? and room_no= ?  ";
         String checkin= " SELECT id, customer_id, room_number, DATEDIFF(end_date,start_date) days from Check_in where hotel_id= ? and end_date between ? and ?  ";
         String servicesfee = "SELECT SUM(fee) AS servicestotal from Service_Record where checkin_id=?";
          String paymethod = "SELECT paymentMethod from Billing where customerId=? ";
         int hotelID;
         String start;
         String end;
        System.out.println("Please enter hotel id and date range(eg: 001,2018-03-21,2018-03-23) ");
        
       Scanner sc = new Scanner(System.in);
          String hrnum = sc.nextLine();
          sc = new Scanner(hrnum).useDelimiter(", *");
          hotelID=sc.nextInt();
          start=sc.next();
          end=sc.next();
           Connection conn = DBConnection.getConnection();
      PreparedStatement ptmt = conn.prepareStatement(checkin);
       ptmt.setInt(1, hotelID);
       ptmt.setString(2,start);
       ptmt.setString(3,end);
       ResultSet resultset=ptmt.executeQuery();





           while(resultset.next())
           {
          
              
           
               String checkInid=resultset.getString(1);
              int days=resultset.getInt(4);
              int roomNum=resultset.getInt(3);
              String customID=resultset.getString(2);

             ptmt = conn.prepareStatement(roomprice);
           ptmt.setInt(1, hotelID);
           ptmt.setInt(2, roomNum);
            ResultSet rs = ptmt.executeQuery();
           rs.next();
           int nightlyrate = rs.getInt(1);
           int roomfees = nightlyrate * days;


           ptmt = conn.prepareStatement(servicesfee);
           ptmt.setString(1, checkInid);
           rs = ptmt.executeQuery();
           rs.next();
           int servicetotalfees = rs.getInt(1);
           int totalfee = roomfees + servicetotalfees;
           


     ptmt = conn.prepareStatement(paymethod);
           ptmt.setString(1, customID);
           rs = ptmt.executeQuery();
           rs.next();
           String payway = rs.getString(1);
           if (new String(payway).equals("hotel credit")) {

               totalfee = (int)(0.95 * totalfee);


           }


        rev+=totalfee;


          
          
         }

         System.out.println("revenue: "+rev);
       






   }
   catch (SQLException e) {
            e.printStackTrace();
        }









 }




    public static void staffservinglist()
    {



     try
     {
        String sql="SELECT id from Check_in where hotel_id= ? and room_number= ? ORDER BY ID DESC LIMIT 1";
        String stafflist = " SELECT * from Staff where id IN(SELECT DISTINCT staff_id from Service_Record where checkin_id=?)";
       int hotelID;
       int roomNum;
        
      Connection conn = DBConnection.getConnection();
      PreparedStatement ptmt = conn.prepareStatement(sql);
      System.out.println("Please enter hotel id and room number (eg: 001,203) ");
      Scanner sc = new Scanner(System.in);
          String hrnum = sc.nextLine();
          sc = new Scanner(hrnum).useDelimiter("[^0-9]+");
          hotelID = sc.nextInt();
          ptmt.setInt(1, hotelID);
          roomNum = sc.nextInt();
          ptmt.setInt(2, roomNum);
          ResultSet rs = ptmt.executeQuery();
          rs.next();
          String checkInid = rs.getString(1);
         ptmt=conn.prepareStatement(stafflist);
          ptmt.setString(1, checkInid);
          rs = ptmt.executeQuery();
          System.out.println("staff memebers who serve this customer" );
          System.out.println("---------------------" );
         ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " " +columnValue  );
                }
                System.out.println("");
            }





     }
     catch (SQLException e) {
          e.printStackTrace();
      }

     









    }


























  
    public static void ReleaseRoom()
  {
     
        
try{
    Connection conn = DBConnection.getConnection();
    String relroom="update Room set avai=1 where hotel_id= ? and room_no= ?";
    PreparedStatement ptmt = conn.prepareStatement(relroom);
    System.out.println("Please enter hotel id and room number to assign room (eg: 001,203) ");
    Scanner sc = new Scanner(System.in);
    String hrnum = sc.nextLine();
    sc=new Scanner(hrnum).useDelimiter("[^0-9]+");
     ptmt.setInt(1, sc.nextInt());
      ptmt.setInt(2, sc.nextInt());

      if(ptmt.executeUpdate()>0)
      {

        System.out.println("room has been released");
   
      }





 }
        catch (SQLException e) {
            e.printStackTrace();
        }


       
 




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
