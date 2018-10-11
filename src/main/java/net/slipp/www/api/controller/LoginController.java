package net.slipp.www.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.slipp.www.api.security.model.LoginUser;
import net.slipp.www.api.security.model.SlippLoginUser;
import net.slipp.www.api.support.ResponseWrapper;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class LoginController {

	@ApiIgnore
	@GetMapping("/api/login-success")
	public ResponseWrapper<SlippLoginUser> success(@LoginUser SlippLoginUser slippUser) {
		return ResponseWrapper.success(slippUser);
	}
}
