package com._12atguigu.java1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestList {
	
	//ArrayList��List����Ҫʵ����
	/*
	 * List�������Collection�������ӵķ���
	 *  void add(int index, Object ele):��ָ��������λ��index���Ԫ��ele
		boolean addAll(int index, Collection eles)
		Object get(int index):��ȡָ��������Ԫ��
		Object remove(int index):ɾ��ָ������λ�õ�Ԫ��
		Object set(int index, Object ele):����ָ������λ�õ�Ԫ��Ϊele
		int indexOf(Object obj):����obj�ڼ������״γ��ֵ�λ�á�û�еĻ�������-1
		int lastIndexOf(Object obj)������obj�ڼ��������һ�γ��ֵ�λ��.û�еĻ�������-1
		List subList(int fromIndex, int toIndex):���ش�fromIndex��toIndex����������ҿ�һ����list
		
		List���õķ�������(add(Object obj)) ɾ(remove) ��(set(int index,Object obj))
					��(get(int index)) ��(add(int index, Object ele)) ����(size())
	 */
	@Test
	public void testList2(){
		List list = new ArrayList();
		list.add(123);
		list.add(456);
		list.add(new String("AA"));
		list.add(new String("GG"));
		list.add(456);
		System.out.println(list.indexOf(456));
		System.out.println(list.lastIndexOf(456));
		System.out.println(list.indexOf(123) == list.lastIndexOf(123));
		System.out.println(list.indexOf(444));
		
		List list1 = list.subList(0, 3);
		System.out.println(list1);
	}
	
	@Test
	public void testList1(){
		List list = new ArrayList();
		list.add(123);
		list.add(456);
		list.add(new String("AA"));
		list.add(new String("GG"));
		System.out.println(list);
		list.add(0,555);
		System.out.println(list);
		Object obj = list.get(1);
		System.out.println(obj);
		list.remove(0);
		System.out.println(list.get(0));
		list.set(0, 111);
		System.out.println(list.get(0));
	}
}
