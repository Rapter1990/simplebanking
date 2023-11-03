package com.eteration.simplebanking.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an error response that can be sent as a JSON response to clients in case of an error.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    /**
     * A message providing details about the error.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * The HTTP status code associated with the error.
     */
    private HttpStatus status;

    /**
     * An integer representation of the HTTP status code.
     */
    private Integer statusCode;

    /**
     * A list of additional error details, if needed.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errorDetails;

    /**
     * The timestamp indicating when the error response was created.
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}
