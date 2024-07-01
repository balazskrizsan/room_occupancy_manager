package org.kbalazsworks.room_occupancy_manager.tests.e2e.unit.domain.services;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kbalazsworks.room_occupancy_manager.AbstractTest;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails;
import org.kbalazsworks.room_occupancy_manager.domain.services.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorService_calculateBookDetailsTest extends AbstractTest
{
    @Autowired
    private CalculatorService calculatorService;

    @ParameterizedTest
    @MethodSource("provider")
    @SneakyThrows
    public void validDataFromProvider_returnsExpectedBookDetails(
        int roomsAvailable,
        List<Double> guestPrices,
        BookDetails expected
    )
    {
        // Arrange - in provider
        // Act
        BookDetails actual = calculatorService.calculateBookDetails(roomsAvailable, guestPrices);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provider()
    {
        return Stream.of(
            Arguments.of(
                1,
                List.of(1.0, 2.0, 3.3),
                new BookDetails(1, 1.0)
            ),
            Arguments.of(
                2,
                List.of(1.0, 2.0, 3.3),
                new BookDetails(2, 3.0)
            ),
            Arguments.of(
                3,
                List.of(1.0, 2.0, 3.3, 10.0),
                new BookDetails(3, 6.3)
            )
        );
    }
}
