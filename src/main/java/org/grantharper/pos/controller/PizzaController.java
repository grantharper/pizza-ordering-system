package org.grantharper.pos.controller;

import org.grantharper.pos.domain.Pizza;
import org.grantharper.pos.enums.PizzaCrustEnum;
import org.grantharper.pos.enums.PizzaSizeEnum;
import org.grantharper.pos.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/orders/{orderId}/pizzas")
public class PizzaController
{
  
  
  @Autowired
  private ToppingRepository toppingRepository;
  
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String pizzaGet(ModelMap model, @PathVariable Long orderId){
    
    model.put("pizzaSizes", PizzaSizeEnum.values());
    model.put("pizzaCrustTypes", PizzaCrustEnum.values());
    model.put("toppings", toppingRepository.findAll());
    
    Pizza pizza = new Pizza();
    model.put("pizza", pizza);
    
    return "pizzas";
  }

}
