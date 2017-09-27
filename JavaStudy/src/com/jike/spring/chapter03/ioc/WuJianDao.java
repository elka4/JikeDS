package com.jike.spring.chapter03.ioc;

//构造函数注入：通过调用类的构造函数，讲接口实现类通过构造函数变量传入

//但是其实liujianming并不是出现在电影的每个场景，这时候可以使用属性注入


//接口注入
public class WuJianDao implements ActorArrangable {
	private LiuJianming ljm;

	@Override
	//1：注入刘建明的具体扮演者
	public void injectLjm(LiuJianming ljm) {
		this.ljm = ljm;		
	}

	//1.属性注入方法
	public void setLjm(LiuJianming ljm){
	    this.ljm = ljm;
    }
	
	public void declare() {
		ljm.declare("我只是想做一个好人！");
	}
}