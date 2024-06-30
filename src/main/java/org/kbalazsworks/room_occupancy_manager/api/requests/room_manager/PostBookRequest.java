package org.kbalazsworks.room_occupancy_manager.api.requests.room_manager;

import java.util.List;

public record PostBookRequest(
    Integer availableEconomyRooms,
    Integer availablePremiumRooms,
    List<Double> customerPrices
)
{
}
