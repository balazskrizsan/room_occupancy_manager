package org.kbalazsworks.room_occupancy_manager.api.requests.room_manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.util.List;

public record PostBookRequest(
    @JsonProperty("availableEconomyRooms")
    @Positive(message = "Economy rooms must be positive number")
    Integer availableEconomyRooms,

    @JsonProperty("availablePremiumRooms")
    @Positive(message = "Premium rooms must be positive number")
    Integer availablePremiumRooms,

    @JsonProperty("customerPrices")
    @Size(min = 1, message = "Customer prices must have at least 1 item")
    List<Double> customerPrices
)
{
}
