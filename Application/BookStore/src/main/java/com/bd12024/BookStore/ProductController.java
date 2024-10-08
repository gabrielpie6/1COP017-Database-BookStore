package com.bd12024.BookStore;


import com.bd12024.BookStore.dao.*;
import com.bd12024.BookStore.entities.*;
// import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {


    @GetMapping(value={"/index", "/"})
    public String showMainPage(Model model) {
        return "index";
    }

    @GetMapping(value={"/products-options"})
    public String showProductsOptions(Model model) {
        return "products-options";
    }

    @GetMapping(value={"/customers-options"})
    public String showCustomersOptions(Model model) {
        return "customers-options";
    }

    @GetMapping(value={"/reports"})
    public String showReports(Model model) {
        ReportDAO dao;
        List<ReportByProductsType> reportsByProductsList;
        List<ReportByGenre>        reportsByGenresList;
        List<ReportByTheme>        reportsByThemesList;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getReportDAO();
            reportsByProductsList   = dao.getReportByProductsType();
            reportsByGenresList     = dao.getReportByGenres();
            reportsByThemesList     = dao.getReportByThemes();

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("reportsProd",   reportsByProductsList);
        model.addAttribute("reportsGenres", reportsByGenresList);
        model.addAttribute("reportsThemes", reportsByThemesList);
        return "reports";
    }












    @GetMapping(value={"/list-books"})
    public String showBooksList(Model model) {
        BookDAO dao;

        List<Book> booksList;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getBookDAO();
            booksList = dao.all();

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("books",      booksList);
        return "list-books";
    }
    @GetMapping("/new-book")
    public String showFormNewBook(Model model){
        Book book = new Book();
        book.setPublisher(new Publisher("DUMMY", "DUMMY"));
        book.setGenre(new Genre("DUMMY", -1.0));

        model.addAttribute("book", book);
        return "new-book";
    }
    @PostMapping("/add-book")
    public String addBook(Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/new-book";
        }

        String publisher_name = book.getPublisher().getName();
        String genre_name     = book.getGenre().getName();


        PublisherDAO daoPub;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            daoPub = daoFactory.getPublisherDAO();
            book.setPublisher(  daoPub.read(publisher_name) );

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            book.setPublisher(new Publisher("NOT_FOUND", "NOT_FOUND") );
        }

        GenreDAO daoGen;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            daoGen = daoFactory.getGenreDAO();
            book.setGenre(  daoGen.read(genre_name)  );

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            book.setGenre(new Genre("NOT_FOUND", 0.0) );
        }




        BookDAO dao;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getBookDAO();
            dao.create(book);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/new-book";
        }

        return "redirect:/list-books";
    }
    @GetMapping("/remove-book/{id}")
    public String removeBook(@PathVariable("id") int id) {
        BookDAO dao;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getBookDAO();
            dao.delete(id);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/list-books";
        }
        return "redirect:/list-books";
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
    @GetMapping("/new-theme")
    public String showFormNewTheme(Theme theme){
        return "new-theme";
    }
    @PostMapping("/add-theme")
    public String addTheme(Theme theme, BindingResult result) {
        if (result.hasErrors()) {
            return "/new-theme";
        }

        ThemeDAO dao;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getThemeDAO();
            dao.create(theme);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/new-theme";
        }

        return "redirect:/list-themes";
    }










    @GetMapping(value={"/list-publishers"})
    public String showPublishersList(Model model) {
        PublisherDAO dao;

        List<Publisher> publisherList;
        List<Integer>   booksCount     = new ArrayList<>();
        List<Integer>   magazinesCount = new ArrayList<>();

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getPublisherDAO();
            publisherList = dao.all();

            for (Publisher p : publisherList)
            {
                booksCount.add(    publisherList.indexOf(p), dao.countBooksByPublisher(    p.getName()) );
                magazinesCount.add(publisherList.indexOf(p), dao.countMagazinesByPublisher(p.getName()) );
            }
        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("publishers",      publisherList);
        model.addAttribute("booksCounts",     booksCount);
        model.addAttribute("magazinesCounts", magazinesCount);
        return "list-publishers";
    }
    @GetMapping("/new-publisher")
    public String showFormNewPublisher(Publisher publisher){
        return "new-publisher";
    }
    @PostMapping("/add-publisher")
    public String addPublisher(Publisher publisher, BindingResult result) {
        if (result.hasErrors()) {
            return "/new-publisher";
        }

        PublisherDAO dao;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getPublisherDAO();
            dao.create(publisher);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/new-publisher";
        }

        return "redirect:/list-publishers";
    }








    @GetMapping(value={"/list-magazines"})
    public String showMagazinesList(Model model) {
        MagazineDAO dao;

        List<Magazine> magazinesList;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getMagazineDAO();
            magazinesList = dao.all();

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("magazines",      magazinesList);
        return "list-magazines";
    }
    
    @GetMapping("/new-magazine")
    public String showFormNewMagazine(Model model){
        Magazine magazine = new Magazine();
        magazine.setPublisher(new Publisher("DUMMY", "DUMMY"));
        magazine.setTheme(new Theme("DUMMY", -1.0));

        model.addAttribute("magazine", magazine);
        return "new-magazine";
    }

    @PostMapping("/add-magazine")
    public String addMagazine(Magazine magazine, BindingResult result) {
        if (result.hasErrors()) {
            return "/new-magazine";
        }

        String publisher_name = magazine.getPublisher().getName();
        String theme_name     = magazine.getTheme().getName();


        PublisherDAO daoPub;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            daoPub = daoFactory.getPublisherDAO();
            magazine.setPublisher(  daoPub.read(publisher_name) );

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            magazine.setPublisher(new Publisher("NOT_FOUND", "NOT_FOUND") );
        }

        ThemeDAO daoTheme;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            daoTheme = daoFactory.getThemeDAO();
            magazine.setTheme(  daoTheme.read(theme_name)  );

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            magazine.setTheme(new Theme("NOT_FOUND", 0.0) );
        }




        MagazineDAO dao;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getMagazineDAO();
            dao.create(magazine);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/new-magazine";
        }

        return "redirect:/list-magazines";
    }

    @GetMapping("/remove-magazine/{id}")
    public String removeMagazine(@PathVariable("id") int id) {
        MagazineDAO dao;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getMagazineDAO();
            dao.delete(id);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/list-magazines";
        }
        return "redirect:/list-magazines";
    }








    @GetMapping(value={"/list-pagemarks"})
    public String showPagemarksList(Model model) {
        PagemarkDAO dao;

        List<Pagemark> pagemarksList;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getPagemarkDAO();
            pagemarksList = dao.all();

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("pagemarks",      pagemarksList);
        return "list-pagemarks";
    }









    @GetMapping(value={"/list-customers"})
    public String showCustomersList(Model model) {
        CustomerDAO dao;

        List<Customer> customersList;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getCustomerDAO();
            customersList = dao.all();

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/index";
        }

        model.addAttribute("customers", customersList);
        return "list-customers";
    }

    @GetMapping("/new-customer")
    public String showFormNewCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new-customer";
    }

    @PostMapping("/add-customer")
    public String addCustomer(Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "/new-customer";
        }

        CustomerDAO dao;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getCustomerDAO();
            dao.create(customer);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "/new-customer";
        }

        return "redirect:/list-customers";
    }

    @GetMapping("/edit-customer/{cpf}")
    public String showFormEditCustomer(@PathVariable("cpf") String cpf, Model model) {
        CustomerDAO dao;
        Customer customer;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getCustomerDAO();
            customer = dao.read(cpf);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "redirect:/list-customers";
        }

        model.addAttribute("customer", customer);
        return "edit-customer";
    }

    @PostMapping("/update-customer")
    public String updateCustomer(Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-customer";
        }

        CustomerDAO dao;
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getCustomerDAO();
            dao.update(customer);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "edit-customer";
        }

        return "redirect:/list-customers";
    }

    @GetMapping("/remove-customer/{cpf}")
    public String removeCustomer(@PathVariable("cpf") String cpf) {
        CustomerDAO dao;

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            dao = daoFactory.getCustomerDAO();
            dao.delete(cpf);

        } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
            System.out.println(ex.getMessage());
            return "redirect:/list-customers";
        }
        return "redirect:/list-customers";
    }
}
