package com.simple.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.entity.Category;
import com.simple.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController extends RESTController<Category, Long> {

	{
		pageAfterRequest.put("delete", "redirect:/");
		pageAfterRequest.put("save", "redirect:/");
		pageAfterRequest.put("update", "redirect:/");
	}

	@Autowired
	public CategoryController(CategoryService service) {
		super(service, Category.class);
	}

}
