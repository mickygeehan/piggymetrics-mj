package com.piggymetrics.auth.service.security;

import com.piggymetrics.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService {

	@Autowired
	private UserRepository repository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		return repository.findById(username).orElseThrow(()->new UsernameNotFoundException(username));
//	}
}
