package com.java2novice.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
public class _16MyStatementBatchUpdate {
 
    public static void main(String a[]){
         
        Connection con = null;
        Statement st = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                    ,"user","password");
            con.setAutoCommit(false);
            st = con.createStatement();
            st.addBatch("update emp set sal=3000 where empid=200");
            st.addBatch("insert into emp values (100,4000)");
            st.addBatch("update emp set emp name='Ram' where empid=345");
            int count[] = st.executeBatch();
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
                if(st != null) st.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
/*Write an example for batch update using Statement.

Description:
Batch update is nothing but executing set of queries at a time. Batch updates reduces number of database calls. In batch processing, batch should not contain select query. You can add queries by calling addBatch() method and can execute the bunch of queries by calling executeBatch() method. When using batch updates with Statement object, you can use multiple types of queries which can be acceptable in executeUpdate() method.

- See more at: http://www.java2novice.com/jdbc/statement-batch-update/#sthash.4I5yfsP0.dpuf*/