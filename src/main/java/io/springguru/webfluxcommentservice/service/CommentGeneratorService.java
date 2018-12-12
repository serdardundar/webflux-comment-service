package io.springguru.webfluxcommentservice.service;

import io.springguru.webfluxcommentservice.model.Comment;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author serdardundar
 * @since 11/12/2018
 */
public interface CommentGeneratorService {

    Flux<Comment> fetchCommentStream(Duration period);
}
