package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class JpaUserRepository implements  UserRepository{
    private final EntityManager em;
    public JpaUserRepository(EntityManager em){
        this.em = em;
    }
    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(String id) {
        return em.createQuery("select u from User u where id = :id", User.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> users = em.createQuery("select u from User u where name = :name", User.class)
                .setParameter("name", name)
                .getResultList();

        return users.stream().findAny();
    }

    @Override
    public String addUser(User user) {
        em.persist(user);//save랑 비슷한거
        return user.getId();
    }

    @Override
    public User saveUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User deleteUser(String id) {
        Optional<User> user = this.findById(id);
        if (user.isPresent()) {
            em.remove(user.get());
            return user.get();
        }

        return null;
    }

    @Override
    public void initialize() {
        String[] sqls = new String[] {
                "create table if not exists UserBak AS select * from User",
                "truncate table User"
        };

        for (String sql : sqls) {
            em.createNativeQuery(sql).executeUpdate();
        }
    }
    @Override
    public void destroy() {
        String[] sqls = new String[] {
                "truncate table User",
                "insert into User select * from UserBak",
                "drop table UserBak"
        };

        for (String sql : sqls) {
            em.createNativeQuery(sql).executeUpdate();
        }
    }
    @Override
    public boolean existsById(String id) {
        String jpql = "SELECT COUNT(u) > 0 FROM User u WHERE u.id = :id";
        return em.createQuery(jpql, Boolean.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
