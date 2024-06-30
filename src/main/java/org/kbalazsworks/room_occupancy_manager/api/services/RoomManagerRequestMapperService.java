package org.kbalazsworks.room_occupancy_manager.api.services;

import org.kbalazsworks.room_occupancy_manager.api.requests.room_manager.PostBookRequest;
import org.kbalazsworks.room_occupancy_manager.domain.entities.Book;
import org.springframework.stereotype.Service;

@Service
public class RoomManagerRequestMapperService
{
    public Book mapToEntity(PostBookRequest request)
    {
        return new Book(
            request.availableEconomyRooms(),
            request.availablePremiumRooms(),
            request.customerPrices()
        );
    }
}
