package by.academy.springnews.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import by.academy.springnews.service.NewsValidationService;
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
	
	@GetMapping("/addForm")
	public String addForm(Model model) {
		News news = new News();
		model.addAttribute("news",news);
	return "form";	
	}
	
	@GetMapping("/editForm")
	public String editForm(@RequestParam("id")int id, Model model) {
		News news=null;
		try {
			news = newsService.find(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("news",news);
	return "form";	
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("news") News news, Model model) {
		if (!NewsValidationService.newsValidation(news)) {
			model.addAttribute("titleWarning", NewsValidationService.titleValidation(news.getTitle()));
			model.addAttribute("briefWarning", NewsValidationService.briefValidation(news.getBrief()));
			model.addAttribute("contentWarning",NewsValidationService.contentValidation(news.getContent()));
			model.addAttribute("news", news);
			return "/form";
		}
		Timestamp datetime=null;
		if (news.getDatetime() == null) {
			datetime = new Timestamp(System.currentTimeMillis());
			news.setDatetime(datetime);
		}
		try {
			newsService.save(news);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/list";
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
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		try {
			newsService.delete(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/list";
	}
	
	@PostMapping("/groupDelete")
	public String groupDelete(@RequestParam("deleteCheckbox") String[] checkboxes) {
		int id;
		for(String checkbox : checkboxes) {
			id = Integer.parseInt(checkbox);
			try {
				newsService.delete(id);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/list";
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
