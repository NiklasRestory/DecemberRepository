package com.example.December2API;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component // This annotation marks the below class as one to create a bean of to be inserted whenever someone makes a variable @Autowired. 
public class PersonList {
	ArrayList<Person> personList = new ArrayList<>();
	
	public PersonList() {
		personList.add(new Person("Joe", 23));
		personList.add(new Person("Jim", 34));
		personList.add(new Person("Jenny", 52));
		personList.add(new Person("James", 15));
	}
	
	public Person getPersonById(int id) {
		for(Person person : personList) {
			if (person.getPersonId() == id) {
				return person;
			}
		}
		return null;
	}

	public void add(Person person) {
		personList.add(person);
	}

	public Person getPersonByName(String name) {
		for(Person person : personList) {
			if (person.getName().equals(name)) {
				return person;
			}
		}
		return null;
	}
}
