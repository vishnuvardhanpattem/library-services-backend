package com.vishnu.springbootlibraryweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishnu.springbootlibraryweb.entity.Message;
import com.vishnu.springbootlibraryweb.requestmodels.AdminQuestionRequest;
import com.vishnu.springbootlibraryweb.service.MessagesService;
import com.vishnu.springbootlibraryweb.utils.ExtractJWT;

//@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

	@Autowired
	private MessagesService messagesService;

	@PostMapping("/secure/add/message")
	public void postMessage(@RequestHeader(value = "Authorization") String token, @RequestBody Message messageRequest) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		messagesService.postMessage(messageRequest, userEmail);
	}

	@PutMapping("/secure/admin/message")
	public void putMessage(@RequestHeader(value = "Authorization") String token,
			@RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
		String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");

		if (admin == null || !admin.equals(admin)) {
			throw new Exception("Adminstration Page Only!");
		}
		messagesService.putMessaage(adminQuestionRequest, userEmail);
	}
}
