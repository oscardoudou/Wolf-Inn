import java.util.Scanner;

public class Menu {
    public static void  main(String[] args){

        DBConnection.initialize();
        Seed.seed();
        Scanner sc = new Scanner(System.in);

        RoomOperation.initialize();
        RoomOperation.initialize();

        while(true) {
            printMenu();
            System.out.println("\n====== Main Menu ======");
            System.out.print("Choose an option: ");
            switch(sc.nextLine()) {
                case "1":
                    System.out.println("\nsuccess enter infoprocessing");
                    HotelOperation.initialize();
                    HotelOperation.showHotels();
                    break;
                case "2":
                    System.out.println("\nenter infoprocessing but delete hotel");
                    HotelOperation.deleteHotel();
                    break;
                case "3":
                    System.out.println("\nnot enter infoprocessing");

                    RoomOperation.isRoomTypeAvailable();
                    RoomOperation.showRooms();
                    break;
                case "4":
                    System.out.println("\nenter hotel update");
                    HotelOperation.updateHotel();
                    break;
                case "5":
                    System.out.println("\nenter room update");
                    RoomOperation.showRooms();
                    RoomOperation.updateRoom();
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
                    // ServiceRecord.enterServiceRec();
                    break;
                default:
                    System.out.println("\n!!! illegel input !!!");
                    break;
            }
        }
    }

    public static void printMenu(){
        System.out.println("----------------------------------");
        System.out.println("Select operation your want to execute: ");
        System.out.println("1. Information Processing");
        System.out.println("2. Maintaining Service Records");
        System.out.println("3. ???");
        System.out.println("4. Reports");
        System.out.println("6. Exit");
        System.out.println("7. Staff Operations");
        System.out.println("8. Customer Operations");
        System.out.println("9. Billing Operations");
        System.out.println("10. Maintain Service Records");
    }
}