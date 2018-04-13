import java.util.Scanner;

public class Menu {
    public static void  main(String[] args){
        DBConnection.initialize();
        Seed.seed();
        Scanner sc = new Scanner(System.in);

        RoomOperation.initialize();
        RoomOperation.initialize();
        while(true){
            printMenu();
            System.out.println("\n====== main menu ======");
            System.out.println("Choose an option: ");
            switch(sc.nextLine()){
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
                default:
                    System.out.println("\n!!! illegel input !!!");
                    break;
            }
        }
    }
    public static void printMenu(){
        System.out.println("----------------------------------");
        System.out.println("Select operation your want to execute: ");
        System.out.println("1.Information Processing");
        System.out.println("2.Maintaining Service Records");
        System.out.println("3.Billing");
        System.out.println("4.Reports");
        System.out.println("5.Exit");
    }
}