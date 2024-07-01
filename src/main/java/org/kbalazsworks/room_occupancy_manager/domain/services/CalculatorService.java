package org.kbalazsworks.room_occupancy_manager.domain.services;

import lombok.NonNull;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails;
import org.kbalazsworks.room_occupancy_manager.domain.enums.RoomTypeEnum;
import org.kbalazsworks.room_occupancy_manager.domain.value_objects.RoomTypePrices;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.kbalazsworks.room_occupancy_manager.domain.enums.RoomTypeEnum.ECONOMY;
import static org.kbalazsworks.room_occupancy_manager.domain.enums.RoomTypeEnum.PREMIUM;

@Service
public class CalculatorService
{
    public RoomTypePrices getRoomTypePrices(
        int premiumRoomsAvailable,
        int economyRoomsAvailable,
        @NonNull List<Double> guestPrices
    )
    {
        List<Double> premiumPrices = calculatePrices(guestPrices, PREMIUM);
        List<Double> economyPrices = calculatePrices(guestPrices, ECONOMY);

        int premiumForEconomy = calculatePremiumForEconomy(
            economyPrices.size(),
            economyRoomsAvailable,
            premiumRoomsAvailable,
            premiumPrices.size()
        );

        return moveEconomyGuestsToPremium(premiumForEconomy, premiumPrices, economyPrices);
    }

    private int calculatePremiumForEconomy(
        int economyGuests,
        int economyRoomsAvailable,
        int premiumRoomsAvailable,
        int premiumGuests
    )
    {
        int economyExtraRooms = Stream.of(0, economyGuests - economyRoomsAvailable)
            .reduce(Integer::max)
            .orElse(0);
        int remainingPremiumRooms = Stream.of(0, premiumRoomsAvailable - premiumGuests)
            .reduce(Integer::max)
            .orElse(0);

        return Math.min(economyExtraRooms, remainingPremiumRooms);
    }

    private RoomTypePrices moveEconomyGuestsToPremium(
        int premiumForEconomy,
        @NonNull List<Double> premiumPrices,
        @NonNull List<Double> economyPrices
    )
    {
        List<Double> modifierList = economyPrices.stream().limit(premiumForEconomy).toList();
        premiumPrices.addAll(modifierList);
        economyPrices.removeAll(modifierList);

        return new RoomTypePrices(premiumPrices, economyPrices);
    }

    private List<Double> calculatePrices(@NonNull List<Double> guestPrices, @NonNull RoomTypeEnum roomTypeEnum)
    {
        return guestPrices.stream()
            .filter(guestPrice -> PREMIUM.equals(roomTypeEnum) == guestPrice.compareTo(BookService.premiumGuestMinPrice) >= 0)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    }

    public BookDetails calculateBookDetails(int roomsAvailable, @NonNull List<Double> guestPrices)
    {
        List<Double> guestsWithRooms = guestPrices.stream().limit(roomsAvailable).toList();

        return new BookDetails(
            guestsWithRooms.size(),
            guestsWithRooms.stream().mapToDouble(Double::doubleValue).sum()
        );
    }
}
