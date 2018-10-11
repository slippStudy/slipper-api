package net.slipp.www.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.slipp.www.api.support.ResponseWrapper;

@RestController
public class HealthController {

	@GetMapping("/api/health")
	public ResponseWrapper<String> health() {
		return ResponseWrapper.success("OK");
	}

}
