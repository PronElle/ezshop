package it.polito.ezshop.data;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class UserRepository extends Repository<User> {
    @Override
    User find(Serializable id) {
        return _find(UserImpl.class, id);
    }

    User findByUsername(String username) {
        try {
            Session session = getSession();
            session.beginTransaction();
            User user = session
                    .createQuery("FROM UserImpl WHERE username = :username", UserImpl.class)
                    .setParameter("username", username)
                    .getSingleResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    List<User> findAll() {
        return _findAll(UserImpl.class);
    }

    @Override
    Integer create(User user) {
        return (Integer) _create(user);
    }

    @Override
    User update(User user) {
        return _update(user);
    }

    @Override
    void delete(User user) {
        _delete(user);
    }
}
