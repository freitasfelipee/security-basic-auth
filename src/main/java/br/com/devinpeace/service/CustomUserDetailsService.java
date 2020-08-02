package br.com.devinpeace.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.devinpeace.auth.ClientUser;
import br.com.devinpeace.auth.ServerUser;
import br.com.devinpeace.auth.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ServerUser serverUser;

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		if (username.equals(this.serverUser.getUsername())) 
			return User.fromClientUser(ClientUser.builder()
													.username(username)
													.password(new BCryptPasswordEncoder().encode(serverUser.getPassword()))
													.roles(Arrays.asList(ClientUser.Role.ADMIN, ClientUser.Role.USER))
												.build());
		
		throw new UsernameNotFoundException("Could not found user: " + username + ".");

	}
	
}
