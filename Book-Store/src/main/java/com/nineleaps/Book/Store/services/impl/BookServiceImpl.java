package com.nineleaps.Book.Store.services.impl;

import com.nineleaps.Book.Store.models.Book;
import com.nineleaps.Book.Store.repositories.BookRepo;
import com.nineleaps.Book.Store.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class BookServiceImpl implements BookService {


    private final BookRepo repo;

    @Autowired
    public BookServiceImpl(BookRepo repo) {
        this.repo = repo;
    }

    @Override
    public Mono<Book> create(Book book) {
        return repo.save(book);
    }

    @Override
    public Flux<Book> getAll() {
        return repo.findAll().map(book -> {
            book.setName(book.getName().toUpperCase());
            return book;
        }).log();
    }

    @Override
    public Mono<Book> get(Integer bookId) {
        return repo.findById(Mono.just(bookId));
    }

    @Override
    public Mono<Book> update(Book book, Integer bookId) {
        Mono<Book> oldBook = repo.findById(bookId);
        return oldBook.flatMap(b->{
            b.setName(book.getName());
            b.setDescription(book.getDescription());
            b.setAuthor(book.getAuthor());
            b.setPublisher(book.getPublisher());
            return repo.save(b);
        });
    }

    @Override
    public Mono<Void> delete(Integer bookId) {
        return repo.findById(bookId).flatMap(repo::delete);
    }

    @Override
    public Mono<Book> searchByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Flux<Book> searchBooks(String title) {
        return repo.searchBookByTitle(title);
    }
}
