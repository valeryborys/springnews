package by.academy.springnews.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.academy.springnews.model.News;
import by.academy.springnews.service.NewsService;
import by.academy.springnews.service.ServiceException;

@Controller
@RequestMapping("/")
public class NewsController {
	//TODO magic values
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
	
	@GetMapping("/show")
	public String findCertainNews(@RequestParam("id") int id, Model model) {
		try {
			News news = newsService.find(id);
			System.out.println(news.getTitle());
			model.addAttribute("certainNews", news);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "news";
		
	}
	
	@RequestMapping("/localeChange")
	public void localeChange(@RequestParam("locale") String locale, HttpServletRequest req, HttpServletResponse resp) {
		req.getSession(true).setAttribute("locale", req.getParameter("locale"));
		// TODO logger.info("Locale changed to "+ req.getParameter("locale"));
		try {
			resp.sendRedirect(req.getHeader("Referer"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
