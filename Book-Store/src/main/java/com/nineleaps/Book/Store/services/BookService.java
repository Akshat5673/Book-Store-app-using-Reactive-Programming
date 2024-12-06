package com.nineleaps.Book.Store.services;

import com.nineleaps.Book.Store.models.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

    public Mono<Book> create(Book book);

    public Flux<Book> getAll();

    public Mono<Book> get(Integer bookId);

    public Mono<Book> update(Book book,Integer bookId);

    public Mono<Void> delete(Integer bookId);

    public Mono<Book> searchByName(String name);

    public Flux<Book> searchBooks(String title);

}
