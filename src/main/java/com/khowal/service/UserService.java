package com.khowal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.khowal.entity.User;

public interface UserService {

	public boolean saveUsers(User user);

	public List<User> getAllUsers();

	public User getUserById(Integer userId);

	public boolean deleteUserById(Integer userId);

	public void sendEmail(String recipient, String body, String subject);

	public String paginationFunction();
	
	public Page<User> getPaginatedUsers(int pageNo, int pageSize);
	
}
