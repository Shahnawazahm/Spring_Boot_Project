package com.myBlog.myBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //to use preAuthorize annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean //we can use bean only in Configuration classes
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } //create an object of BCry....
    @Override
    protected  void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/api/**").permitAll() //don't put these under authentication, if any user wants to access, provide them
// if not to use @EnableGlobalMethodSecurity annotation and @PreAuthorize annotation in controller class
//                .antMatchers(HttpMethod.POST,"/api/auth/signup").hasRole("ROLE_SUPER") //do signup permission to super user only
                .antMatchers(HttpMethod.POST,"/api/auth/signup").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    //method responsible to create object in which username and password is to be stored
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder().username("user").password(passwordEncoder().encode("password")).roles("USER").build();
        UserDetails user2 = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1,user2); //storing users in inMemory object not in db
    }
}