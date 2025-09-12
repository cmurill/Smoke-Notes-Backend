package dev.cmurillo.SmokeNotesBackend.Cigar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientCigarRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientCigarRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientCigarRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Cigar> findAll() {
        return jdbcClient.sql("select * from cigar")
                .query(Cigar.class)
                .list();
    }

    public Optional<Cigar> findByCigarId(String id) {
        return jdbcClient.sql("select * from cigar where cigar_id = ?")
                .param(id)
                .query(Cigar.class)
                .optional();
    }

    public void createCigar(Cigar cigar) {
        var updated = jdbcClient.sql("insert into cigar(cigar_id,cigar_name,factory_name,wrapper_type,wrapper_country,binder_country,filler_country,user_rating) values(?,?,?,?,?,?,?,?)")
                .params(List.of(cigar.cigarId(),cigar.cigarName(),cigar.factoryName(),cigar.wrapperType().toString(),cigar.wrapperCountry().toString(),cigar.binderCountry().toString(),cigar.fillerCountry().toString(),cigar.userRating()))
                .update();

        Assert.state(updated == 1, "Failed to create the cigar: " + cigar.cigarName());
    }

    public void updateCigar(String id, Cigar updatedCigar) {
        var updated = jdbcClient.sql("update cigar set cigar_id = ?,cigar_name = ?,factory_name = ?,wrapper_type = ?,wrapper_country = ?,binder_country = ?,filler_country = ?, user_rating = ? where cigar_id = ?")
                .params(List.of(updatedCigar.cigarId(),updatedCigar.cigarName(),updatedCigar.factoryName(),updatedCigar.wrapperType().toString(),updatedCigar.wrapperCountry().toString(),updatedCigar.binderCountry().toString(),updatedCigar.fillerCountry().toString(), updatedCigar.userRating(),id))
                .update();

        //Assert.state(updated == 1, "Failed to update the cigar: " + updatedCigar.cigarName());
    }

    public  void deleteCigarById(String id) {
        var updated = jdbcClient.sql("delete from cigar where cigar_id = ?")
                .param(id)
                .update();

        Assert.state(updated == 1, "Failed to delete the cigar with id: " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from cigar").query().listOfRows().size();
    }

    public void saveAll(List<Cigar> cigars) {
        cigars.stream().forEach(this::createCigar);
    }

}