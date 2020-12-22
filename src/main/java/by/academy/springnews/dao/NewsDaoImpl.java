package by.academy.springnews.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.academy.springnews.model.News;

@Repository
public class NewsDaoImpl implements NewsDao{
	
//TODO logger
	
	@Autowired
	private SessionFactory factory;
	
	private static final String HQL_FROM_NEWS = "from news";
	
	@Override
	public void save(News news) throws DaoException {
		Session session = factory.getCurrentSession();
		session.persist(news);
		
	}

	@Override
	public void delete(int id) throws DaoException {
		Session session = factory.getCurrentSession();
		News news = (News) session.load(News.class, id);
		if(news != null) {
			session.delete(news);
		}
		
	}

	@Override
	public void update(News news) throws DaoException {
		Session session = factory.getCurrentSession();
		session.update(news);
		
	}

	@Override
	public News find(int id) throws DaoException {
		Session session = factory.getCurrentSession();
		News news = (News) session.load(News.class, id);
		return news;
	}

	@Override
	public List<News> findAll() throws DaoException {
		Session session = factory.getCurrentSession();
		List<News> list = (List<News>) session.createQuery(HQL_FROM_NEWS).list();
		return list;
	}

}
