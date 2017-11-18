package _10_DS.SegmentTree.Count_Sum_Max;
import org.junit.Test;

public class test {

    @Test
    public void test01(){
        SegmentTree st = new SegmentTree(new int[]{2,1,0,3});
        /*
                      [1, 4, max=3]
                    /                \
        [1, 2, max=2]                [3, 4, max=3]
       /              \             /             \
[1, 1, max=2], [2, 2, max=1], [3, 3, max=0], [4, 4, max=3]



         */
//        SegmentTreeNode stn = st.build(1,4);
        //System.out.println(stn);


//        st.modify(stn, 1,2);
//        st.modify(stn, 2,1);
//        st.modify(stn, 3,0);
//        st.modify(stn, 4,3);
//        System.out.println(stn);
        System.out.println("-------------------------------------------------------");
        System.out.println(st.query_count(1,3));//3
        System.out.println(st.query_max(1,3));//2
        System.out.println(st.query_min(1,3));//0
        System.out.println(st.query_sum(1,3));//3
        System.out.println("-------------------------------------------------------");
        System.out.println(st.query_count(1,4));//4
        System.out.println(st.query_max(1,4));//3
        System.out.println(st.query_min(1,4));//3
        System.out.println(st.query_sum(1,4));//6
        System.out.println("-------------------------------------------------------");
        System.out.println(st.query_count(1,1));//1
        System.out.println(st.query_count(3,3));//1
        System.out.println(st.query_min(2,4));//1
        System.out.println(st.query_min(3,4));//3

        System.out.println(st.root);//[start: 1; end: 4; count: 4; max: 3; min: 3; sum: 6]


    }//java.lang.Exception: Test class should have exactly one public constructor


    /*@Test
    public void test02(){
        //SegmentTree st = new SegmentTree();
        //SegmentTreeNode stn = st.build(1,10);
        //st.modify(stn, 1,1);
        //System.out.println(st.query(stn, 1,1));

    }*///java.lang.Exception: Test class should have exactly one public constructor
}
