package com.jt.demo.service;

import com.jt.demo.dao.UserDAO;
import com.jt.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }
    @Transactional
    @Override
    public User save(User theUser) {
        return userDAO.save(theUser);
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public List<User> findByFirstName(String theFirstName) {
        return userDAO.findByFirstName(theFirstName);
    }
}
