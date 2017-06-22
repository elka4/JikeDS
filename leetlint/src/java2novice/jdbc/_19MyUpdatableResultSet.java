package java2novice.jdbc;

import java.sql.*;
 
public class _19MyUpdatableResultSet {
 
    public static void main(String a[]){
         
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                        ,"user","password");
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery("select accno, bal from bank");
            while(rs.next()){
                if(rs.getInt(1) == 100){
                    rs.updateDouble(2, 2000);
                    rs.updateRow();
                    System.out.println("Record updated!!!");
                }
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
                if(st != null) st.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
