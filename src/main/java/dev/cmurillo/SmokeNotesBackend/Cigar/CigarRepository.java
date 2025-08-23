package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CigarRepository {

    private static final Logger log = LoggerFactory.getLogger(CigarRepository.class);
    private final JdbcClient jdbcClient;

    public CigarRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Cigar> findAll() {
        return jdbcClient.sql("select * from cigar")
                .query(Cigar.class)
                .list();
    }


}
