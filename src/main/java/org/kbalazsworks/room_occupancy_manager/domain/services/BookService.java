package org.kbalazsworks.room_occupancy_manager.domain.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.kbalazsworks.room_occupancy_manager.api.responses.room_manager.PostBookResponse;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails;
import org.kbalazsworks.room_occupancy_manager.domain.entities.Book;
import org.kbalazsworks.room_occupancy_manager.domain.value_objects.RoomTypePrices;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService
{
    private final CalculatorService calculatorService;

    public static final Double premiumGuestMinPrice = 100.0;

    public PostBookResponse book(@NonNull Book book)
    {
        RoomTypePrices roomTypePrices = calculatorService.getRoomTypePrices(
            book.availablePremiumRooms(),
            book.availableEconomyRooms(),
            book.customerPrices()
        );

        BookDetails premiumBookDetails = calculatorService.calculateBookDetails(
            book.availablePremiumRooms(),
            roomTypePrices.premiumPrices()
        );
        BookDetails economyBookDetails = calculatorService.calculateBookDetails(
            book.availableEconomyRooms(),
            roomTypePrices.economyPrices()
        );

        return new PostBookResponse(premiumBookDetails, economyBookDetails);
    }
}
