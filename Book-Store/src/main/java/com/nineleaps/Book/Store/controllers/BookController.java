package com.nineleaps.Book.Store.controllers;

import com.nineleaps.Book.Store.models.Book;
import com.nineleaps.Book.Store.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/book")

public class BookController {


    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public Mono<Book> create(@RequestBody Book book){
        return service.create(book);
    }

    @GetMapping("/list")
    public Flux<Book> list(){
        return service.getAll();
    }

    @GetMapping("/retrieve/{bookId}")
    public Mono<Book> retrieve(@PathVariable Integer bookId){
        return service.get(bookId);
    }

    @PutMapping("/update/{bookId}")
    public Mono<Book> update(@RequestBody Book book, @PathVariable Integer bookId){
        return service.update(book,bookId);
    }

    @DeleteMapping("/delete/{bookId}")
    public Mono<Void> delete(@PathVariable Integer bookId){
        return service.delete(bookId);
    }

    @GetMapping("/search-by-name")
    public Mono<Book> searchByName(@RequestParam String name){
        return service.searchByName(name);
    }

    @GetMapping("search-by-title")
    public Flux<Book> searchByTitle(@RequestParam String title){
        return service.searchBooks(title);
    }

}
