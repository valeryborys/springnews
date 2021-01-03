package by.academy.springnews.service;

public interface SecurityService {

    String findLoggedInUser(String username) throws ServiceException;

    void autoLogin(String username, String password) throws ServiceException;
}
