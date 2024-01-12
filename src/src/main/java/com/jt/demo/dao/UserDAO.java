package com.jt.demo.dao;


import com.jt.demo.entity.User;

import java.util.List;

public interface UserDAO {
    User findById(int id);
    User save(User theUser);
    void deleteById(int id);
    List<User> findAll();
    List<User> findByFirstName(String theFirstName);

}
