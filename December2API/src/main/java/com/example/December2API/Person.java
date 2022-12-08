package com.example.December2API;

public class Person {
	static int nextId = 0;
	
	private int personId;
	private String name;
	private int age;
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
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public Person(String name, int age) {
		personId = nextId;
		nextId++;
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
	
	//public interface makeJson {};
	/*@JsonView(makeJson.class)
	public String name() {
		return name;
	}
	@JsonView(makeJson.class)
	public int age() {
		return age;
	}*/
}
