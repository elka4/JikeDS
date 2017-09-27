package com.jike.spring.chapter09.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class StuInterceptor {

	/**
	 * ��ӡ����AOP
	 */
	@Pointcut("execution(* com.jike.spring.chapter09.aspect.Student.print(..))")
	// @Pointcut("execution(* com.jike.spring.chapter09.aop.aspect.Student.*(..))")
	public void printMethod() {
	}

	@Before("printMethod()")
	public void printBeforeAdvice() {
		System.out.println("printBeforeAdvice()!");
	}

	@AfterReturning(pointcut = "printMethod()", returning = "flag")
	public void printAfterAdvice(String flag) {
		System.out.println("printAfterAdvice()! " + flag);
	}

	@After("printMethod()")
	public void finallyAdvice() {
		System.out.println("finallyAdvice()!");
	}

	@Around("printMethod() && args(name)")
	public Object printAroundAdvice(ProceedingJoinPoint pjp, String name)
			throws Throwable {
		Object result = null;
		if (name.equals("whc"))
			pjp.proceed();
		else
			System.out.println("print()�����Լ�������...");
		return result;
	}
}
