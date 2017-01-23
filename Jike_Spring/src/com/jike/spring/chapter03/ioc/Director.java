package com.jike.spring.chapter03.ioc;

public class Director {
   public void direct(){
	   LiuJianming ljm = new LiuDeHua();
	   WuJianDao wjd = new WuJianDao();

	   wjd.injectLjm(ljm);

	   //2调用属性setter注入
	   wjd.setLjm(ljm);

	   wjd.declare();
   }
}
