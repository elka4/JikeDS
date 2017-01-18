package jike;

/**
 * 链表的节点类
 */
public class ListNode {
public int val;
public ListNode next;
public ListNode(int val) {
	super();
	this.val = val;
}
public ListNode(int val, ListNode next) {
	super();
	this.val = val;
	this.next = next;
}
@Override
public String toString() {
	return "ListNode [val=" + val + "]";
}

public static void printList(ListNode head){
	ListNode p=head;
	while(p!=null){
		System.out.print(p.val+" ");
		p=p.next;
	}
	System.out.println();
}
public static ListNode arrayToList(int[] array){
	ListNode head=new ListNode(0);
	ListNode p=head;
	for(int value:array){
		p.next=new ListNode(value);
		p=p.next;
	}
	return head.next;
}
/**
 * 创建约瑟夫环
 */
public static ListNode arrayToCircle(int[] array){
	ListNode head=new ListNode(0);
	ListNode p=head;
	for(int value:array){
		p.next=new ListNode(value);
		p=p.next;
	}
	p.next=head.next;
	return head.next;
}
/**
 * 创建有交点的两个链表
 */
public static ListNode[] arrayToIntersection(int[] array1,int[] array2,int interIndex){
	//array1={1,2,3,4,5,6,7,8} array2={9,10} interIndex=5
	//list1=1,2,3,4,5,6,7,8 list2=9,10,5,6,7,8
	ListNode head1=new ListNode(0);
	ListNode head2=new ListNode(0);
	ListNode p1=head1;
	ListNode p2=head2;
	int m=array1.length;
	ListNode intersection=null;
	for(int i=0;i<m;i++){
		p1.next=new ListNode(array1[i]);
		p1=p1.next;
		if(i==interIndex-1){
			intersection=p1;
		}
	}
	int n=array2.length;
	for(int i=0;i<n;i++){
		p2.next=new ListNode(array2[i]);
		p2=p2.next;
	}
	p2.next=intersection;
	ListNode[] array={head1.next,head2.next};
	return array;
}
/**
 * 创建带环的链表
 */
public static ListNode createCycle(int[] array,int index){
	ListNode head=new ListNode(0);
	ListNode p=head;
	int n=array.length;
	ListNode startNode=null;
	for(int i=0;i<n;i++){
		p.next=new ListNode(array[i]);
		p=p.next;
		if(i==index-1){
			startNode=p;
		}
	}
	p.next=startNode;
	return head.next;
}
}
