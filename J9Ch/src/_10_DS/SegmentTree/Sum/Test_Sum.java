package _10_DS.SegmentTree.Sum;

import org.junit.Test;

public class Test_Sum {
    NumArray st = new NumArray(new int[]{1,2,3,4,5,6});


    @Test
    public void test01(){
        System.out.println(st.sumRange(0,0));
        System.out.println(st.sumRange(0,3));
    }


}
