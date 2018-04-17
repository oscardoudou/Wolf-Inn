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
                    System.out.println("\nEnter Hotel Info Processing");
                    HotelOperation.openMenu();
                    break;
                case "2":
                    System.out.println("\nEntering Service Records Maintaining ");
                    ServiceRecord.openMenu();
                    break;
                case "3":
                    System.out.println("\nEnter Room Info Processing");
                    RoomOperation.openRoomMenu();
                    break;
                case "4":
                    System.out.println("\nEntering report");
                    OccupancyReport.openMenu();
                    break;
                case "5":
                    StaffOperations.openStaffMenu();
                    break;
                case "6":
                    CustomerOperations.openCustomerMenu();
                    break;
                case "7":
                    BillingOperations.openBillingMenu();
                    break;
                case "8":
                    CheckInOut.OpenCheckInoutmenu();
                    break;
                case "9":
                    System.out.println("\n\nBye!");
                    DBConnection.close();
                    return;
                default:
                    System.out.println("\n!!! illegal input !!!");
                    break;
            }
        }
    }

    public static void printMenu(){
        System.out.println("----------------------------------");
        System.out.println("Select operation your want to execute: ");
        System.out.println("1. Hotel Information Processing");
        System.out.println("2. Maintaining Service Records");
        System.out.println("3. Room Information processing");
        System.out.println("4. Reports");
        System.out.println("5. Staff Operations");
        System.out.println("6. Customer Operations");
        System.out.println("7. Billing Operations");
        System.out.println("8. CheckInOut Operations");
        System.out.println("9. Exit");

    }
}
