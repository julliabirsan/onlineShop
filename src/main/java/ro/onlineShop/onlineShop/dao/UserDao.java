package ro.onlineShop.onlineShop.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(String email, String password){
        //jdbcTemplate.update("insert into user values(null, ?,?)", email, password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        entityManager.persist(user);

    }

    public List<User> findByEmail(String email){
        Query query = entityManager.createNativeQuery("SELECT * from user where email ='" + email + "'", User.class);
        return query.getResultList();
    }
}
