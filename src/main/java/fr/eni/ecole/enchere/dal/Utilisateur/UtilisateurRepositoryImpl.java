package fr.eni.ecole.enchere.dal.Utilisateur;

import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class UtilisateurRepositoryImpl implements UtilisateurRepository {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UtilisateurRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        super();
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }

    @Override
    public Utilisateur findById(int id) {
        return null;
    }

    @Override
    public void save(Utilisateur utilisateur) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void update(Utilisateur utilisateur) {

    }
}
