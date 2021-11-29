package edu.neiu.tweak.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    public static BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/", "/createprofile/**").permitAll()  //allow all to view
                .and()
                .authorizeRequests()
                .antMatchers("/createpost/**", "/viewposts/**", "/viewprofiles/**", "/useraccount/**").hasAnyRole("ADMIN", "USER") //required auth to view
                .and()
                .authorizeRequests()
                .antMatchers("/login/**", "/createprofile/**").anonymous()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/useraccount", true).permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();
    }

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER") //give user a role
            .and()
                .withUser("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN");
    }
}
