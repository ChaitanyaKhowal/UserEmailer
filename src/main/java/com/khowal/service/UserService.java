package com.khowal.service;

import java.util.List;

import com.khowal.entity.User;

public interface UserService {

	public boolean saveUsers(User user);

	public List<User> getAllUsers();

	public User getUserById(Integer userId);

	public boolean deleteUserById(Integer userId);

	public void sendEmail(String recipient, String body, String subject);

}
