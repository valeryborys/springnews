package by.academy.springnews.dao.impl;

import java.util.List;

import by.academy.springnews.dao.DaoException;
import by.academy.springnews.dao.NewsDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.springnews.model.News;

@Repository
public class NewsDaoImpl implements NewsDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String HQL_FROM_NEWS = "from News order by id desc";
	
	@Override
	public void save(News news) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(news);
		
	}

	@Override
	public void delete(int id) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		News news = (News) session.load(News.class, id);
		if(news != null) {
			session.delete(news);
		}
		
	}

	@Override
	public void update(News news) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		session.update(news);
		
	}

	@Override
	public News find(int id) throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		News news = (News) session.get(News.class, id);
		return news;
	}

	@Override
	public List<News> findAll() throws DaoException {
		Session session = sessionFactory.getCurrentSession();
		List<News> list = (List<News>) session.createQuery(HQL_FROM_NEWS, News.class).getResultList();
		return list;
	}

}
