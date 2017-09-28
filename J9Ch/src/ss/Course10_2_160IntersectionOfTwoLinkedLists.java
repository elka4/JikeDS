package ss;

import org.junit.Test;

import java.util.HashSet;


public class Course10_2_160IntersectionOfTwoLinkedLists{
	@Test
	public void test01(){
		int[] array1={1,2,3,4,5,6,7,8};
		int[] array2={9,10};
		ListNode[] heads=ListNode.arrayToIntersection(array1, array2, 5);
		ListNode.printList(heads[0]);
		ListNode.printList(heads[1]);
	}
	/**
	 * 暴力求解法
	 */
	public ListNode bruteForce(ListNode headA,ListNode headB){
		for(ListNode p=headA;p!=null;p=p.next){
			for(ListNode q=headB;q!=null;q=q.next){
				if(p==q){
					return p;
				}
			}
		}
		return null;
	}
	/**
	 * 利用哈希表
	 */
	public ListNode hashFunction(ListNode headA,ListNode headB){
		HashSet<ListNode> hashSet=new HashSet<ListNode>();
		for(ListNode p=headB;p!=null;p=p.next){
			hashSet.add(p);
		}
		for(ListNode p=headA;p!=null;p=p.next){
			if(hashSet.contains(p)){
				return p;
			}
		}
		return null;
	}
	@Test
	public void test02(){
		int[] array1={1,2,3,4,5,6,7,8};
		int[] array2={9,10};
		ListNode[] heads=ListNode.arrayToIntersection(array1, array2, 5);
		ListNode head=hashFunction(heads[0], heads[1]);
		System.out.println(head.val);
		head=hashFunction(ListNode.arrayToList(array1), ListNode.arrayToList(array2));
		System.out.println(head);
	}
	/**
	 * 取得链表长度
	 */
	public int lengthOfList(ListNode head){
		ListNode p=head;
		int n=0;
		while(p!=null){
			n++;
			p=p.next;
		}
		return n;
	}
	/**
	 * 线性算法+常数空间 求链表交点
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int m=lengthOfList(headA);
		int n=lengthOfList(headB);
		if(m==0||n==0){
			return null;
		}else{
			int k;
			ListNode p=headA;
			ListNode q=headB;
			if(m>n){
				k=m-n;
				for(int i=1;i<=k;i++){
					p=p.next;
				}
			}else if(m<n){
				k=n-m;
				for(int i=1;i<=k;i++){
					q=q.next;
				}
			}
			while(p!=null&&q!=null){
				if(p==q){
					return q;
				}else{
					p=p.next;
					q=q.next;
				}
			}
			return null;
		}
	}
	@Test
	public void test03(){
		int[] array1={1,2,3,4,5,6,7,8};
		int[] array2={9,10};
		ListNode[] heads=ListNode.arrayToIntersection(array1, array2, 5);
		ListNode head=getIntersectionNode(heads[0], heads[1]);
		System.out.println(head.val);
		head=getIntersectionNode(ListNode.arrayToList(array1), ListNode.arrayToList(array2));
		System.out.println(head);
	
	}
}
