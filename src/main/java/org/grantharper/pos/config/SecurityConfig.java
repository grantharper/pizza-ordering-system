package org.grantharper.pos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
  
  @Autowired
  public void globalConfig(AuthenticationManagerBuilder auth, UserDetailsService userDetailsService) throws Exception {
    auth.userDetailsService(userDetailsService);
  }


  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.authorizeRequests().antMatchers("/customers").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").defaultSuccessUrl("/orders").permitAll().and()
        .logout().logoutSuccessUrl("/").permitAll();
    
    // disable these protections so that I can access the H2 console
    httpSecurity.csrf().disable();
    httpSecurity.headers().frameOptions().disable();
  }
  
  
}
