package com.example.Wordle.repository;

import com.example.Wordle.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserRepositoryImp implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

}
