package com.springbootlearning.learningspringboot.springmvcthymleaf.controller;

import com.springbootlearning.learningspringboot.springmvcthymleaf.model.Book;
import com.springbootlearning.learningspringboot.springmvcthymleaf.service.BookService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public String getbookPage(
            @RequestParam(required = false) String login,
            Model model,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        if(login != null && !login.isEmpty()){
            session.setAttribute("userlogin", login);
        }

        String userLogin = (String) session.getAttribute("userlogin");
        if(userLogin == null || userLogin.isEmpty()){
            return "redirect:/";
        }

        model.addAttribute("userLogin", userLogin);
        model.addAttribute("books", bookService.getAllBooks(userLogin));
        return "book_page";
    }


    @GetMapping("/create")
    public String getCreateBookPage(Model model){
        model.addAttribute("newbook", new Book());
        return "create_book";
    }

    @PostMapping("/create")
    public String postBookPage(@ModelAttribute Book newbook){
        bookService.addBook(newbook);
        return "redirect:/books";
    }

    @GetMapping("/edit/{title}")
    public String getEditBookPage(Model model, @PathVariable String title){
        Book booktile = bookService.findByTitleAndDelete(title);
        model.addAttribute("bookToEdit", booktile);
        return "edit_book";
    }

    @PostMapping("/edit")
    public String postEditBookPage(@ModelAttribute Book book){
        bookService.edit(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{title}")
    public String delete(@PathVariable String title) {
        bookService.delete(title);
        return "redirect:/books";
    }
}
