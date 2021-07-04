package ro.onlineShop.onlineShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.onlineShop.onlineShop.dao.Category;
import ro.onlineShop.onlineShop.dao.Product;
import ro.onlineShop.onlineShop.dao.ProductDao;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductDummyController {
    @Autowired
    ProductDao productDao;

    @GetMapping("/addDummyProducts")
    @ResponseBody
    public List<Product> addDummyProducts(){
        Category c1 = new Category();
        c1.setName("telefoane");
        Category c2 = new Category();
        c2.setName("televizoare");
        Product p1 = new Product();
        p1.setCategory(c1);
        p1.setName("Samsung Galaxy A4");
        p1.setPrice(1300);
        p1.setQuantity(100);
        productDao.save(p1);

        Product p2 = new Product();
        p2.setName("Sony T1");
        p2.setPrice(1000);
        p2.setCategory(c2);
        p2.setQuantity(20);
        productDao.save(p2);

        List<Product> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        return list;
    }

}
