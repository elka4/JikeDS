package com.jike.spring.chapter09.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory implements InvocationHandler {
	private Object stu;

	public Object createStudentProxy(Object stu) {
		this.stu = stu;
		return Proxy.newProxyInstance(
				stu.getClass().getClassLoader(), 
				stu.getClass().getInterfaces(), 
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		StudentBean s = (StudentBean) stu;
		Object object = null;
		if (s.getName() != null)
			object = method.invoke(stu, args);
		else
			System.out.println("����Ϊ�գ��������Ѿ����أ�");
		return object;
	}
}
