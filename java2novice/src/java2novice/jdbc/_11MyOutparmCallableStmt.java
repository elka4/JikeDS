package java2novice.jdbc;

import java.sql.*;
 
public class _11MyOutparmCallableStmt {
 
    public static void main(String a[]){
         
        Connection con = null;
        CallableStatement callSt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                    getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                        ,"user","password");
            callSt = con.prepareCall("{call myprocedure(?,?)}");
            callSt.setInt(1,200);
            //below method used to register data type of the out parameter
            callSt.registerOutParameter(2, Types.DOUBLE);
            callSt.execute();
            Double output = callSt.getDouble(2);
            System.out.println("The output returned from stored procedure: "+output);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(callSt != null) callSt.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
/*Write a program for CallableStatement statement with stored procedure returns OUT parameters.

Description:
A CallableStatement object provides a way to call stored procedures using JDBC. Connection.prepareCall() method provides you CallableStatement object. Below example shows how to call stored procedure with out parameters. You can regester output parameters data types by using registerOutParameter() method.

- See more at: http://www.java2novice.com/jdbc/out-callable-statement/#sthash.qvZ4GHSv.dpuf*/