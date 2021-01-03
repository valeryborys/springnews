package by.academy.springnews.service.impl;

import by.academy.springnews.dao.DaoException;
import by.academy.springnews.dao.UserDao;
import by.academy.springnews.model.Role;
import by.academy.springnews.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = null;
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        try {
            user = userDao.find(name);
            for (Role role : user.getRoles()) {
                grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthoritySet);
    }
}
