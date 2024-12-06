package com.nineleaps.Book.Store.repositories;

import com.nineleaps.Book.Store.models.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepo extends ReactiveCrudRepository<Book,Integer> {

     Mono<Book> findByName(String name);

     Flux<Book> findByAuthor(String author);

     Flux<Book> findByPublisher(String publisher);

     @Query("select * from Book_Details where book_name= :bookName or author= :author")
     Flux<Book> getBooksByNameOrAuthor(@Param("bookName") String name, String author);

     @Query("SELECT * FROM Book_Details WHERE book_name LIKE CONCAT('%', :title, '%')")
     Flux<Book> searchBookByTitle(String title);

}
