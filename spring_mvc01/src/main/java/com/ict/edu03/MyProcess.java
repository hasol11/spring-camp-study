package com.ict.edu03;

public class MyProcess {
	private String name="홍길동";
	private int age=17;
	
	public MyProcess() {
		System.out.println("MyProcess 기본 생성자");
	}
	
	public MyProcess(String name, int age) {
		System.out.println("MyProcess 기본 생성자 아님");
		this.name=name;
		this.age=age;
	}

	public void prn() {
		System.out.println("이름: "+name);
		System.out.println("나이: "+age);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
