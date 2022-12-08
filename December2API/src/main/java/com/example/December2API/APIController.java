package com.example.December2API;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController // A RestController is the same as @Controller, but it puts @ResponseBody on all functions automatically, so you don't have to write it to make this an API.
public class APIController {
	@Autowired // Autowired automatically puts any available beans into the variable right below. It needs to be marked @Component for this to work.
	PersonList personList;
	
	@GetMapping("/person") // GetMapping calls the below function when someone sends a request with the url <domain>/<what we put in the parenthesis>. In this case, http://localhost:8080/person
	public Person getPersonByName(HttpServletRequest req) {
		System.out.println("Get Person was called."); // We can get parameters behind ?nameOfParameter=valueOfParameter. localhost:8080/person?name=Joe is a call to this function but with a parameter that the name is Joe.
		String name = req.getParameter("name"); // I say this function takes an HttpServletRequest, that makes Spring Boot automatically put the request in the req variable after when this request is called. I can get the parameters after that.
		Person person = personList.getPersonByName(name);
		if (person != null) {
			return person; // Then return said individual.
		}
		return null;
	}
	
	@GetMapping("/personById")
	public Person getPerson(HttpServletRequest req) { // Returning a person or an array (not a list) in a @RestController makes it automatically Json.
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Person person = personList.getPersonById(id);
			if (person != null) {
				return person;
			}
			return null;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@PostMapping("/person")
	public void addPerson(HttpServletRequest req, HttpServletResponse res) { // If we make the function take a HttpServletResponse, which we can write our response directly into via PrintWriter. It is then automatically sent back.
		System.out.println("Post person was called.");
		try {
			String name = req.getParameter("name");
			int age = Integer.parseInt(req.getParameter("age"));
			personList.add(new Person(name, age));
			res.setStatus(201); // Post doesn't want to give back anything but a status.
			// 201 means "create successful"
		}
		catch(Exception e) {
			res.setStatus(405);
		}
	}
	
	/*@GetMapping("/personJson")
	@JsonView(Person.makeJson.class)
	public Person getPersonJson(HttpServletRequest req) {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			Person person = personList.getPersonById(id);
			if (person != null) {
				return person;
			}
			return null;
		}
		catch(Exception e) {
			return null;
		}
	}*/
}
