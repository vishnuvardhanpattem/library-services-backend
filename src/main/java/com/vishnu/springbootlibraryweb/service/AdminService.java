package com.vishnu.springbootlibraryweb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishnu.springbootlibraryweb.dao.BookRepository;
import com.vishnu.springbootlibraryweb.dao.CheckoutRepository;
import com.vishnu.springbootlibraryweb.dao.ReviewRepository;
import com.vishnu.springbootlibraryweb.entity.Book;
import com.vishnu.springbootlibraryweb.requestmodels.AddBookRequest;

@Service
public class AdminService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CheckoutRepository checkoutRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	public void increaseBookQuantity(Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);

		if (!book.isPresent()) {
			throw new Exception("Book not found");
		}
		book.get().setCopies(book.get().getCopies() + 1);
		book.get().setCopiesAvailable(book.get().getCopiesAvailable() + 1);

		bookRepository.save(book.get());
	}

	public void decreaseBookQuantity(Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);

		if (!book.isPresent() || book.get().getCopies() <= 0 || book.get().getCopiesAvailable() <= 0) {
			throw new Exception("Book not found or Quantity locked");
		}
		book.get().setCopies(book.get().getCopies() - 1);
		book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);

		bookRepository.save(book.get());
	}

	public void postBook(AddBookRequest addBookRequest) {
		Book book = new Book();
		book.setTitle(addBookRequest.getTitle());
		book.setAuthor(addBookRequest.getAuthor());
		book.setDescription(addBookRequest.getDescription());
		book.setCopies(addBookRequest.getCopies());
		book.setCopiesAvailable(addBookRequest.getCopies());
		book.setCategory(addBookRequest.getCategory());
		book.setImg(addBookRequest.getImg());

		bookRepository.save(book);
	}

	public void deleteBook(Long bookId) throws Exception {
		Optional<Book> book = bookRepository.findById(bookId);
		if (!book.isPresent()) {
			throw new Exception("Book not found");
		}
		bookRepository.delete(book.get());
		checkoutRepository.deleteAllByBooksId(bookId);
		reviewRepository.deleteAllByBooksId(bookId);

	}
}
