package net.slipp.www.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.test.context.ActiveProfilesResolver;

// Junit Common
public class ActiveProfileResolver implements ActiveProfilesResolver {

	@Override
	public String[] resolve(Class<?> testClass) {
		String profile = StringUtils.defaultIfEmpty(System.getProperty("profile"), "local");
		return new String[] { profile };

	}
}
