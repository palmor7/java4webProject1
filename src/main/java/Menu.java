import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Connection connection;
    private int num;

    public void start() throws Exception {

        System.out.println("<--- Welcome to the vehicle’s insurance --->");
        System.out.println("");
        System.out.println("Select functionality to perform:");
        System.out.println("Press 1 for vehicle insurance status");
        System.out.println("Press 2 for forecoming expiries");
        System.out.println("Press 3 for fine calculation");
        System.out.println("Press 0 for exit");


        try {
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Non acceptable input!");
            System.exit(-1);
        }
        DBConnection dbConnection = new DBConnection();

        System.out.println("Give user name!");
        Scanner scanner = new Scanner(System.in);
        String givenPlate = scanner.nextLine();
        dbConnection.setDB_USER(givenPlate);

        System.out.println("Give password!");
        givenPlate = scanner.nextLine();
        dbConnection.setDB_PASSWORD(givenPlate);

        connection = dbConnection.getDBConnection();

        switch (num) {
            case 0:
                System.out.println("Pressed 0");
                System.out.println("Exit");
                System.exit(0);
            case 1:
                System.out.println("Pressed 1");
                System.out.println("Vehicle insurance status");
                System.out.println("Give vehicle's plate!");
                givenPlate = scanner.nextLine();

                while (!givenPlate.matches("[A-Z]{3}-[0-9]{4}")) {
                    System.out.println("Wrong format. Please use the 'ABC-1234' format ");
                    givenPlate = scanner.nextLine();

                }

                F1 f1 = new F1(givenPlate,connection );
                f1.F1SQL();
                break;
            case 2:
                System.out.println("Pressed 2");
                System.out.println("Forecoming expiries");

                System.out.println("Give days to check the upcoming expires!");

                try {
                    int days = scanner.nextInt();
                    F2 f2 = new F2(days,connection );
                    f2.F2SQL(days);
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Non acceptable input!");
                    System.exit(-1);
                }

            case 3:
                System.out.println("Pressed 3");
                System.out.println("Fine calculation");
                break;
            default:
                System.err.println("Non acceptable input!");
                System.exit(-1);
        }
    }

}