package org.kbalazsworks.room_occupancy_manager.api.controllers.health;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("HealtControllerGet200OkController")
@RequestMapping("/health")
public class Get200OkController
{
    @GetMapping("200ok")
    public ResponseEntity<String> action()
    {
        return new ResponseEntity<>("OK", HttpStatusCode.valueOf(200));
    }
}
