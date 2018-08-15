package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.beans.Sample;

@Controller
public class SampleController {

	private static final String welcomemsg = "Welcome Mr. %s!";

	@GetMapping("/welcome/user")
	@ResponseBody
	public Sample welcomeUser(@RequestParam(name="name", required=false, defaultValue="Java Fan") String name) {
		return new Sample(String.format(welcomemsg, name));
	}
}
