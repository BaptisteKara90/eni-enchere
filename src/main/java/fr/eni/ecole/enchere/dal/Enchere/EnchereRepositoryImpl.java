package fr.eni.ecole.enchere.dal.Enchere;

import fr.eni.ecole.enchere.bo.Enchere;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereRepositoryImpl implements EnchereRepository {

    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EnchereRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Enchere> findAll() {

        String sql = "select * from encheres";
        List<Enchere> list = jdbcTemplate.query(sql, new EnchereRowMapper());
        return list;
    }

    @Override
    public Enchere findById(int id) {
        String sql = "select * from encheres where no_article = :id";
        Enchere enchere = jdbcTemplate.queryForObject(sql, new EnchereRowMapper(), id);
        return enchere;
    }

    @Override
    public List<Enchere> findByIdUtilisateur(int idUtilisateur) {
        String sql = "select * from encheres where no_utilisateur = :idUtilisateur";
        List<Enchere> list = jdbcTemplate.query(sql, new EnchereRowMapper(), idUtilisateur);
        return list;
    }


    @Override
    public void save(Enchere enchere) {
        String sql = "insert into encheres (no_utilisateur, no_article, date_enchere, montant_enchere) values (:no_utilisateur, :no_article, :date_enchere, :montant_encher)";
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Enchere enchere) {

    }


}
