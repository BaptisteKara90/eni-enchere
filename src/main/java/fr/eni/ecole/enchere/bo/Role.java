package fr.eni.ecole.enchere.bo;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private String email;
    private String role;
    private int id;

    public Role(String email, String role, int id) {
        this.email = email;
        this.role = role;
        this.id = id;
    }

    public Role() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}

