package com.jt.demo.dao;

import com.jt.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAOJpaImpl implements UserDAO {

    private EntityManager entityManager;
    @Autowired
    public UserDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public User findById(int id) {
        User theUser = entityManager.find(User.class, id);
        return theUser;
    }
    @Override
    public User save(User theUser) {
        User dbUser = entityManager.merge(theUser);
        return dbUser;
    }
    @Override
    public void deleteById(int id) {
        User dbUser = entityManager.find(User.class, id);
        entityManager.remove(dbUser);
    }
    @Override
    public List<User> findAll() {

        // create a query and execute it to create a list of users and return it
        TypedQuery<User> query = entityManager.createQuery("From User", User.class);
        List<com.jt.demo.entity.User> users = query.getResultList();
        return users;
    }

    @Override
    public List<User> findByFirstName(String theFirstName) {

        TypedQuery<User> query = entityManager.createQuery("From User WHERE firstName=:theData", User.class);
        query.setParameter("theData", theFirstName);
        return query.getResultList();
    }
}
