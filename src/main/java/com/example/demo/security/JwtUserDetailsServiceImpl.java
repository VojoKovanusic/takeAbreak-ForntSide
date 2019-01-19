package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.dao.UserDao;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	 UserDao userDao;
   @Autowired
	public JwtUserDetailsServiceImpl(UserDao userDao) {
	 
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByEmailIgnoreCase(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No User found with username '%s'.", username));
		} else {
			return JwtuserFactory.create(user);
		}
		
	}

}
