package com.simple.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;
import com.simple.entity.Category;
import com.simple.repository.CategoryRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	CategoryRepository categoryRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		Iterable<Category> allCategory = categoryRepository.findAll();
		model.addAttribute("categoryList", Lists.newArrayList(allCategory));
		return "index";
	}

	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public String services(ModelMap model) {
		return "services";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(ModelMap model) {
		return "contact";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(ModelMap model) {
		return "about";
	}
}
