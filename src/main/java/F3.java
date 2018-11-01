import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class F3 {
    public ArrayList<String> OrderedPlates(ArrayList<String> arrayList){
        System.out.println("Do you want it in alphanumerical order? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        while (!choice.matches("[y,n]")) {
            System.out.println("Wrong input. Please enter (y/n) ");
            choice = scanner.nextLine();
        }
        switch (choice) {
            case "y":
                Collections.sort(arrayList);
                System.out.println("Sorted!!!");
                break;
            default:
                break;
        }

        return arrayList;
    }
}

