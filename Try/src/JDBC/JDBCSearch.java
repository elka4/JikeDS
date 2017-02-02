package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by tzh on 2/1/17.
 */
public class JDBCSearch {
    public static void main(String[] args) {
        String sql = "SELECT * FROM tbl_usr";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db", "root", "dashizi");
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                System.out.print(rs.getString("user_name"));
                System.out.print(rs.getInt("age"));
                System.out.print(rs.getDate("signup_date"));
                System.out.println();

            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                rs.close();
            }catch (Exception e){

            }
            try{
                st.close();
            }catch (Exception e){

            }
            try{
                conn.close();
            }catch (Exception e){

            }
        }

    }
}
