package org.grantharper.pos.controller;

import org.grantharper.pos.domain.Customer;
import org.grantharper.pos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController
{
  
  @Autowired
  CustomerRepository customerRepo;

  @RequestMapping(value = "/customers", method = RequestMethod.GET)
  public String getCustomers(ModelMap model)
  {
    Customer customer = new Customer();
    model.put("customer", customer);
    return "customers";
  }
  
  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  public String postCustomers(ModelMap model, @ModelAttribute("customer") Customer customer){
    
    customerRepo.save(customer);
    return "redirect:/orders";
  }
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getRoot(ModelMap model)
  {
    return "redirect:/customers";
  }
  
}
