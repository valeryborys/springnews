package by.academy.springnews.dao.impl;

import by.academy.springnews.dao.DaoException;
import by.academy.springnews.dao.RoleDao;
import by.academy.springnews.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final String HQL_GET_ROLE="from Role where id =: roleId";

    @Override
    public Role getRole(int id) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery(HQL_GET_ROLE, Role.class);
        query.setParameter("roleId", id);
        Role role = query.uniqueResult();
        return role;

    }
}
