package com.mcgb.varbifikrimbackend.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class GenericException {
    private HttpStatus status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HashMap validationErrors;
}
