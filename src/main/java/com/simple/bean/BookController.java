package com.simple.bean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.entity.Book;
import com.simple.entity.Category;
import com.simple.service.CategoryService;
import com.simple.service.RESTService;

/**
 * 
 * @author Labotski_SV
 *
 */
@Controller
@RequestMapping("/book")
public class BookController extends RESTController<Book, Long> {

	@Autowired
	private CategoryService categoryService;

	{
		pageAfterRequest.put("delete", "redirect:/");
		pageAfterRequest.put("save", "redirect:/");
		pageAfterRequest.put("update", "redirect:/");
	}

	public BookController(RESTService<Book, Long> service) {
		super(service, Book.class);
	}

	@ModelAttribute("categoryOptions")
	public Map<Long, String> getCategoriesName() {
		Map<Long, String> categoryNames = new HashMap<Long, String>();
		for (Category c : categoryService.findAll()) {
			categoryNames.put(c.getId(), c.getName());
		}
		return categoryNames;
	}

}
