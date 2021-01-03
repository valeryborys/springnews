package by.academy.springnews.dao;

import by.academy.springnews.model.Role;

public interface RoleDao {
    Role getRole(int id) throws DaoException;
}
