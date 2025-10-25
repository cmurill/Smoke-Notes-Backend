package dev.cmurillo.SmokeNotesBackend.Controller;

import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigarDTO;
import dev.cmurillo.SmokeNotesBackend.Service.UserCigarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog/{userId}")
public class UserCigarController {

    private final UserCigarService userCigarService;

    public UserCigarController(UserCigarService userCigarService) {
        this.userCigarService = userCigarService;
    }

    @GetMapping("")
    UserCigarDTO findAllUsersCigars(@PathVariable String userId) {
        return userCigarService.getUserCatalog(userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/add/{cigarId}")
    void addToCatalog(@PathVariable String userId, @PathVariable String cigarId) {
        userCigarService.addUserCigar(userId, cigarId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/remove/{cigarId}")
    void removeFromCatalog(@PathVariable String userId, @PathVariable String cigarId) {
        userCigarService.removeUserCigar(userId, cigarId);
    }
}
