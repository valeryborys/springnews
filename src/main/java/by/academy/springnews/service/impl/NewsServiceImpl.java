package by.academy.springnews.service.impl;


import java.util.List;

import by.academy.springnews.service.NewsService;
import by.academy.springnews.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.academy.springnews.dao.DaoException;
import by.academy.springnews.dao.NewsDao;
import by.academy.springnews.model.News;



@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	private NewsDao newsDao;
	
	private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

	@Override
	@Transactional
	public void save(News news) throws ServiceException {
		try {
			newsDao.save(news);
		} catch (DaoException e) {
			logger.error("DaoException while news DB saving",e);
			throw new ServiceException(e);		
		}	
	}

	@Override
	@Transactional
	public void delete(int id) throws ServiceException {
		try {
			newsDao.delete(id);
		} catch (DaoException e) {
			logger.error("DaoException while news DB deleting",e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void update(News news) throws ServiceException {
		try {
			newsDao.update(news);
		} catch (DaoException e) {
			logger.error("DaoException while news DB updating",e);
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
			logger.error("DaoException while finding News in the DB",e);
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
			logger.error("DaoException while finding News list in the DB",e);
			throw new ServiceException(e);
		}
		return list;
	}

}
