package br.com.devinpeace.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private static final String SPRINT_SECURITY_ROLE_PREFIX = "ROLE_";
	
	protected ClientUser clientUser;

	public static User fromClientUser(ClientUser user) {
		return new User(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Iterator<ClientUser.Role> iterator = clientUser.getRoles().iterator();
		
		List<GrantedAuthority> simpleAuthorities = new ArrayList<>();
		
		while (iterator.hasNext()) 
			simpleAuthorities.add(new SimpleGrantedAuthority(SPRINT_SECURITY_ROLE_PREFIX + iterator.next()));
		
		return simpleAuthorities;
	}

	@Override
	public String getPassword() {
		return clientUser.getPassword();
	}

	@Override
	public String getUsername() {
		return clientUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
