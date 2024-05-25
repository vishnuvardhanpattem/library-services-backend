package com.vishnu.springbootlibraryweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.vishnu.springbootlibraryweb.requestmodels.PaymentInfoRequest;
import com.vishnu.springbootlibraryweb.service.PaymentService;
import com.vishnu.springbootlibraryweb.utils.ExtractJWT;

//@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/payment/secure")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymenetIntent(@RequestBody PaymentInfoRequest paymentInfoRequest)
			throws StripeException {
		PaymentIntent paymentIntent = paymentService.createPaymentIntent(paymentInfoRequest);
		String paymentStr = paymentIntent.toJson();

		return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
	}

	@PutMapping("/payment-complete")
	public ResponseEntity<String> stripePaymentComplete(@RequestHeader(value = "Authorization") String token)
			throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		if (userEmail == null) {
			throw new Exception("User email is missing");
		}
		return paymentService.stripePayment(userEmail);
	}
}
