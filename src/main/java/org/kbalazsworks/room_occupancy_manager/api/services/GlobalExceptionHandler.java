package org.kbalazsworks.room_occupancy_manager.api.services;

import org.kbalazsworks.room_occupancy_manager.api.builders.ResponseEntityBuilder;
import org.kbalazsworks.room_occupancy_manager.api.exceptions.ApiException;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.ResponseData;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ResponseData<String>> simpleErrorHandler(Exception ex) throws ApiException
    {
        return new ResponseEntityBuilder<String>()
            .data(ex.getMessage())
            .statusCode(HttpStatus.BAD_REQUEST)
            .errorCode(1001)
            .build();
    }
}
