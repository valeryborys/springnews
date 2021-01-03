package by.academy.springnews.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import static by.academy.springnews.controller.ConstantValues.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.academy.springnews.model.News;
import by.academy.springnews.service.NewsService;
import by.academy.springnews.service.impl.NewsValidationService;
import by.academy.springnews.service.ServiceException;

@Controller
@RequestMapping("/")
public class NewsController {
	
	private static final Logger logger = LogManager.getLogger(NewsController.class);
	
	@Autowired
	private NewsService newsService;
	
	
	@RequestMapping("/list")
	public String listNews(Model model) {
		
		try {
			List<News> news = newsService.findAll();
			model.addAttribute(LIST_NEWS, news);
		} catch (ServiceException e) {
				logger.error("Main page presentation service exception", e);
				return ERROR_PAGE;
		}
		return LIST;
	}
	
	
	@GetMapping("/addForm")
	public String addForm(Model model) {
		News news = new News();
		model.addAttribute(NEWS,news);
	return FORM;	
	}
	
	
	@GetMapping("/editForm")
	public String editForm(@RequestParam(ID)int id, Model model) {
		News news=null;
		try {
			news = newsService.find(id);
		} catch (ServiceException e) {
			logger.error("Service exception while savig news edit", e);
			return ERROR_PAGE;
		}
		model.addAttribute(NEWS,news);
	return FORM;	
	}
	
	
	@PostMapping("/save")
	public String save(@ModelAttribute(NEWS) News news, Model model) {
		if (!NewsValidationService.newsValidation(news)) {
			model.addAttribute(TITLE_WARNING, NewsValidationService.titleValidation(news.getTitle()));
			model.addAttribute(BRIEF_WARNING, NewsValidationService.briefValidation(news.getBrief()));
			model.addAttribute(CONTENT_WARNING,NewsValidationService.contentValidation(news.getContent()));
			model.addAttribute(NEWS, news);
			return FORM;
		}
		Timestamp datetime=null;
		if (news.getDatetime() == null) {
			datetime = new Timestamp(System.currentTimeMillis());
			news.setDatetime(datetime);
		}
		try {
			newsService.save(news);
		} catch (ServiceException e) {
			logger.error("Service exception while savig news", e);
			return ERROR_PAGE;
		}
		return REDIRECT+LIST;
	}
	
	
	@GetMapping("/show")
	public String findCertainNews(@RequestParam(ID) int id, Model model) {
		try {
			News news = newsService.find(id);
			model.addAttribute(CERTAIN_NEWS, news);
		} catch (ServiceException e) {
			logger.error("Finding news service exception", e);
			return ERROR_PAGE;
		}
		return NEWS;
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam(ID) int id) {
		try {
			newsService.delete(id);
		} catch (ServiceException e) {
			logger.error("Service exception while deleting news", e);
			return ERROR_PAGE;
		}
		return REDIRECT+LIST;
	}
	
	
	@PostMapping("/groupDelete")
	public String groupDelete(@RequestParam(DELETE_CHECKBOX) String[] checkboxes) {
		int id;
		for(String checkbox : checkboxes) {
			id = Integer.parseInt(checkbox);
			try {
				newsService.delete(id);
			} catch (ServiceException e) {
				logger.error("Service exception while deleting news", e);
				return ERROR_PAGE;
			}
		}
		return REDIRECT+LIST;
	}
	
	
	@RequestMapping("/localeChange")
	public void localeChange(@RequestParam(LOCALE) String locale, HttpServletRequest req, HttpServletResponse resp) {
		req.getSession(true).setAttribute(LOCALE, req.getParameter(LOCALE));
		logger.info("Locale changed to "+ req.getParameter(LOCALE));
		try {
			resp.sendRedirect(req.getHeader(REFERER));
		} catch (IOException e) {
			logger.error("IO exception while locale changing", e);
		}
	}
}
