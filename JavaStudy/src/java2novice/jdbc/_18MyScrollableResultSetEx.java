package java2novice.jdbc;

import java.sql.*;
 
public class _18MyScrollableResultSetEx {
 
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
                            ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select accno, bal from bank");
            System.out.println("ResultSet Curson is at before first: "+rs.isBeforeFirst());
            while(rs.next()){
                System.out.println(rs.getInt(1)+"   "+rs.getDouble(2));
            }
            //now result set cursor reached the last position
            System.out.println("Is After Last: "+rs.isAfterLast());
            while(rs.previous()){
                System.out.println(rs.getInt(1)+"   "+rs.getDouble(2));
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
/*Write an example for scrollable result set with read only mode.

Description:
The result can be scrolled; its cursor can move both forward and backward relative to the current position, and it can move to an absolute position. The result set reflects changes made to the underlying data source while the result set remains open.

- See more at: http://www.java2novice.com/jdbc/scroll-read-only/#sthash.WKv1pdop.dpuf*/