package fr.eni.ecole.enchere.securite;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery("select email, mot_de_passe, 1 from utilisateurs where email = ?");
        manager.setAuthoritiesByUsernameQuery("select email, role from roles where email = ?");

        return manager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            //On donne accès à la reque^te de type Get /security
            auth.requestMatchers(HttpMethod.GET, "/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/css/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/images/*").permitAll();
            auth.anyRequest().denyAll();
        });

        //Gestion automatique du login
        //http.formLogin(Customizer.withDefaults());

        //Gestion du login avec un fichier login.html
        http.formLogin(form -> {
            form.loginPage("/login").permitAll().defaultSuccessUrl("/");
        });

        http.logout(logout -> {
            //supprime la session côté serveur
            logout.invalidateHttpSession(true)
                    .clearAuthentication(true)
                    //supprime le cookie de session
                    .deleteCookies("JSESSIONID")
                    //on détermine la page à utiliser pour le logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    //redirige vers la page d'accueil
                    .logoutSuccessUrl("/")
                    //permission d'accès à tout le monde
                    .permitAll();
        });

        return http.build();
    }
}

