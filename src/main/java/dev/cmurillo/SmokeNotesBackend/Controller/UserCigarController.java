package dev.cmurillo.SmokeNotesBackend.Controller;

import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigar;
import dev.cmurillo.SmokeNotesBackend.Service.UserCigarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog/{userId}")
public class UserCigarController {

    private final UserCigarService userCigarService;

    public UserCigarController(UserCigarService userCigarService) {
        this.userCigarService = userCigarService;
    }

    @GetMapping("")
    List<UserCigar> findAllUsersCigars(@PathVariable String userId) {
        return userCigarService.getUserCatalog(userId);
    }

    @PostMapping("/add/{cigarId}")
    void addToCatalog(@PathVariable String userId, @PathVariable String cigarId) {
        userCigarService.addUserCigar(userId, cigarId);
    }

}
