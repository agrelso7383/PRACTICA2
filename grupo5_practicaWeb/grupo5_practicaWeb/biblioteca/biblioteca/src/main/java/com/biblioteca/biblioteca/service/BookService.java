package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Book;
import com.biblioteca.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book update(Long id, Book newBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            return bookRepository.save(book);
        }).orElseThrow();
    }

    public Book patch(Long id, String title, String author) {
        return bookRepository.findById(id).map(book -> {
            if (title != null) book.setTitle(title);
            if (author != null) book.setAuthor(author);
            return bookRepository.save(book);
        }).orElseThrow();
    }
}
