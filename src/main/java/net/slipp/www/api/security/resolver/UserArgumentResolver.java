package net.slipp.www.api.security.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import net.slipp.www.api.domain.user.SlippUser;
import net.slipp.www.api.security.model.LoginUser;
import net.slipp.www.api.security.model.SlippLoginUser;
import net.slipp.www.api.service.user.SlippUserFindService;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private SlippUserFindService slippUserFindService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(LoginUser.class) != null
				&& parameter.getParameterType().equals(SlippLoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			UserDetails userDetails = ((UserDetails) principal);
			SlippUser slippUser = slippUserFindService.findByEmail(userDetails.getUsername());

			return SlippLoginUser.builder().email(slippUser.getEmail()).name(slippUser.getName())
					.userRole(slippUser.getUserRole()).build();
		}
		return null;
	}

}
