package com.jike.spring.chapter09.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class CGlibProxyFactory implements MethodInterceptor {
	private Object object;

	public Object createStudent(Object object) {
		this.object = object;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(object.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		StudentBean stu = (StudentBean) object;
		Object result = null;
		if (stu.getName() != null)
			result = methodProxy.invoke(object, args);
		else
			System.out.println("�����Ѿ�������...");
		return result;
	}
}
