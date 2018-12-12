package io.springguru.webfluxcommentservice.web;

import io.springguru.webfluxcommentservice.model.Comment;
import io.springguru.webfluxcommentservice.service.CommentGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author serdardundar
 * @since 11/12/2018
 */
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentGeneratorService commentGeneratorService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Comment> getCommentStream() {
        return commentGeneratorService.fetchCommentStream(Duration.ofMillis(1000L));
    }
}
