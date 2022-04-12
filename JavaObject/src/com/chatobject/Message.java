/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chatobject;

/**
 *
 * @author User
 */


import java.io.Serializable;
public class Message implements Serializable{
	private String sender,text;
	
	public Message() {
		
	}
	
	public Message(String sender,String text) {
		this.sender=sender;
		this.text=text;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}