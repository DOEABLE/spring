package com.hana4.demo1.repository;

import com.hana4.demo1.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class JpaUserRepository implements UserRepository {
    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Long addUser(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public User saveUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> user = this.findById(id);
        if (user.isPresent()) {
            em.remove(user.get());
            return user.get();
        }

        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        // return Optional.ofNullable(users.get(id));
        return em.createQuery("select u from User u where id = :id", User.class)
                .setParameter("id", id)
                .getResultList().stream().findAny();
        // .getSingleResult();
        // return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> users = em.createQuery("select u from User u where name = :name", User.class)
                .setParameter("name", name)
                .getResultList();

        return users.stream().findAny();
    }

    @Override
    public void initialize() {
        System.out.println("IIIIIIIIIIIIIIIIIIIII");
        String[] sqls = new String[]{
                "create table if not exists DemoUserBak AS select * from DemoUser",
                "delete from DemoUser where id > 0",
                "alter table DemoUser auto_increment = 1"
        };

        for (String sql : sqls) {
            em.createNativeQuery(sql).executeUpdate();
        }
    }

    @Override
    public void destroy() {
        System.out.println("DDDDDDDDDDDDDDDDDDD");
        String[] sqls = new String[]{
                "delete from DemoUser where id > 0",
                "insert into DemoUser select * from DemoUserBak",
                "drop table DemoUserBak"
        };

        for (String sql : sqls) {
            em.createNativeQuery(sql).executeUpdate();
        }
    }
}