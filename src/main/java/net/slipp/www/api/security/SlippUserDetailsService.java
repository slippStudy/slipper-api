package net.slipp.www.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.slipp.www.api.domain.user.SlippUser;
import net.slipp.www.api.service.user.SlippUserFindService;

@Service
public class SlippUserDetailsService implements UserDetailsService {

	@Autowired
	private SlippUserFindService slippUserFindService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SlippUser slippUser = slippUserFindService.findByEmail(email);
		return new SecuritySlippUser(slippUser);
	}

}
