package com.vishnu.springbootlibraryweb.responsemodels;

import com.vishnu.springbootlibraryweb.entity.Book;

import lombok.Data;

@Data
public class ShelfCurrentLoansResponse {

	private Book book;
	private int daysleft;

	public ShelfCurrentLoansResponse(Book book, int daysleft) {
		this.book = book;
		this.daysleft = daysleft;
	}

}
