package org.kbalazsworks.room_occupancy_manager.tests.e2e.api.controllers.health;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.kbalazsworks.room_occupancy_manager.AbstractE2eTest;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class Http200OkTest extends AbstractE2eTest
{
    @Test
    @SneakyThrows
    public void availableEndpoint_returns200ok()
    {
        // Arrange
        String testedUrl = "/health/200ok";

        ResultMatcher expectedStatus = status().isOk();
        String expectedData = "OK";

        // Act - Assert
        getMockMvc()
            .perform(MockMvcRequestBuilders.get(testedUrl))
            .andExpect(expectedStatus)
            .andExpect(content().string(expectedData));
    }
}
