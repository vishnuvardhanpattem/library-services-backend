package com.vishnu.springbootlibraryweb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String question;
	private String userEmail;
	private String adminEmail;
	private String response;
	private boolean closed;

	public Message() {
	}

	public Message(String title, String question) {
		this.title = title;
		this.question = question;
	}
}
