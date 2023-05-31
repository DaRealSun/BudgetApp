package com.budget.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budget.demo.data.User;
import com.budget.demo.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("User id" + id));
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public User updateUser(User user, Long id) {
		return userRepository.findById(id)
				.map(t -> {
					t.setId(user.getId());
					t.setName(user.getName());
					t.setTransactions(user.getTransactions());
					return userRepository.save(t);
				})
				.orElseGet(() -> {
					user.setId(id);
					return userRepository.save(user);
				});
	}

}
