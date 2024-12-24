package com.khowal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.khowal.entity.User;
import com.khowal.repo.UserRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public boolean saveUsers(User user) {

		if (user.getUserId() != null && userRepo.existsById(user.getUserId())) {
			userRepo.save(user);
			return true;
		} else {
			User savedUser = userRepo.save(user);
			return savedUser.getUserId() != null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> byId = userRepo.findById(id);
		return byId.orElse(null);
	}

	@Override
	public boolean deleteUserById(Integer id) {

		Optional<User> byId = userRepo.findById(id);

		if (byId.isPresent()) {
			userRepo.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void sendEmail(String recipient, String body, String subject) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setTo(recipient);
			helper.setSubject(subject);
			helper.setText(body, true);
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {

			e.printStackTrace();
			throw new RuntimeException("Failed to send email");

		}

	}

}
