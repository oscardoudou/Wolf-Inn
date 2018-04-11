import java.util.Scanner;

public class Menu {
    public static void  main(String[] args){
        printMenu();
        DBConnection.initialize();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("No: ");
            switch(sc.nextLine()){
                case "1":
                    System.out.println("success enter infoprocessing");
                    HotelOperation.initialize();
                    break;
                case "2":
                    System.out.println("enter infoprocessing but delete hotel");
                    HotelOperation.deleteHotel();
                    break;
                case "3":
                    System.out.println("enter hotel update");
                    HotelOperation.updateHotel();
                    break;
                case "5":
                    System.out.println("Bye!");
                    DBConnection.close();
                    return;
                default:
                    System.out.println("illegel input");
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