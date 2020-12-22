package by.academy.springnews.service;

import java.util.List;

import by.academy.springnews.model.News;

public interface NewsService {
	void save(News news) throws ServiceException;

	void delete(int id) throws ServiceException;

	void update(News news) throws ServiceException;

	News find(int id) throws ServiceException;

	List<News> findAll() throws ServiceException;
}
