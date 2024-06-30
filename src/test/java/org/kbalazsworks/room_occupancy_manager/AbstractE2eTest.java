package org.kbalazsworks.room_occupancy_manager;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractE2eTest
{
    @Autowired
    @Getter
    private MockMvc mockMvc;
}
