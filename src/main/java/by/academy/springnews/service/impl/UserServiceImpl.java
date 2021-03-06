package by.academy.springnews.service.impl;

import by.academy.springnews.dao.DaoException;
import by.academy.springnews.dao.RoleDao;
import by.academy.springnews.dao.UserDao;
import by.academy.springnews.model.Role;
import by.academy.springnews.model.User;
import by.academy.springnews.service.ServiceException;
import by.academy.springnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final int ROLE_ID=2;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public void save(User user) throws ServiceException {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    Set<Role> roles = new HashSet<>();
        try {
            roles.add(roleDao.getRole(ROLE_ID));
            user.setRoles(roles);
            userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    @Transactional
    public User find(String name) throws ServiceException {
        User user;
        try {
            user = userDao.find(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
