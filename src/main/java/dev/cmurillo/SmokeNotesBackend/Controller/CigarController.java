package dev.cmurillo.SmokeNotesBackend.Controller;

import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigar;
import dev.cmurillo.SmokeNotesBackend.Service.CigarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cigars")
public class CigarController {

    private final CigarService cigarService;

    public CigarController(CigarService cigarService) {
        this.cigarService = cigarService;
    }

    @GetMapping("")
    List<Cigar> findAll() {
        return cigarService.getAllCigars();
    }

    @GetMapping("/{id}")
    Cigar findById(@PathVariable String id) {
        return cigarService.getCigarById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Cigar cigar) {
        cigarService.createCigar(cigar);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Cigar updatedCigar, @PathVariable String id) {
        cigarService.updateCigar(updatedCigar, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        cigarService.deleteCigar(id);
    }
}
