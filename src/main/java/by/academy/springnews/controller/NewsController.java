package by.academy.springnews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import by.academy.springnews.model.News;
import by.academy.springnews.service.NewsService;
import by.academy.springnews.service.ServiceException;

@Controller
@RequestMapping("/")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/list")
	public String listNews(Model model) {
		
		try {
			List<News> news = newsService.findAll();
			model.addAttribute("listNews", news);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "list";
		
	}

}
