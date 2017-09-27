package com.jike.spring.chapter09.cglib;

public class StudentBean {
	private String name;

	public StudentBean() {
	}

	public StudentBean(String name) {
		this.name = name;
	}

	public void print() {
		System.out.println(name + " hello world!");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
