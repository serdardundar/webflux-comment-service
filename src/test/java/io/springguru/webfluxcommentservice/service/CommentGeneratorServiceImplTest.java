package io.springguru.webfluxcommentservice.service;

import io.springguru.webfluxcommentservice.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * @author serdardundar
 * @since 11/12/2018
 */
@DisplayName("Comment Generator Test")
@SpringBootTest
class CommentGeneratorServiceImplTest {

    @Autowired
    private CommentGeneratorService commentGeneratorService;

    @Test
    @DisplayName("Fetch Comment Stream Test")
    void fetchCommentStream() {
        Flux<Comment> commentFlux = commentGeneratorService.fetchCommentStream(Duration.ofMillis(1000L));

        commentFlux.take(1000)
                .subscribe(System.out::println);
    }

    @Test
    @DisplayName("Fetch Comment Stream and Count Down")
    void fetchCommentStreamAndCountDown() throws InterruptedException {

        Flux<Comment> commentFlux = commentGeneratorService.fetchCommentStream(Duration.ofMillis(1000L));
        Consumer<Comment> println = System.out::println;
        Consumer<Throwable> errorHandler = e -> System.out.println("Some error occured");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable allDone = countDownLatch::countDown;

        commentFlux.take(30)
                .subscribe(println, errorHandler, allDone);

        countDownLatch.await();
    }
}
