package java2novice.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
public class _23MyConnWithProperties {
 
    public static void main(String a[]){
         
        System.setProperty("Jdbc.drivers", "oracle.jdbc.driver.OracleDriver");
        Properties prop = new Properties();
        prop.put("user", "user-name");
        prop.put("password", "password");
        try {
            Connection con = DriverManager
                .getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>", prop);
            System.out.println("Got Connection Object");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
