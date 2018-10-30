import java.sql.Connection;
import java.util.Scanner;

public class main {
    private static Connection connection;

    public static void main(String[] args) throws Exception {

        Menu menu = new Menu();
        menu.start();

        DBConnection dbConnection = new DBConnection();

        System.out.println("Give user name!");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        dbConnection.setDB_USER(inputString);

        System.out.println("Give password!");
        inputString = scanner.nextLine();
        dbConnection.setDB_PASSWORD(inputString);

        connection = dbConnection.getDBConnection();

        switch (menu.getNum()){
            case 1:
                System.out.println("Give vehicle's plate!");
                inputString = scanner.nextLine();

                while (!inputString.matches("[A-Z a-z]{3}-[0-9]{4}")) {
                    System.out.println("Wrong format. Please use the 'ABC-1234' format ");
                    inputString = scanner.nextLine();

                }
              /*if((inputString.length() != 8) || (inputString.charAt(3) != '-')|| !(Character.isLetter(inputString.charAt(0)))
                        || !(Character.isLetter(inputString.charAt(1))) || !(Character.isLetter(inputString.charAt(2))) || !(Character.isDigit(inputString.charAt(4)))
                       || !(Character.isDigit(inputString.charAt(5))) || !(Character.isDigit(inputString.charAt(6))) || !(Character.isDigit(inputString.charAt(7)))){
                   System.out.println("Non acceptable plate’s pattern");
                    dbConnection.closeDBConnection();
                    System.exit(0);
                }*/

                F1 f1 = new F1(inputString,connection );
                f1.F1SQL();
                break;
            case 2:
                System.out.println("Give days to check the upcoming expires!");
                int days = scanner.nextInt();
                F2 f2 = new F2(days,connection );
                f2.F2SQL(days);

                break;
            case 3:
                break;
            case 4:
                break;
        }

    }
}
