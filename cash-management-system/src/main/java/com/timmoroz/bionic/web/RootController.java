package com.timmoroz.bionic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
}