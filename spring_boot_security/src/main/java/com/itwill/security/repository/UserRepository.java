package com.itwill.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.security.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	 Optional<User> findByUsername(String username);
}
