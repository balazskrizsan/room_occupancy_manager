package org.kbalazsworks.room_occupancy_manager.tests.e2e.integration.domain.services;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kbalazsworks.room_occupancy_manager.AbstractTest;
import org.kbalazsworks.room_occupancy_manager.api.responses.room_manager.PostBookResponse;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails;
import org.kbalazsworks.room_occupancy_manager.domain.entities.Book;
import org.kbalazsworks.room_occupancy_manager.domain.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BookService_bookTest extends AbstractTest
{
    @Autowired
    private BookService bookService;

    private static final List<Double> mockGuestValues = List
        .of(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0);

    @ParameterizedTest
    @MethodSource("provider")
    @SneakyThrows
    public void asd(
        int premiumRooms,
        int economyRooms,
        List<Double> guestValues,
        PostBookResponse expected
    )
    {
        // Arrange - Act
        PostBookResponse actual = bookService.book(new Book(premiumRooms, economyRooms, guestValues));

        // Assert
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provider()
    {
        return Stream.of(
            Arguments.of(
                3,
                3,
                mockGuestValues,
                new PostBookResponse(
                    new BookDetails(3, 738),
                    new BookDetails(3, 167.99)
                )
            ),
            Arguments.of(
                7,
                5,
                mockGuestValues,
                new PostBookResponse(
                    new BookDetails(6, 1054),
                    new BookDetails(4, 189.99)
                )
            ),
            Arguments.of(
                2,
                7,
                mockGuestValues,
                new PostBookResponse(
                    new BookDetails(2, 583),
                    new BookDetails(4, 189.99)
                )
            ),
            Arguments.of(
                7,
                1,
                mockGuestValues,
                new PostBookResponse(
                    new BookDetails(7, 1153.99), // In challenge, it was 1153.0
                    new BookDetails(1, 45)       // and this 45.99
                )
            )
        );
    }
}
