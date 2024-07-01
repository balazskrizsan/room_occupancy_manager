package org.kbalazsworks.room_occupancy_manager.domain.value_objects;

import java.util.List;

public record RoomTypePrices(List<Double> premiumPrices, List<Double> economyPrices)
{
}
