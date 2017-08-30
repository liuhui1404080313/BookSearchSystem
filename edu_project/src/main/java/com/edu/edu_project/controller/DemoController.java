package com.edu.edu_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {
	 @RequestMapping(value = "/toPage1.htm")
	 public ModelAndView toPage1() {
	  return new ModelAndView("page1");
	 }
	}
