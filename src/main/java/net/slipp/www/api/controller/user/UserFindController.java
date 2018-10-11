package net.slipp.www.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.slipp.www.api.domain.user.SlippUser;
import net.slipp.www.api.service.user.SlippUserFindService;
import net.slipp.www.api.support.ResponseWrapper;

@Api(value = "사용자 조회", tags = "사용자 API")
@RequestMapping("/v1/users")
@RestController
public class UserFindController {

	@Autowired
	private SlippUserFindService slippUserFindService;

	@ApiOperation(value = "사용자 조회 by email", responseReference = "ResponseWrapper<SlippUser>")
	@GetMapping("/by-email")
	public ResponseWrapper<SlippUser> success(@RequestParam("email") String targetEmail) {
		SlippUser slippUser = slippUserFindService.findByEmail(targetEmail);
		return ResponseWrapper.success(slippUser);
	}
}
