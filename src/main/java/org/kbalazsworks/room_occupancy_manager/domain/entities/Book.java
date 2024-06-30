package org.kbalazsworks.room_occupancy_manager.domain.entities;

import java.util.List;

public record Book(
    Integer availablePremiumRooms,
    Integer availableEconomyRooms,
    List<Double> customerPrices
)
{
}
