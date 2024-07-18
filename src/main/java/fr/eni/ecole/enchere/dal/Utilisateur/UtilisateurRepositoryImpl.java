package fr.eni.ecole.enchere.dal.Utilisateur;

import fr.eni.ecole.enchere.bo.Role;
import fr.eni.ecole.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

        String sql = "select * from utilisateurs";

        List<Utilisateur> list = jdbcTemplate.query(sql, new UtilisateurRowMapper());

        return list;
    }

    @Override
    public Utilisateur findById(int id) {

        String sql = "select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, active from utilisateurs where no_utilisateur = :id;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(sql, map, new UtilisateurRowMapper());

        return utilisateur;
    }

    @Override
    public void save(Utilisateur utilisateur) {

        GeneratedKeyHolder key = new GeneratedKeyHolder();

        String sql = "insert into utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) " +
                "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur);";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("telephone", utilisateur.getTelephone());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("code_postal", utilisateur.getCode_postal());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("mot_de_passe", utilisateur.getMot_de_passe());
        map.addValue("credit", utilisateur.getCredit());
        map.addValue("administrateur", utilisateur.isAdministrateur());

        namedParameterJdbcTemplate.update(sql, map, key);

        if(key.getKey() != null) {
            utilisateur.setNo_utilisateur(key.getKey().intValue());
        }

    }

    @Override
    public void deleteById(int id) {

        String sql = "delete from utilisateurs where no_utilisateur = :id;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void update(Utilisateur utilisateur) {

        String sql = "update utilisateurs " +
                "set pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, code_postal = :code_postal, ville = :ville " +
                "where no_utilisateur = :id;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("pseudo", utilisateur.getPseudo());
        map.addValue("nom", utilisateur.getNom());
        map.addValue("prenom", utilisateur.getPrenom());
        map.addValue("email", utilisateur.getEmail());
        map.addValue("telephone", utilisateur.getTelephone());
        map.addValue("rue", utilisateur.getRue());
        map.addValue("code_postal", utilisateur.getCode_postal());
        map.addValue("ville", utilisateur.getVille());
        map.addValue("id", utilisateur.getNo_utilisateur());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        String sql = "select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, active from utilisateurs where email = :email;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);

        Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(sql, map, new UtilisateurRowMapper());

        return utilisateur;
    }

    @Override
    public void changeCredit(int id, int credit) {
        String sql = "update utilisateurs set credit = :credit where no_utilisateur = :id;";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("credit", credit);
        map.addValue("id", id);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        String sql = "select email, role, id from roles where email = :emailUtilisateur;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("emailUtilisateur", username);

        List<Role> list = namedParameterJdbcTemplate.query(sql, map, new RoleRowMapper());

        return list;
    }

    @Override
    public void desactivateById(int id) {

        String sql = "update utilisateurs set active = 0 where no_utilisateur = :id;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void activateById(int id) {

        String sql = "update utilisateurs set active = 1 where no_utilisateur = :id;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void saveNewPassword(int id, String password) {

        String sql = "update utilisateurs set mot_de_passe = :mot_de_passe where no_utilisateur = :id;";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("mot_de_passe", password);
        map.addValue("id", id);

        namedParameterJdbcTemplate.update(sql, map);
    }
}
