import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private int num;

    public void start() {

        System.out.println("<--- Welcome to the vehicle’s insurance --->");
        System.out.println("");
        System.out.println("Select functionality to perform:");
        System.out.println("Press 1 for vehicle insurance status");
        System.out.println("Press 2 for forecoming expiries");
        System.out.println("Press 3 for expiries by plate");
        System.out.println("Press 4 for fine calculation");
        System.out.println("Press 0 for exit");


        try {
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Non acceptable input!");
            System.exit(0);
        }

        switch (num) {
            case 0:
                System.out.println("Pressed 0");
                System.out.println("Exit");
                System.exit(0);
            case 1:
                System.out.println("Pressed 1");
                System.out.println("Vehicle insurance status");
                break;
            case 2:
                System.out.println("Pressed 2");
                System.out.println("Forecoming expiries");
                break;
            case 3:
                System.out.println("Pressed 3");
                System.out.println("Expiries by plate");
                break;
            case 4:
                System.out.println("Pressed 4");
                System.out.println("Fine calculation");
                break;
            default:
                System.out.println("Non acceptable input!");
                System.exit(0);
        }
    }

    public int getNum() {
        return num;
    }
}
