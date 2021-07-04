package ro.onlineShop.onlineShop.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    //select * from user where email = ''
    List<User> findByEmail(String email);
}
