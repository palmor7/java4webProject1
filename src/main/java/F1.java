import java.sql.*;

public class F1 {
    private String plate;
    private Connection conn;

    F1(String plate, Connection conn) {
        this.plate = plate;
        this.conn = conn;
    }

    public void F1SQL() throws SQLException {
        String query = "Select ex_date from vehicles where plate='" + this.plate + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {

            Date rsex_date = rs.getDate("ex_date");

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);

            if (date.compareTo(rsex_date) == 1) {
                System.out.println("Expired: " + rsex_date);
            } else {
                System.out.println("The insurance will exrpire: " + rsex_date);
            }
        }
        st.close();
    }

}


