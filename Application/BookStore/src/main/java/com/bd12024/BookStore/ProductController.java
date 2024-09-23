package com.bd12024.BookStore;


import com.bd12024.BookStore.dao.DAOFactory;
import com.bd12024.BookStore.dao.GenreDAO;
import com.bd12024.BookStore.dao.ThemeDAO;
import com.bd12024.BookStore.entities.Book;
import com.bd12024.BookStore.entities.Genre;
import com.bd12024.BookStore.entities.Product;
import com.bd12024.BookStore.entities.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    ProductRepository productRepository;

    @GetMapping(value={"/index", "/"})
    public String showMainPage(Model model) {
        return "index";
    }


    @GetMapping(value={"/list-books"})
    public String showBooksList(Model model) {
        List<Book> mylist = new ArrayList<>();
        mylist.add(
                new Book(1, 2.0, 15, 1,"oi",1,"io", 1,100, null, null)
        );

        model.addAttribute("books", mylist);
        return "list-books";
    }




    @GetMapping(value={"/list-genres"})
    public String showGenresList(Model model) {
        GenreDAO dao;

        List<Genre> genreList;
        List<Integer> booksCount = new ArrayList<>();

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getGenreDAO();
            genreList = dao.all();

            for (Genre g : genreList)
            {
                booksCount.add(genreList.indexOf(g), dao.countBooksByGenre(g.getName()) );
            }
        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("genres",      genreList);
        model.addAttribute("booksCounts", booksCount);
        return "list-genres";
    }


    @GetMapping("/new-genre")
    public String showFormNewGenre(Genre genre){
        return "new-genre";
    }
    @PostMapping("/add-genre")
    public String addGenre(Genre genre, BindingResult result) {
        if (result.hasErrors()) {
            return "/new-genre";
        }

        GenreDAO dao;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getGenreDAO();
            dao.create(genre);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/new-genre";
        }

        return "redirect:/list-genres";
    }








    @GetMapping(value={"/list-themes"})
    public String showThemesList(Model model) {
        ThemeDAO dao;

        List<Theme>   themeList;
        List<Integer> themesCount = new ArrayList<>();

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getThemeDAO();
            themeList = dao.all();

            for (Theme t : themeList)
            {
                themesCount.add(themeList.indexOf(t), dao.countMagazinesByTheme(t.getName()) );
            }
        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("themes",      themeList);
        model.addAttribute("themesCount", themesCount);
        return "list-themes";
    }







}
