package ru.pdasilem.spring_rest.dao;

import org.springframework.stereotype.Repository;
import ru.pdasilem.spring_rest.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsersWithRoles() {
        return entityManager.createQuery("select distinct usr from User usr left join fetch usr.roles", User.class).getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User updUser) {
        entityManager.merge(updUser);
    }

    @Override
    public String getUserPassword(User user) {
        return entityManager
                .createQuery("select u.password from User u where u.id = :id", String.class).setParameter("id", user.getId())
                .getSingleResult();
    }

}
