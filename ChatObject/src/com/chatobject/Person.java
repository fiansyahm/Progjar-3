/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatobject;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Person implements Serializable{
	private String fullname;
	private int age;
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person() {
		
	}
	
	public Person(String fullname, int age) {
		this.fullname = fullname;
		this.age = age;
	}
	
}
