package fr.eni.ecole.enchere.dal.Utilisateur;

import fr.eni.ecole.enchere.bo.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

        Role role = new Role();

        role.setId(rs.getInt("id"));
        role.setEmail(rs.getString("email"));
        role.setRole(rs.getString("role"));

        return role;
    }
}
