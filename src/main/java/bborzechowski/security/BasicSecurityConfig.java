package bborzechowski.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    public BasicSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user")
                .password("$2a$10$azLPsKqPhDji9xVpKzrNzOMcyxiD6c95z0KUmLUwz38J47keVIyOy")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("$2a$10$ZYu/I.ofA88lP/16W7CP9Ow1vkPYL6BzAQCR.6p5wO.V9U.PivE5i")
                .roles("ADMIN", "USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  //zabezpieczenia ddos itp
                .cors().disable()
                .authorizeRequests() //wszystkie zaytania beda auteryzowane
                .antMatchers("/api/user").hasAnyRole("USER", "ADMIN") //ustawiamy pod jakie urle moze sie dostac dany uzytkownik
                .antMatchers("/api/admin").hasRole("ADMIN")
              //  .anyRequest().authenticated()  //wszystkie inne musza być auteryzowane
                .anyRequest().permitAll() //kazdy inny jest wolny dostepny
                .and()
                .formLogin()
                .defaultSuccessUrl("/api/home"); //jeśli sie zalogujesz przejdzie  do home


    }
}
