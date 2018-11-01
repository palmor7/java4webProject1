import java.sql.*;

public class F4 {
    private Connection conn;

    F4(Connection conn) {
        this.conn = conn;
    }

    public void F4sql(String last_name, int fine) throws SQLException {
        PreparedStatement p = conn.prepareStatement("Select exp_date from Vehicle v, User u where  u.last_name=? and u.id=v.user_id");
        p.setString(1, last_name);

        ResultSet rs = p.executeQuery();
        java.util.Date date = new java.util.Date();
        int count = 0;
        boolean b = false;

        while (rs.next()) {
            b = true;
            Date rsex_date = rs.getDate("exp_date");
            if ((rsex_date.compareTo(date) == -1)) {
                count++;
            }
        }
        if (b) {
            if (count == 0) {
                System.out.println(last_name + " has insured car!");
            }
            System.out.println(last_name + "'s fine is: " + count * fine + "!");
        } else {
            System.out.println(last_name + " is not in the database!");
        }


    }
}
