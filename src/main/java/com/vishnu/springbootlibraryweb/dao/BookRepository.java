package com.vishnu.springbootlibraryweb.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishnu.springbootlibraryweb.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Page<Book> findBytitleContaining(@RequestParam("title") String title, Pageable pageable);

	Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);

//	@Query("select o from Book o where id in:book_ids")
//	List<Book> findBooksByBookIds(@Param("book_ids") List<Long> bookId);
	List<Book> findByIdIn(List<Long> bookIds);

}
