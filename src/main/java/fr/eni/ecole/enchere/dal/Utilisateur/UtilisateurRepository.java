package fr.eni.ecole.enchere.dal.Utilisateur;


import fr.eni.ecole.enchere.bo.Role;
import fr.eni.ecole.enchere.bo.Utilisateur;
import fr.eni.ecole.enchere.dal.Dao;

import java.util.List;

public interface UtilisateurRepository extends Dao<Utilisateur> {

    Utilisateur findByEmail(String email);
    void changeCredit(int id, int credit);
    List<Role> findRolesByUsername(String username);
}
