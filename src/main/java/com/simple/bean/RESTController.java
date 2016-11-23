package com.simple.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.service.RESTService;

/**
 * 
 * @author Labotski_SV
 *
 * @param <T>
 * @param <ID>
 */
public abstract class RESTController<T, ID extends Serializable> {

	private Logger logger = LoggerFactory.getLogger(RESTController.class);

	protected Map<String, String> pageAfterRequest = new HashMap<String, String>();

	private RESTService<T, ID> service;

	private final Class<T> clazz;

	private String layoutName = null;

	public RESTController(RESTService<T, ID> service, Class<T> clazz) {
		this.service = service;
		this.clazz = clazz;
	}

	{
		layoutName = this.getClass().getSimpleName().replace("Controller", "").toLowerCase();
		pageAfterRequest.put("listAll", layoutName + "/index");
		pageAfterRequest.put("get", layoutName + "/show");
		pageAfterRequest.put("delete", "redirect:/" + layoutName);
		pageAfterRequest.put("add", layoutName + "/add");
		pageAfterRequest.put("save", "redirect:/" + layoutName);
		pageAfterRequest.put("edit", layoutName + "/edit");
		pageAfterRequest.put("update", "redirect:/" + layoutName);
		pageAfterRequest.put("error", "error");
	}

	/**
	 * Request mapping for fetch all entities
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String listAll(Model model) {
		logger.info("Fetch all list {}", layoutName);
		List<T> all = service.findAll();
		model.addAttribute(layoutName + "List", all);
		return pageAfterRequest.get("listAll");
	}

	/**
	 * Request mapping for fetch entity by id
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String get(@PathVariable ID id, Model model) {
		logger.info("Fetch {} with id {}", layoutName, id);
		T entity = service.findById(id);
		if (entity != null) {
			model.addAttribute(layoutName, entity);
			return pageAfterRequest.get("get");
		}
		return pageAfterRequest.get("error");
	}

	/**
	 * Request mapping for delete entity by id
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable ID id, Model model) {
		logger.info("Delete {} with id {}", layoutName, id);
		service.deleteById(id);
		model.addAttribute("success", true);
		return pageAfterRequest.get("delete");
	}

	/**
	 * Request mapping for render Add page
	 * 
	 * @param model
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) throws InstantiationException, IllegalAccessException {
		logger.info("Add {}", layoutName);
		model.addAttribute(layoutName, clazz.newInstance());
		return pageAfterRequest.get("add");
	}

	/**
	 * Request mapping for save entity
	 * 
	 * @param model
	 * @param entity
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @Valid @ModelAttribute T entity, Errors errors) {
		logger.info("Save {}", layoutName);
		logger.info("Entity {}", entity);
		logger.info("{}", errors);
		if (errors.hasErrors()) {
			logger.info("Validation errors number is {}", errors.getErrorCount());
			return pageAfterRequest.get("add");
		}
		service.save(entity);
		return pageAfterRequest.get("save");
	}

	/**
	 * Request mapping for render Edit page
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable ID id, Model model) {
		logger.info("Edit {}", layoutName);
		model.addAttribute(layoutName, service.findById(id));
		return pageAfterRequest.get("edit");
	}

	/**
	 * Request mapping for update entity
	 * 
	 * @param model
	 * @param entity
	 * @param errors
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @Valid @ModelAttribute T entity, Errors errors) {
		logger.info("Update {}", layoutName);
		logger.info("Entity {}", entity);
		if (errors.hasErrors()) {
			logger.info("Validation errors number is {}", errors.getErrorCount());
			return pageAfterRequest.get("edit");
		}
		service.save(entity);
		return pageAfterRequest.get("update");
	}
}
