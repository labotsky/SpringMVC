package com.simple.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

public abstract class RESTController<T, ID extends Serializable> {

	private Logger logger = LoggerFactory.getLogger(RESTController.class);

	protected Map<String, String> pageAfterRequest = new HashMap<String, String>();

	private CrudRepository<T, ID> repo;

	private final Class<T> clazz;

	private String layoutName = null;

	public RESTController(CrudRepository<T, ID> repo, Class<T> clazz) {
		this.repo = repo;
		this.clazz = clazz;
	}

	{
		layoutName = this.getClass().getSimpleName().replace("Controller", "").toLowerCase();
		pageAfterRequest.put("listAll", layoutName + "/index");
		pageAfterRequest.put("get", layoutName + "/show");
		pageAfterRequest.put("delete", "redirect:/" + layoutName);
		pageAfterRequest.put("add", layoutName + "/add");
		pageAfterRequest.put("save", "redirect:/" + layoutName + "/index");
		pageAfterRequest.put("error", "error");
	}

	@RequestMapping
	public String listAll(Model model) {
		logger.info("Fetch all list {}", layoutName);
		Iterable<T> all = this.repo.findAll();
		model.addAttribute(layoutName + "List", Lists.newArrayList(all));
		return pageAfterRequest.get("listAll");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable ID id, Model model) {
		logger.info("Fetch {} with id {}", layoutName, id);
		T entity = this.repo.findOne(id);
		if (entity != null) {
			model.addAttribute(layoutName, entity);
			return pageAfterRequest.get("get");
		}
		return pageAfterRequest.get("error");
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable ID id, Model model) {
		logger.info("Delete {} with id {}", layoutName, id);
		this.repo.delete(id);
		model.addAttribute("success", true);
		return pageAfterRequest.get("delete");
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) throws InstantiationException, IllegalAccessException {
		logger.info("Add {}", layoutName);
		model.addAttribute("category", clazz.newInstance());
		return pageAfterRequest.get("add");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute T entity) {
		logger.info("Save {}", layoutName);
		logger.info("Entity {}", entity);
		this.repo.save(entity);
		return pageAfterRequest.get("save");
	}
}
