package net.slipp.www.api.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.slipp.www.api.domain.user.SlippUserRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlippLoginUser {
	private Long id;

	private String name;

	private String email;

	private SlippUserRole userRole;
}
