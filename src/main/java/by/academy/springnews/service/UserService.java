package by.academy.springnews.service;

import by.academy.springnews.model.User;

public interface UserService {
    void save(User user) throws ServiceException;
    User find(String name) throws ServiceException;
}
