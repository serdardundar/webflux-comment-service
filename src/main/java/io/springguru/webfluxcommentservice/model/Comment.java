package io.springguru.webfluxcommentservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * This is a simulated social media comment class
 *
 * @author serdardundar
 * @since 11/12/2018
 */
@Data
@Builder(toBuilder = true)
public class Comment {

    private String message;
    private Instant createdTime;
    private String subscriber;
    private boolean liked;
}
