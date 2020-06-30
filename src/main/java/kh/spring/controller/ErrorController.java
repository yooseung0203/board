package kh.spring.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/")
public class ErrorController {
	
	@RequestMapping("loginError")
	public String loginError() {
		return "error/loginError";
	}

}
