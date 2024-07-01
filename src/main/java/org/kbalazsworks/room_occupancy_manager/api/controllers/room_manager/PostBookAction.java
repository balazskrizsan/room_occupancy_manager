package org.kbalazsworks.room_occupancy_manager.api.controllers.room_manager;

import lombok.RequiredArgsConstructor;
import org.kbalazsworks.kbalazsworks_common.validators.JavaxValidatorService;
import org.kbalazsworks.room_occupancy_manager.api.builders.ResponseEntityBuilder;
import org.kbalazsworks.room_occupancy_manager.api.exceptions.ApiException;
import org.kbalazsworks.room_occupancy_manager.api.requests.room_manager.PostBookRequest;
import org.kbalazsworks.room_occupancy_manager.api.responses.room_manager.PostBookResponse;
import org.kbalazsworks.room_occupancy_manager.api.services.RoomManagerRequestMapperService;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.ResponseData;
import org.kbalazsworks.room_occupancy_manager.domain.entities.Book;
import org.kbalazsworks.room_occupancy_manager.domain.services.BookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("RoomManagerPostBookAction")
@RequestMapping("/room-manager/book")
@RequiredArgsConstructor
public class PostBookAction
{
    private final RoomManagerRequestMapperService roomManagerRequestMapperService;
    private final BookService bookService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseData<PostBookResponse>> action(@RequestBody PostBookRequest request)
        throws ApiException
    {
        new JavaxValidatorService<PostBookRequest>().validate(request);

        Book book = roomManagerRequestMapperService.mapToEntity(request);

        return new ResponseEntityBuilder<PostBookResponse>()
            .data(bookService.book(book))
            .build();
    }
}
