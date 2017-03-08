package com.evergrande.wc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evergrande.base.controller.BaseController;

@Controller
@RequestMapping("base/wc")
public class WelcomController extends BaseController {
	
	@RequestMapping("welcome")
	public String welcomePage(){
		return viewPage("welcome");
	}
}
