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

    F2(int days, Connection conn) {
        this.days = days;
        this.conn = conn;
    }

    public void F2SQL(int days) throws SQLException, IOException {

        Date nextDay = convertToDateViaSqlDate(LocalDate.now().plusDays(days));
        java.sql.Date date = getCurrentDatetime();

        PreparedStatement statement = conn.prepareStatement("Select * from vehicle where ex_date between ? and ?");
        statement.setDate(1, date);
        statement.setDate(2, (java.sql.Date) nextDay);

        ResultSet rs = statement.executeQuery();
        HashMap PlateDate = new HashMap();
        while (rs.next()) {
            String rsplate = rs.getString("plate");
            Date rsex_date = rs.getDate("ex_date");
            PlateDate.put(rsplate, rsex_date);
        }
        System.out.println(nextDay);
        System.out.println(PlateDate);
        statement.close();

        if (PlateDate.size() < 1) {
            System.out.println("Results 0");
            return;
        }
        System.out.println("Results " + PlateDate.size());
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
                System.out.println("<----- Forecoming_exriries ----->");
                PlateDate.forEach((key, value) -> System.out.println(key + " : " + value));
                break;
            case 2:
                writeFile(PlateDate);

        }
    }

    private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private java.sql.Date getCurrentDatetime() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    private void writeFile(HashMap PlateDate) throws IOException {
        FileWriter fileWriter = new FileWriter("forecoming_exriries.out");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("<----- Forecoming_exriries ----->\n");
        PlateDate.forEach((key, value) -> printWriter.printf(key + " : " + value +"\n"));

        printWriter.close();
    }
}





