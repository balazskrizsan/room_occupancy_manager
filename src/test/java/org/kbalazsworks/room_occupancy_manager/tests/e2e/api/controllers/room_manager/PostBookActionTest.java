package org.kbalazsworks.room_occupancy_manager.tests.e2e.api.controllers.room_manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.kbalazsworks.room_occupancy_manager.AbstractE2eTest;
import org.kbalazsworks.room_occupancy_manager.api.responses.room_manager.PostBookResponse;
import org.kbalazsworks.room_occupancy_manager.api.value_objects.BookDetails;
import org.kbalazsworks.room_occupancy_manager.domain.entities.Book;
import org.kbalazsworks.room_occupancy_manager.domain.services.BookService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostBookActionTest extends AbstractE2eTest
{
    @MockBean
    private BookService bookService;

    @Test
    @SneakyThrows
    public void validRequest_callsService()
    {
        // Arrange
        String testedUri = "/room-manager/book";
        HashMap<String, Object> testedBody = new HashMap<>()
        {{
            put("availableEconomyRooms", 5);
            put("availablePremiumRooms", 3);
            put("customerPrices", List.of(1, 2, 3.3));
        }};

        Book expectedBookServiceCall = new Book(5, 3, List.of((double) 1, (double) 2, (double) 3.3));
        double expectedBookedPremiumRoomsBookedRooms = 1;
        double expectedBookedPremiumRoomsIncome = 2.2;
        double expectedBookedEconomyRoomsBookedRooms = 3;
        double expectedBookedEconomyRoomsIncome = 4.4;

        PostBookResponse mockedBookResponse = new PostBookResponse(
            new BookDetails(1, 2.2),
            new BookDetails(3, 4.4)
        );

        when(bookService.book(expectedBookServiceCall)).thenReturn(mockedBookResponse);

        // Act
        ResultActions result = getMockMvc()
            .perform(
                MockMvcRequestBuilders
                    .post(testedUri)
                    .content(new ObjectMapper().writeValueAsString(testedBody))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            );

        // Assert
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.bookedEconomyRooms.bookedRooms").value(expectedBookedEconomyRoomsBookedRooms))
            .andExpect(jsonPath("$.data.bookedEconomyRooms.income").value(expectedBookedEconomyRoomsIncome))
            .andExpect(jsonPath("$.data.bookedPremiumRooms.bookedRooms").value(expectedBookedPremiumRoomsBookedRooms))
            .andExpect(jsonPath("$.data.bookedPremiumRooms.income").value(expectedBookedPremiumRoomsIncome))
        ;

        verify(bookService).book(expectedBookServiceCall);
    }

    @Test
    @SneakyThrows
    public void invalidValues_responseWithError()
    {
        // Arrange
        String testedUri = "/room-manager/book";
        HashMap<String, Object> testedBody = new HashMap<>()
        {{
            put("availableEconomyRooms", -1);
            put("availablePremiumRooms", 3);
            put("customerPrices", List.of(1, 2, 3.3));
        }};

        Book expectedBookServiceCall = new Book(-1, 3, List.of((double) 1, (double) 2, (double) 3.3));
        String expectedErrorMessage = "Economy rooms must be positive number";
        boolean expectedSuccess = false;
        int expectedErrorCode = 1001;
        int expectedRequestId = 1;

        PostBookResponse mockedBookResponse = new PostBookResponse(
            new BookDetails(1, 2.2),
            new BookDetails(3, 4.4)
        );

        when(bookService.book(expectedBookServiceCall)).thenReturn(mockedBookResponse);

        // Act
        ResultActions result = getMockMvc()
            .perform(
                MockMvcRequestBuilders
                    .post(testedUri)
                    .content(new ObjectMapper().writeValueAsString(testedBody))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            );

        // Assert
        result
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.data").value(expectedErrorMessage))
            .andExpect(jsonPath("$.success").value(expectedSuccess))
            .andExpect(jsonPath("$.errorCode").value(expectedErrorCode))
            .andExpect(jsonPath("$.requestId").value(expectedRequestId))
        ;

        verify(bookService, never()).book(expectedBookServiceCall);
    }
}
