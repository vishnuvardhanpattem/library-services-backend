package com.vishnu.springbootlibraryweb.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vishnu.springbootlibraryweb.entity.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

	Checkout findByUserEmailAndBookId(String userEmail, Long bookId);

	List<Checkout> findBooksByUserEmail(String userEmail);

	@Modifying
	@Transactional
	@Query("delete from Checkout where book_id in :book_id")
	void deleteAllByBooksId(@Param(value = "book_id") Long bookId);

//	@Modifying
//	@Query("delete from Checkout c where c.book_id = :book_id")
//	void deleteAllBooksById(@Param("book_id") Long bookId);

}
