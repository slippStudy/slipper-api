package net.slipp.www.api.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.slipp.www.api.domain.user.SlippUser;
import net.slipp.www.api.repository.SlippUserRepository;

@Service
public class SlippUserFindService {

	@Autowired
	private SlippUserRepository slippUserRepository;

	public SlippUser findByEmail(String email) {
		return slippUserRepository.findByEmail(email);
	}

}
