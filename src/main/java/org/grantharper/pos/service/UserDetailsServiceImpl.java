package org.grantharper.pos.service;

import org.grantharper.pos.domain.CustomUserDetails;
import org.grantharper.pos.domain.Customer;
import org.grantharper.pos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
  
  @Autowired
  private CustomerRepository customerRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    Customer customer = customerRepo.findByEmailAddress(username);
    
    if(customer == null)
    {
      throw new UsernameNotFoundException("Username " + username + " not found.");
    }else {
      return new CustomUserDetails(customer);
    }

  }

}
