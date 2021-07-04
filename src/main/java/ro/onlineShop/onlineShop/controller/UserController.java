package ro.onlineShop.onlineShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.onlineShop.onlineShop.dao.Product;
import ro.onlineShop.onlineShop.dao.User;
import ro.onlineShop.onlineShop.security.UserSession;
import ro.onlineShop.onlineShop.service.ProductService;
import ro.onlineShop.onlineShop.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserSession userSession;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    static int itemsCart;
    List<Product> productList;

    @GetMapping("/register-form")
    public ModelAndView registerAction(@RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("password-again") String password2){
        ModelAndView modelAndView = new ModelAndView("register");

        //ce facem daca parolele sunt identice?
        //daca totul este ok, salvam in bd
        List<User> users = userService.getUsersByEmail(email);
        if (users.size() > 0) {
            modelAndView.addObject("message", "Acest email este deja folosit");
            return modelAndView;
        }
        if(!password.equals(password2)){
            modelAndView.addObject("message", "Parolele nu sunt identice");
        } else {
            //salvare in baza de date
            userService.save(email, password);
        }

        //redirectioneaza user-ul catre pagina index.html
        return new ModelAndView("redirect:index.html");
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");

    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView("index");

        //cum fac loginul? cum verific daca utilizatorul este inregistrat?
        List<User> userList = userService.getUsersByEmail(email);
        if(userList.size() == 0){
            modelAndView.addObject("message", "Credentialele nu sunt corecte");
        }
        if(userList.size() > 1){
            modelAndView.addObject("message", "Credentialele nu sunt corecte");
        }

        if(userList.size() == 1){
            User userFromDatabase = userList.get(0);
            if(!userFromDatabase.getPassword().equals(password)){
                modelAndView.addObject("message", "Credentialele nu sunt corecte");
            } else {
                userSession.setId(userFromDatabase.getId());
                modelAndView = new ModelAndView("redirect:/dashboard");
            }
        }
        return modelAndView;
    }

    @GetMapping("dashboard")
    public ModelAndView dashboard(){
        ModelAndView modelAndView = new ModelAndView("dashboard");

        int id = userSession.getId();
        if (id == 0){
            return new ModelAndView("redirect:/index.html");
        }

        productList = productService.getAllProducts();
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("items", itemsCart);
        //cum verific daca user-ul este logat sau nu?
        return modelAndView;
    }

    @PostMapping("/addToCart")
    public ModelAndView addToCart(@RequestParam("productId") Integer id){
        ModelAndView modelAndView = new ModelAndView("dashboard");
        System.out.println(id);
        itemsCart++;
        modelAndView.addObject("productList", productList);
        modelAndView.addObject("items", itemsCart);
        return modelAndView;
    }
}
