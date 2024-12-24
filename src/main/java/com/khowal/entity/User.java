package com.khowal.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NotEmpty(message = "Username Cannot be empty")
	private String userName;
	
	@NotEmpty(message = "Email Cannot be empty")
	@Email(message = "Please enter valid Email")
	private String userEmail;
	
	@NotNull(message = "Phone number Cannot be empty")
	private Long userPhone;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdOn;

	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedOn;

}
