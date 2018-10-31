import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class F2 {

    private int days;
    private Connection conn;
    private ArrayList<String> Plates;

    F2(int days, Connection conn) {
        this.days = days;
        this.conn = conn;
        Plates = new ArrayList<String>();
    }

    public void F2SQL(int days) throws SQLException, IOException {

        Date nextDay = convertToDateViaSqlDate(LocalDate.now().plusDays(days));
        java.sql.Date date = getCurrentDatetime();


        PreparedStatement statement = conn.prepareStatement("Select plate, exp_date from vehicle");

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String rsplate = rs.getString("plate");
            Date rsex_date = rs.getDate("exp_date");


            if ((rsex_date.compareTo(date) == 1) && (rsex_date.compareTo(nextDay) == -1)) {
                getPlates().add(rsplate);
            }

        }
        statement.close();

        if (getPlates().size() < 1) {
            System.out.println("Results 0");
            return;
        }
        System.out.println("Results " + getPlates().size());

        //f3
        F3 f3 = new F3();
        ArrayList test = f3.orderedPlates(Plates);

        System.out.println("Choose export format:");
        System.out.println("Press 1 for console");
        System.out.println("Press 2 for exported file");

        int num;
        try {
            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Non acceptable input!");
            return;
        }
        switch (num) {
            case 1:
                System.out.println("<----- Forecoming_expiries ----->");
                test.forEach((value) -> System.out.println(value));
                break;
            case 2:
                writeFile(test);
                break;
            default:
                System.err.println("Non acceptable input!");
                System.exit(-1);

        }
    }

    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private java.sql.Date getCurrentDatetime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    private void writeFile(ArrayList<String> Plates) throws IOException {
        FileWriter fileWriter = new FileWriter("forecoming_expiries.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("<----- Forecoming_expiries ----->\n");
        Plates.forEach((value) -> printWriter.printf(value + ","));

        printWriter.close();
    }

    public ArrayList<String> getPlates() {
        return Plates;
    }
}





