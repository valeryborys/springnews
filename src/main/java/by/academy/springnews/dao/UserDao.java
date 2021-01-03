package by.academy.springnews.dao;

import by.academy.springnews.model.User;

public interface UserDao {
    User find(String name) throws DaoException;
    void save(User user) throws DaoException;
}
