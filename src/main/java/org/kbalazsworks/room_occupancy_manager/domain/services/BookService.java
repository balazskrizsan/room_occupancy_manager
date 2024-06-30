package org.kbalazsworks.room_occupancy_manager.domain.services;

import org.kbalazsworks.room_occupancy_manager.api.responses.room_manager.PostBookResponse;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails;
import org.kbalazsworks.room_occupancy_manager.domain.entities.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    public PostBookResponse book(Book book)
    {
        return new PostBookResponse(
            new BookDetails(1111, 22.22),
            new BookDetails(3333, 44.44)
        );
    }
}
