package com.thefauxpho.The.Faux.Pho.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandleController implements ErrorController {

	@RequestMapping(value="/error")
	public String handleError() {
		return "forward:/index.html";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
