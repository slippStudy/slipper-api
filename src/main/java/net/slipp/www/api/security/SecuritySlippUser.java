package net.slipp.www.api.security;

import java.io.Serializable;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.google.common.collect.Lists;

import net.slipp.www.api.domain.user.SlippUser;

@SuppressWarnings("serial")
public class SecuritySlippUser extends User implements Serializable {

	public SecuritySlippUser(SlippUser user) {
		super(user.getEmail(), user.getPassword(),
				Lists.newArrayList(new SimpleGrantedAuthority(user.getUserRole().name())));
	}

}
