package org.kbalazsworks.room_occupancy_manager.tests.e2e.unit.domain.services;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kbalazsworks.room_occupancy_manager.AbstractTest;
import org.kbalazsworks.room_occupancy_manager.domain.services.CalculatorService;
import org.kbalazsworks.room_occupancy_manager.domain.value_objects.RoomTypePrices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorService_getRoomTypePricesTest extends AbstractTest
{
    @Autowired
    private CalculatorService calculatorService;

    @ParameterizedTest
    @MethodSource("provider")
    @SneakyThrows
    public void validDataFromProvider_returnsRoomTypePrices(
        int premiumRoomsAvailable,
        int economyRoomsAvailable,
        List<Double> guestPrices,
        RoomTypePrices expected
    )
    {
        // Arrange - in provider
        // Act
        RoomTypePrices actual = calculatorService.getRoomTypePrices(
            premiumRoomsAvailable,
            economyRoomsAvailable,
            guestPrices
        );

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provider()
    {
        return Stream.of(
            Arguments.of(
                1,
                2,
                List.of(1.0, 2.0, 3.3),
                new RoomTypePrices(List.of(3.3), List.of(2.0, 1.0))
            ),
            Arguments.of(
                1,
                1,
                List.of(99.0, 100.0, 101.0),
                new RoomTypePrices(List.of(101.0, 100.0), List.of(99.0))
            )
        );
    }
}
