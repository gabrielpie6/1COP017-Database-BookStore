package com.bd12024.BookStore;


import com.bd12024.BookStore.entities.Book;
import com.bd12024.BookStore.entities.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    ProductRepository productRepository;

    @GetMapping(value={"/index", "/"})
    public String showProductsList(Model model) {
        List<Product> mylist = new ArrayList<>();
        mylist.add(
                new Book(1, 2.0, 15, 1,"oi",1,"io", 1,100, null, null)
        );

        model.addAttribute("products", mylist);
        return "index";
    }
}
