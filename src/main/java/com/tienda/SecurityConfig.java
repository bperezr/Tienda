
package com.tienda;

import com.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserService getUserService(){
      return new UserService();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
     DaoAuthenticationProvider daoAuthneticationProvider = new DaoAuthenticationProvider();
     daoAuthneticationProvider.setPasswordEncoder(passwordEncoder());
     daoAuthneticationProvider.setUserDetailsService(getUserService());
     return daoAuthneticationProvider;
    }
    
    @Bean
    public AuthenticationSuccessHandler appAuthenticationSuccessHandler(){
     return new AppAuthenticationSuccessHandler();
    }
    
    public SecurityConfig (UserService userPrincipalDetailsService){
     this.userDetailsService = userPrincipalDetailsService;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure (HttpSecurity http) throws Exception {
      http.authorizeHttpRequests()
              .antMatchers("/persona","/login")
              .hasRole("ADMIN")
              .antMatchers("/personasN","/persona","/","/login")
              .hasAnyRole("USER", "VENDEDOR","ADMIN")
              .anyRequest().authenticated()
              .and()
              .formLogin();
    }
    
}
