package com.java2novice.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
public class _04MyQueryUpdate {
 
    public static void main(String[] args) {
         
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                    getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                        ,"user","password");
            stmt = con.createStatement();
            String query = "update table emp set salary=2000 where empid=200";
            //count will give you how many records got updated
            int count = stmt.executeUpdate(query);
            System.out.println("Updated queries: "+count);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}

/*How to update a record in the database using JDBC?

Description:
This example shows how to update table records using 
SQL update query. 
By using Statement.executeUpdate() you can execute 
update statements. Statement.executeUpdate() method 
returns you number of records got updated.

- See more at: http://www.java2novice.com/jdbc/update-record/#sthash.7fWmwdYc.dpuf*/