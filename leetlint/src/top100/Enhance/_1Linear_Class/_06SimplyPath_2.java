package top100.Enhance._1Linear_Class;

public class _06SimplyPath_2 {
public String simplifyPath2(String path) {
       if (path == null || path.length() == 0) {
           return new String();
       }
       path = path.trim();
       StringBuilder sb = new StringBuilder();
       String[] strs = path.split( "/");
       int count = 0;
       for (int i = strs.length - 1; i  >= 0; i--) {
           if (strs[i].equals( ".."))  {
               count++;
           } else if (!strs[i].equals( ".") && !strs[i].equals( "")) {
               if (count >  0) {
                   count--;
               } else {
                   sb.insert(0,  "/" + strs[i]);
               }
           }
       }         
       return sb.length() == 0 ? new String( "/") : sb.toString();
   }
}
