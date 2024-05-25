package com.vishnu.springbootlibraryweb.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vishnu.springbootlibraryweb.dao.ReviewRepository;
import com.vishnu.springbootlibraryweb.entity.Review;
import com.vishnu.springbootlibraryweb.requestmodels.ReviewRequest;

@Service
@Transactional
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
		Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
		if (validateReview != null) {
			throw new Exception("Review already created");
		}

		Review review = new Review();
		review.setBookId(reviewRequest.getBookId());
		review.setRating(reviewRequest.getRating());
		review.setUserEmail(userEmail);
		if (reviewRequest.getReviewDescription().isPresent()) {
			review.setReviewDescription(reviewRequest.getReviewDescription().map(Object::toString).orElse(null));
		}
		review.setDate(Date.valueOf(LocalDate.now()));
		reviewRepository.save(review);
	}

	public Boolean userReviewListed(String userEmail, Long bookId) {
		Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
		if (validateReview != null) {
			return true;
		} else {
			return false;
		}
	}

}
