package _10_DS.SegmentTree;

import org.junit.Test;

public class test {

    @Test
    public void test01(){
        SegmentTree st = new SegmentTree();
        /*
                      [1, 4, max=3]
                    /                \
        [1, 2, max=2]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]



         */
        SegmentTreeNode stn = st.build(1,6);
        System.out.println(stn);

        st.modify(stn, 1,2);
        st.modify(stn, 2,1);
        //st.modify(stn, 3,3);
        st.modify(stn, 4,3);
        System.out.println(stn);
        System.out.println(st.query2(stn, 1,6));
//        System.out.println(st.query(stn, 1,4));
//        System.out.println(st.query(stn, 2,8));
//        System.out.println(st.query(stn, 1,10));
//        System.out.println(st.query2(stn, 3,7));
//        System.out.println(st.query2(stn, 1,6));

    }//java.lang.Exception: Test class should have exactly one public constructor


    @Test
    public void test02(){
        SegmentTree st = new SegmentTree();
        //SegmentTreeNode stn = st.build(1,10);
        //st.modify(stn, 1,1);
        //System.out.println(st.query(stn, 1,1));

    }//java.lang.Exception: Test class should have exactly one public constructor
}
