package ro.onlineShop.onlineShop.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
}
