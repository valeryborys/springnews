package by.academy.springnews.dao;

import by.academy.springnews.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final String HQL_GET_USER = "from Users where username =: username";

    @Override
    public User find(String name) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(HQL_GET_USER, User.class);
        query.setParameter("username", name);
        User user = query.uniqueResult();
        return user;

    }

    @Override
    public void save(User user) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);

    }


}
