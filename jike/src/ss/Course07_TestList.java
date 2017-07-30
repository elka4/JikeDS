package ss;

import org.junit.Test;

import java.util.Comparator;

public class Course07_TestList {
	
	/**
	 * 链表节点
	 */
	public class ListNode<T> {
		/**
		 * 值
		 */
		public T value;
		/**
		 * 指向下一个节点的指针(引用)
		 */
		public ListNode<T> next;
		public ListNode(T value, ListNode<T> next) {
			super();
			this.value = value;
			this.next = next;
		}
		public ListNode() {
			super();
		}
		/**
		 * 指向前趋节点的指针，用于双链表
		 */
		public ListNode<T> pre;
	}



    @SuppressWarnings("unused")
    @Test
    public void testNode01(){
        ListNode<Integer> p4=new ListNode<Integer>(4, null);
        ListNode<Integer> p3=new ListNode<Integer>(3, p4);
        ListNode<Integer> p2=new ListNode<Integer>(2, p3);
        ListNode<Integer> p1=new ListNode<Integer>(1, p2);
    }
    @SuppressWarnings("unused")
    @Test
    public void testNode02(){
        ListNode<Integer> p1=new ListNode<Integer>(1, new ListNode<Integer>(2,
                new ListNode<Integer>(3, new ListNode<Integer>(4, null))));
    }
    /**
     * 测试：单链表的功能
     */
    @Test
    public void testCourse07_MiniList(){
        Course07_MiniList<Integer> list=new Course07_MiniList<Integer>();
        Integer[] array={0,1,2,3,4};
        list.arrayToList(array);
        list.printList();
        list.insert(2, 10);
        list.printList();
        list.remove(4);
        list.printList();
        list.set(3, 13);
        list.printList();
        System.out.println(list.get(4));
    }
    /**
     * 测试：逆序打印链表
     */
    @Test
    public void testPrintReverse(){
        Course07_MiniList<Integer> list=new Course07_MiniList<Integer>();
        Integer[] array={0,1,2,3,4};
        list.arrayToList(array);
        list.printList();
        list.printInverse();
        list.printInverseRecursive();
    }
    /**
     * 测试：获取链表的最大元素，整形
     */
    @Test
    public void testMaxInteger(){
        Course07_MiniList<Integer> list=new Course07_MiniList<Integer>();
        Integer[] array={3,4,1,2};
        list.arrayToList(array);
        System.out.println(list.getMax());
    }
    /**
     * 测试：获取链表的最大元素，整形普通类型
     */
    @Test
    public void testMaxPerson(){
        Course07_MiniList<Course07_Person> list=new Course07_MiniList<Course07_Person>();
        list.comp=new Comparator<Course07_Person>() {
            @Override
            public int compare(Course07_Person o1, Course07_Person o2) {
                return o2.getId()-o1.getId();
            }
        };
        Course07_Person[] array={new Course07_Person(3, "AAA"),
                new Course07_Person(2, "BBB"),
                new Course07_Person(1, "CCC"),
                new Course07_Person(4, "DDD")};
        list.arrayToList(array);
        System.out.println(list.getMax());
    }
}
