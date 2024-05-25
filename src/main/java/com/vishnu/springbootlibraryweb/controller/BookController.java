package com.vishnu.springbootlibraryweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vishnu.springbootlibraryweb.entity.Book;
import com.vishnu.springbootlibraryweb.responsemodels.ShelfCurrentLoansResponse;
import com.vishnu.springbootlibraryweb.service.BookService;
import com.vishnu.springbootlibraryweb.utils.ExtractJWT;

//@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/secure/currentloans")
	public List<ShelfCurrentLoansResponse> currentLoans(@RequestHeader(value = "Authorization") String token)
			throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.currentLoans(userEmail);
	}

	@GetMapping("/secure/currentloans/count")
	public int currentLoansCount(@RequestHeader(value = "Authorization") String token) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.currentLoansCount(userEmail);
	}

	@GetMapping("/secure/ischeckedout/byuser")
	public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)
			throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.checkoutBookByUser(userEmail, bookId);
	}

	@PutMapping("/secure/checkout")
	public Book checkoutBook(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)
			throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		return bookService.chekoutBook(userEmail, bookId);
	}

	@PutMapping("/secure/return")
	public void returnBook(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)
			throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		bookService.returnBook(userEmail, bookId);
	}

	@PutMapping("/secure/renew/loan")
	public void renewLoan(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)
			throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		bookService.renewLoan(userEmail, bookId);
	}
}
