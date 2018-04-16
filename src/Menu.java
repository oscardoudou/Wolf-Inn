import java.util.Scanner;

public class Menu {
    public static void  main(String[] args){
        DBConnection.initialize();
        Seed.seed();
        Scanner sc = new Scanner(System.in);

        while(true) {
            printMenu();
            System.out.println("\n====== Main Menu ======");
            System.out.print("Choose an option: ");
            switch(sc.nextLine()) {
                case "1":
                    System.out.println("\nsuccess enter infoprocessing");
                    HotelOperation.showHotels();
                    break;
                case "2":
                    System.out.println("\nenter infoprocessing but delete hotel");
                    HotelOperation.deleteHotel();
                    break;
                case "3":
                    RoomOperation.openRoomMenu();
                    break;
                case "4":
                    System.out.println("\nenter occupancy update");
                    OccupancyReport.getHotelOccupancy();
                    OccupancyReport.getRoomTypeOccupancy();
                    //HotelOperation.updateHotel();
                    break;
                case "5":
                    System.out.println("enter service record");
                    ServiceRecord.updateServiceRec();
                    break;
                case "6":
                    System.out.println("\n\nBye!");
                    DBConnection.close();
                    return;
                case "7":
                    StaffOperations.openStaffMenu();
                    break;
                case "8":
                    CustomerOperations.openCustomerMenu();
                    break;
                case "9":
                    BillingOperations.openBillingMenu();
                    break;
                case "10":
                    ServiceRecord.enterServiceRec();
                    break;
                case "11":
                    ServiceRecord.retrieveServiceRec();
                    break;
                default:
                    System.out.println("\n!!! illegal input !!!");
                    break;
            }
        }
    }

    public static void printMenu(){
        System.out.println("----------------------------------");
        System.out.println("Select operation your want to execute: ");
        System.out.println("1. Information Processing");
        System.out.println("2. Maintaining Service Records");
        System.out.println("3. This is for hotel test, would be merged into infomation processing later");
        System.out.println("4. Reports");
        System.out.println("5. test for updateServiceRec");
        System.out.println("6. Exit");
        System.out.println("7. Staff Operations");
        System.out.println("8. Customer Operations");
        System.out.println("9. Billing Operations");
        System.out.println("10.test for enterServiceRec");
        System.out.println("11.test for retrieveServiceRec");

    }
}