package fr.eni.ecole.enchere.securite;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String SELECT_USER = "select email, mot_de_passe, active from utilisateurs where email = ?";
    private final String SELECT_ROLES = "select email, role from roles where email = ?";
    private static UserDetailsService userService;

    public SecurityConfig(UserDetailsService userService) {
        this.userService = userService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        manager.setUsersByUsernameQuery(SELECT_USER);
        manager.setAuthoritiesByUsernameQuery(SELECT_ROLES);

        return manager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            //On donne accès à la reque^te de type Get /security
            auth.requestMatchers(HttpMethod.GET, "/encheres").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/register").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/search-article").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/register").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/search-article").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/css/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/images/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/uploads/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/js/*").permitAll();
            auth.requestMatchers(HttpMethod.GET, "/forget-password").permitAll();
            auth.requestMatchers(HttpMethod.POST, "/forget-password/change-password").permitAll();
            auth.anyRequest().authenticated();
            //auth.anyRequest().denyAll();
        });

        //Gestion automatique du login
//        http.formLogin(Customizer.withDefaults());

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

