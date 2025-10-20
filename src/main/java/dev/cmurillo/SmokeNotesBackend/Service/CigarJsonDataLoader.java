package dev.cmurillo.SmokeNotesBackend.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cmurillo.SmokeNotesBackend.Model.Cigars.Cigars;
import dev.cmurillo.SmokeNotesBackend.Repository.CigarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class CigarJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CigarJsonDataLoader.class);
    private final CigarRepository cigarRepository;
    private final ObjectMapper objMapper;

    public CigarJsonDataLoader(CigarRepository cigarRepository) {
        this.cigarRepository = cigarRepository;
        this.objMapper = new ObjectMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        if (cigarRepository.count() == 0) {
            try (InputStream is = getClass().getClassLoader().getResourceAsStream("data/cigars.json")) {
                Cigars allCigars = objMapper.readValue(is, Cigars.class);
                log.info("Reading {} cigars from JSON data and saving to postgreSQL database running in container.", allCigars.getCigars().size());
                cigarRepository.saveAll(allCigars.getCigars());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Cigars from JSON data because the collection contains the data already.");
        }
    }
}
