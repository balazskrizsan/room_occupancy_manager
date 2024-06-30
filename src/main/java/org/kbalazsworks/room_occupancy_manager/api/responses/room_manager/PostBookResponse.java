package org.kbalazsworks.room_occupancy_manager.api.responses.room_manager;

import org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails;

public record PostBookResponse(
    BookDetails bookedPremiumRooms,
    BookDetails bookedEconomyRooms
)
{
}
