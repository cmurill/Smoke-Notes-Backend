package dev.cmurillo.SmokeNotesBackend.Service;

import dev.cmurillo.SmokeNotesBackend.Exceptions.UserCigarNotFoundException;
import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigar;
import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigarDTO;
import dev.cmurillo.SmokeNotesBackend.Model.UserCigars.UserCigarId;
import dev.cmurillo.SmokeNotesBackend.Model.Users.User;
import dev.cmurillo.SmokeNotesBackend.Repository.UserCigarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCigarService {

    private final UserCigarRepository userCigarRepository;
    private final UserService userService;
    private final CigarService cigarService;
    private static final Logger log = LoggerFactory.getLogger(UserCigarService.class);

    public UserCigarService(UserCigarRepository userCigarRepository, UserService userService, CigarService cigarService) {
        this.userCigarRepository = userCigarRepository;
        this.userService = userService;
        this.cigarService = cigarService;
    }

    public UserCigarDTO getUserCatalog(String userId) {
        User currentUser = userService.getUserById(userId);
        log.info(getClass() + ": Retrieving user's cigar catalog with user id: " + userId);
        List<UserCigar> userCigars = userCigarRepository.findByUser(currentUser);
        List<UserCigarDTO.UserCigarItem> cigarItems = userCigars.stream()
                .map(uc -> new UserCigarDTO.UserCigarItem(uc.getCigar(), uc.getDateAdded()))
                .toList();
        log.info(getClass() + ": Successfully retrieved the user's cigar catalog with user id: " + userId);
        return new UserCigarDTO(userId, cigarItems);
    }

    public void addUserCigar(String userId, String cigarId) {
        User user = userService.getUserById(userId);
        Cigar cigar = cigarService.getCigarById(cigarId);
        UserCigar userCigar = new UserCigar(user, cigar);
        userCigarRepository.save(userCigar);
        log.info(getClass() + ": Added the cigar " + cigar.getCigarName() + " to user " + user.getFullName() + "'s cigar catalog");
    }

    public void removeUserCigar(String userId, String cigarId) {
        UserCigarId ucid = new UserCigarId(userId, cigarId);
        UserCigar uc = checkUserCigarById(ucid);
        User user = userService.getUserById(userId);
        Cigar cigar = cigarService.getCigarById(cigarId);
        userCigarRepository.delete(uc);
        log.info(getClass() + ": Removed the cigar " +  cigar.getCigarName() + " from user " + user.getFullName() + "'s cigar catalog");
    }

    private UserCigar checkUserCigarById(UserCigarId id) {
        Optional<UserCigar> uc =  userCigarRepository.findById(id);
        if (uc.isEmpty()) {
            log.warn(getClass() + ": Error finding the UserCigar with the UCID " + id);
            throw new UserCigarNotFoundException("A user with id " + id.getUserId() + ", does not contain a cigar with id " + id.getCigarId());
        } else {
            log.info(getClass() + ": Cigar found in user's catalog.");
            return uc.get();
        }
    }
}