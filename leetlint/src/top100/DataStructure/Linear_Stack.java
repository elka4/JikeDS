package top100.DataStructure;
import org.junit.Test;

import java.util.Stack;
import java.util.*;

//extends vector
public class Linear_Stack {


    @Test
    public void test01(){
        // creating stack
        Stack st = new Stack();

        // populating stack
        st.push("Java");
        st.push("Source");
        st.push("code");

        // removing top object
        System.out.println("Removed object is: "+st.pop());

        // elements after remove
        System.out.println("Elements after remove: "+st);
    }


}
