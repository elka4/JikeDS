package java2novice.jdbc;

import java.sql.*;
 
public class _05MyExecuteMethod {
 
    public static void main(String a[]){
         
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                    getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                        ,"user","password");
            Statement stmt = con.createStatement();
            //The query can be update query or can be select query
            String query = "select * from emp";
            boolean status = stmt.execute(query);
            if(status){
                //query is a select query.
                ResultSet rs = stmt.getResultSet();
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                rs.close();
            } else {
                //query can be update or any query apart from select query
                int count = stmt.getUpdateCount();
                System.out.println("Total records updated: "+count);
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(con != null) con.close();
            } catch (Exception ex){}
        }
    }
}

/*How to execute any type of query in JDBC?

Description:
The Statement.execute() method allows us to execute 
any kind of query like select,
 update. It returns boolean. If the return value is 
 true, then it executed select query, get the ResultSet
  object and read the resulted records. If it returns 
  false, then it can be update query, call 
  getUpdateCount() method to get total records updated.
   Check the below sample code:

- See more at: http://www.java2novice.com/jdbc/execute-method/#sthash.D7sASqt3.dpuf*/