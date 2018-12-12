package io.springguru.webfluxcommentservice.service;

import io.springguru.webfluxcommentservice.model.Comment;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.function.BiFunction;

/**
 * This class is used to generate random social media comments
 *
 * @author serdardundar
 * @since 11/12/2018
 */
@Service
public class CommentGeneratorServiceImpl implements CommentGeneratorService{

    @Override
    public Flux<Comment> fetchCommentStream(Duration period) {

        return Flux.generate(() -> 0,
                (BiFunction<Integer, SynchronousSink<Comment>, Integer>) (index, sink) -> {
                    Comment generatedComment = generateRandomComment();
                    sink.next(generatedComment);
                    return ++index;
                })
                .zipWith(Flux.interval(period))
                .map(Tuple2::getT1)
                .log("io.springguru.service.CommentGenerator");
    }

    private Comment generateRandomComment() {

        Random ran = new Random();
        int randomUserId = ran.nextInt(9999999) + 5;
        int randomCommentId = ran.nextInt(9999999) + 5;

        return Comment.builder()
                .userId(randomUserId + "")
                .createdTime(LocalDateTime.now())
                .id(randomCommentId + "")
                .like(randomCommentId % 2 == 0)
                .message(RandomStringUtils.random(100, true, true))
                .build();

    }
}
