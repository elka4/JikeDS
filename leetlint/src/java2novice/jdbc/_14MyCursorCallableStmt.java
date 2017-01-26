package java2novice.jdbc;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import oracle.jdbc.OracleTypes;
 
public class _14MyCursorCallableStmt {
 
    public static void main(String a[]){
         
        Connection con = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                    ,"user","password");
            callSt = con.prepareCall("{call myCursorExmp(?,?)}");
            callSt.setInt(1,200);
   //         callSt.registerOutParameter(2, OracleTypes.CURSOR);
            callSt.execute();
            rs = (ResultSet)callSt.getObject(2);
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(rs != null) rs.close();
                if(callSt != null) callSt.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
