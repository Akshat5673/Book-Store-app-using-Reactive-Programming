package com.nineleaps.Book.Store;

import com.nineleaps.Book.Store.models.Book;
import com.nineleaps.Book.Store.repositories.BookRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class RepoTest {

    private final BookRepo repo;

    @Autowired
    public RepoTest(BookRepo repo) {
        this.repo = repo;
    }

    @Test
    void findByNameTest(){
        Mono<Book> bookMono = repo.findByName("Intro to Java").log();
        StepVerifier.create(bookMono).expectNextCount(1).verifyComplete();
    }

    @Test
    void findByAuthorTest(){
        Flux<Book> bookFlux = repo.findByAuthor("James Gosling").log();
        StepVerifier.create(bookFlux).expectNextCount(2).verifyComplete();
    }

    @Test
    void findByPublisherTest(){
        Flux<Book> bookFlux = repo.findByPublisher("ABC publishing house").log();
        StepVerifier.create(bookFlux).expectNextCount(2).verifyComplete();
    }

    @Test
    void findByNameOrAuthor() {
         repo.getBooksByNameOrAuthor("Intro to Python","James Gosling").log()
                .as(StepVerifier::create)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void searchBookByTitle() {
        repo.searchBookByTitle("%Intro%").log()
                .as(StepVerifier::create)
                .expectNextCount(3)
                .verifyComplete();


    }
}
