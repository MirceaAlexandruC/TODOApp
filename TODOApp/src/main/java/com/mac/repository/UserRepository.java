package com.mac.repository;

import com.mac.model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepository implements CrudRepository<User, Integer> {

    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> findAll() {
        return null;
    }

    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void deleteById(Integer id) {
        User user = findById(id);
        if(user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(id);
            entityManager.getTransaction().commit();
        }
    }

    public User findById(Integer id) {

        try {
            User user = entityManager.find(User.class, id);
            return user;
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
      return null;
    }

    public User findByUsername (String username) {
        User user = (User)
        entityManager
                .createQuery("SELECT u FROM User u WHERE u.username =: username")
                .setParameter("Username", username)
                .getResultList().get(0);
        return user;

//        int id = entityManager
//                .createQuery("SELECT u FROM User u WHERE u.username =:username")
//                .setParameter("username", username)
//                .getFirstResult();
//        return findById(id);
    }
}
