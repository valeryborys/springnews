package by.academy.springnews.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import by.academy.springnews.dao.DaoException;
import by.academy.springnews.dao.NewsDao;
import by.academy.springnews.model.News;



@Service
public class NewsServiceImpl implements NewsService{
	
	@Autowired
	private NewsDao newsDao;

	@Override
	@Transactional
	public void save(News news) throws ServiceException {
		try {
			newsDao.save(news);
		} catch (DaoException e) {
			//TODO logger
			throw new ServiceException(e);
			
		}
		
	}

	@Override
	@Transactional
	public void delete(int id) throws ServiceException {
		try {
			newsDao.delete(id);
		} catch (DaoException e) {
			//TODO logger
			throw new ServiceException(e);
		}
		
	}

	@Override
	@Transactional
	public void update(News news) throws ServiceException {
		try {
			newsDao.update(news);
		} catch (DaoException e) {
			//TODO logger
			throw new ServiceException(e);
		}
		
	}

	@Override
	@Transactional
	public News find(int id) throws ServiceException {
		News news;
		try {
			 news = newsDao.find(id);
		} catch (DaoException e) {
			//TODO logger
			throw new ServiceException(e);
		}
		return news;
	}

	@Override
	@Transactional
	public List<News> findAll() throws ServiceException {
		List<News> list;
		try {
			list = newsDao.findAll();
		} catch (DaoException e) {
			//TODO logger
			throw new ServiceException(e);
		}
		return list;
	}

}
