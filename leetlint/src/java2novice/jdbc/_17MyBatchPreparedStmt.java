package java2novice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class _17MyBatchPreparedStmt {
 
    public static void main(String a[]){
         
        Connection con = null;
        PreparedStatement pst = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                    getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                        ,"user","password");
            con.setAutoCommit(false);
            pst = con.prepareStatement("update emp set sal=? where empid=?");
            pst.setInt(1, 3000);
            pst.setInt(2, 200);
            pst.addBatch();
            pst.setInt(1, 4000);
            pst.setInt(2, 230);
            pst.addBatch();
            int count[] = pst.executeBatch();
            for(int i=1;i<=count.length;i++){
                System.out.println("Query "+i+" has effected "+count[i]+" times");
            }
            con.commit();
        } catch (ClassNotFoundException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally{
            try{
                if(pst != null) pst.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
/*Write an example for batch update using PreparedStatement.

Description:
Batch update is nothing but executing set of queries at a time. Batch updates reduces number of database calls. In batch processing, batch should not contain select query. When we are using PreparedStatement to execute batch update, we have to run the same query multiple times. Below examples shows how to do batch updates with PreparedStatement.

- See more at: http://www.java2novice.com/jdbc/prepared-statement-batch-update/#sthash.Hud7Ezt7.dpuf*/