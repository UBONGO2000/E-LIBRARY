package com.springbootlearning.learningspringboot.springmvcthymleaf.service;

import com.springbootlearning.learningspringboot.springmvcthymleaf.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private static List<Book> books;

    static {
        books = new ArrayList<>();
        books.add(new Book("Spring Boot in action", 2024));
        books.add(new Book("The kill a Mockingbird", 1960));
        books.add(new Book("The Catcher in the Rye", 1951));
    }


    public List<Book> getAllBooks(String login) {

        if (login != null || !login.isEmpty()) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getYear() > 1951)
                .collect(Collectors.toList());
    }


    public void addBook(Book newbook) {
        if (newbook != null) books.add(newbook);
    }

    public Book findByTitleAndDelete(String title) {
        Book bookModel = books.stream()
                .filter(it -> it.getTitle().equals(title))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        books.remove(bookModel);
        return bookModel;
    }

    public void edit(Book book) {
        books.add(book);
    }

    public void delete(String title) {
        findByTitleAndDelete(title);
    }
}