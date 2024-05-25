package com.vishnu.springbootlibraryweb.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishnu.springbootlibraryweb.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Page<Review> findByBookId(@RequestParam("book_id") Long bookId, Pageable pageabe);

	Review findByUserEmailAndBookId(String userEmail, Long BookId);

	@Modifying
	@Transactional
	@Query("delete from Review where book_id in :book_id")
	void deleteAllByBooksId(@Param(value = "book_id") Long bookId);
}
