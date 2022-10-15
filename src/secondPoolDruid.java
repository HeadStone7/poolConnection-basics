import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class secondPoolDruid {

    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        InputStream inputStream = secondPoolDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        Connection connection = null;

        try {
            properties.load(inputStream);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();

            String sqlRetrieve ="UPDATE account SET money=money-? WHERE name=?";
            String sqlAdd ="UPDATE account SET money=money+? WHERE name=?";
            PreparedStatement ps = connection.prepareCall(sqlRetrieve);
            PreparedStatement prpStmt2 = connection.prepareCall(sqlAdd);

            ps.setInt(1,50);
            ps.setString(2, "张三");

            prpStmt2.setInt(1,50);
            prpStmt2.setString(2, "李四");


            ps.executeUpdate();
            prpStmt2.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            properties.load(inputStream);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
