import java.sql.*;

public class F1 {
    private String plate;
    private Connection conn;
    private Date rsex_date = null;

    F1(String plate, Connection conn) {
        this.plate = plate;
        this.conn = conn;
    }

    public void F1sql() throws SQLException {
        String query = "Select exp_date from vehicle where plate=?";

        try (PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, plate);
            ResultSet rs = st.executeQuery();


            if (rs.next()) {
                rsex_date = rs.getDate("exp_date");
                java.util.Date date = new java.util.Date();

                if (date.compareTo(rsex_date) == 1) {
                    System.out.println("Expired at: " + rsex_date);
                } else {
                    System.out.println("The insurance will expire at: " + rsex_date);
                }
            } else {
                System.err.println("No result !!!");
                System.exit(0);
            }
        }

        }

    }



