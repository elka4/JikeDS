package com.java2novice.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
public class _01JdbcConnection {
 
    public static void main(String a[]){
         
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.
                getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                    ,"user","password");
            Statement stmt = con.createStatement();
            System.out.println("Created DB Connection....");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

/*How to create JDBC Connection?

Description:
Steps to create JDBC connection:

Register the database driver with java.sql.DriverManager,
 where DriverManager is a class which is given under 
 JDBC specifications. You can do this by loading driver 
 implementation class into the JVM by using 
 Class.forName().
Open a session to database, i.e. establish a connection 
to database. You can do this by calling 
DriverManager.getConnection() method, which returns
 Connection object.
- See more at: http://www.java2novice.com/jdbc/create-connection/#sthash.gIsUaxVx.dpuf*/