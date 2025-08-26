package dev.cmurillo.SmokeNotesBackend.Cigar;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    public CigarJsonDataLoader(CigarRepository cigarRepository, ObjectMapper objectMapper) {
        this.cigarRepository = cigarRepository;
        this.objectMapper = new ObjectMapper();
    }

    /* Using the command line runner for a programmatic way to check if our "in-memory" h2 database has data in it
     * If no data is present, read the data in from the JSON file
     * If data is present, output that data exists
     */
    @Override
    public void run(String... args) throws Exception {
        if (cigarRepository.count() == 0) {
            try (InputStream is = TypeReference.class.getResourceAsStream("/data/cigars.json")) {
                Cigars allCigars = objectMapper.readValue(is, Cigars.class);
                log.info("Reading {} cigars from JSON data and saving to in-memory collection.", allCigars.cigars().size());
                cigarRepository.saveAll(allCigars.cigars());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Cigars from JSON data because the collection contains data.");
        }
    }
}
