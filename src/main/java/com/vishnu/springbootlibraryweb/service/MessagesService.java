package com.vishnu.springbootlibraryweb.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishnu.springbootlibraryweb.dao.MessageRepository;
import com.vishnu.springbootlibraryweb.entity.Message;
import com.vishnu.springbootlibraryweb.requestmodels.AdminQuestionRequest;

@Service
@Transactional
public class MessagesService {

	@Autowired
	private MessageRepository messageRepository;

	public void postMessage(Message messageRequest, String userEmail) {
		Message message = new Message(messageRequest.getTitle(), messageRequest.getQuestion());
		message.setUserEmail(userEmail);
		messageRepository.save(message);
	}

	public void putMessaage(AdminQuestionRequest adminQuestionRequest, String userEmail) throws Exception {
		Optional<Message> message = messageRepository.findById(adminQuestionRequest.getId());
		if (!message.isPresent()) {
			throw new Error("Something went wrong");
		}
		message.get().setAdminEmail(userEmail);
		message.get().setResponse(adminQuestionRequest.getResponse());
		message.get().setClosed(true);

		messageRepository.save(message.get());
	}
}
