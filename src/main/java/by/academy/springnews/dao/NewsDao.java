package by.academy.springnews.dao;

import java.util.List;

import by.academy.springnews.model.News;

public interface NewsDao {
	void save(News news);
	void delete(int id);
	void update(News news);
	News find(int id);
	List<News> findAll();
//TODO throws Exception
}