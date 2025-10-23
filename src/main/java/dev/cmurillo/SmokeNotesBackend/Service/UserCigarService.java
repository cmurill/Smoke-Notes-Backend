package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigar;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Repository.UserCigarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCigarService {

    private final UserCigarRepository userCigarRepository;
    private final UserService userService;
    private final CigarService cigarService;

    public UserCigarService(UserCigarRepository userCigarRepository, UserService userService, CigarService cigarService) {
        this.userCigarRepository = userCigarRepository;
        this.userService = userService;
        this.cigarService = cigarService;
    }

    public List<UserCigar> getUserCatalog(String userId) {
        User user = userService.getUserById(userId);
        return userCigarRepository.findByUser(user);
    }

    public void addUserCigar(String userId, String cigarId) {
        User user = userService.getUserById(userId);
        Cigar cigar = cigarService.getCigarById(cigarId);
        UserCigar userCigar = new UserCigar(user, cigar);
        userCigarRepository.save(userCigar);
    }

}
