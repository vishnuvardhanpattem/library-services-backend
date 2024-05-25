package com.vishnu.springbootlibraryweb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "checkout")
@Data
public class Checkout {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long bookId;

	private String userEmail;

	private String checkoutDate;

	private String returnDate;

	public Checkout(String userEmail, String checkoutDate, String returnDate, Long bookId) {
		this.bookId = bookId;
		this.userEmail = userEmail;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
	}

	public Checkout() {
		super();
	}

}
