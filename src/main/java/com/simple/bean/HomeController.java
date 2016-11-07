package com.simple.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<String> list = new ArrayList<String>();
		list.add("Sergey");
		list.add("Alex");
		list.add("Igor");
		model.addAttribute("message", list);
		return "index";
	}
}
