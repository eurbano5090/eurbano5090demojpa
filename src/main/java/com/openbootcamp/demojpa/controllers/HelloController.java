package com.openbootcamp.demojpa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hola")
	public  String HolaMundo() {
		
		return "Hola Mundo que tal vamos";
		
	}
}
