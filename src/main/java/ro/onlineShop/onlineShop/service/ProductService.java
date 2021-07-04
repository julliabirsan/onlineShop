package ro.onlineShop.onlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.onlineShop.onlineShop.dao.Product;
import ro.onlineShop.onlineShop.dao.ProductDao;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public List<Product> getAllProducts(){
        return (List<Product>) productDao.findAll();
    }
}
