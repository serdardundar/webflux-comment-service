package io.springguru.webfluxcommentservice.web;

import io.springguru.webfluxcommentservice.model.Comment;
import io.springguru.webfluxcommentservice.service.CommentGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author serdardundar
 * @since 11/12/2018
 */
@Component
@RequiredArgsConstructor
public class CommentController {

    private final CommentGeneratorService commentGeneratorService;

    public Mono<ServerResponse> fetchComments(ServerRequest request) {
        int size = Integer.parseInt(request.queryParam("size").orElse("10"));

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.commentGeneratorService.fetchCommentStream(Duration.ofMillis(100))
                        .take(size), Comment.class);
    }

    public Mono<ServerResponse> streamComments(ServerRequest request){
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(this.commentGeneratorService.fetchCommentStream(Duration.ofMillis(1000L)), Comment.class);
    }
}
