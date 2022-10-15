import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class c3P0Connection {

    public static void main(String[] args) {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = dataSource.getConnection();
            String sqlRetrieve ="UPDATE account SET money=money-? WHERE name=?";
            String sqlAdd ="UPDATE account SET money=money+? WHERE name=?";
            ps = conn.prepareCall(sqlRetrieve);
            PreparedStatement prpStmt2 = conn.prepareCall(sqlAdd);

            ps.setInt(1,50);
            ps.setString(2, "张三");

            prpStmt2.setInt(1,50);
            prpStmt2.setString(2, "李四");


            ps.executeUpdate();
            prpStmt2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
