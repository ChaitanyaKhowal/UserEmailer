package com.khowal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khowal.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
