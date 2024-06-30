package org.kbalazsworks.room_occupancy_manager.api.controllers.health;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("HealtControllerGet403ForbiddenController")
@RequestMapping("/health")
public class Get403ForbiddenController
{
    @GetMapping("403Forbidden")
    public ResponseEntity<String> action()
    {
        return new ResponseEntity<>("403Forbidden", HttpStatusCode.valueOf(403));
    }
}
