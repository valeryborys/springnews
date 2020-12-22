package by.academy.springnews.dao;

import java.util.List;

import by.academy.springnews.model.News;

public interface NewsDao {
	void save(News news) throws DaoException;

	void delete(int id) throws DaoException;

	void update(News news) throws DaoException;

	News find(int id) throws DaoException;

	List<News> findAll() throws DaoException;
}
