package dev.cmurillo.SmokeNotesBackend.Controller;

import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/cigars")
public class UserCigarController {

    private final UserCigarService userCigarService;

    public UserCigarController(UserCigarService userCigarService) {
        this.userCigarService = userCigarService;
    }

    @GetMapping("")
    List<Cigar> findAllUsersCigars() {
        return userCigarService.getAllUsersCigars();
    }

}
