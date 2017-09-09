package org.grantharper.pos.controller;

import java.util.Optional;

import org.grantharper.pos.domain.Order;
import org.grantharper.pos.domain.Pizza;
import org.grantharper.pos.domain.Topping;
import org.grantharper.pos.enums.PizzaCrustEnum;
import org.grantharper.pos.enums.PizzaSizeEnum;
import org.grantharper.pos.repository.OrderRepository;
import org.grantharper.pos.repository.PizzaRepository;
import org.grantharper.pos.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/orders/{orderId}/pizzas")
public class PizzaController
{

  @Autowired
  private OrderRepository orderRepo;
  
  @Autowired
  private PizzaRepository pizzaRepo;

  @Autowired
  private ToppingRepository toppingRepo;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String pizzaGet(ModelMap model, @PathVariable Long orderId)
  {

    model.put("pizzaSizes", PizzaSizeEnum.values());
    model.put("pizzaCrustTypes", PizzaCrustEnum.values());
    model.put("toppings", toppingRepo.findAll());

    Pizza pizza = new Pizza();
    model.put("pizza", pizza);

    return "pizzas";
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public String pizzaPost(ModelMap model, @PathVariable Long orderId, @ModelAttribute Pizza pizza)
  {
    Order order = orderRepo.findOne(orderId);
    
    Double pizzaPrice = 0.0;
    
    for (Topping topping : pizza.getToppings())
    {
      topping.getPizzas().add(pizza);
      pizzaPrice += topping.getPrice();
    }
    
    pizzaPrice += pizza.getCrustType().getPrice();
    pizzaPrice += pizza.getSize().getPrice();
    pizza.setPrice(pizzaPrice);
    pizza.setOrder(order);
    order.getPizzas().add(pizza);

    orderRepo.save(order);

    return "redirect:/orders/" + orderId;
  }

  @RequestMapping(value = "/{pizzaId}/delete", method = RequestMethod.POST)
  public String pizzaPostDelete(@PathVariable Long orderId, @PathVariable Long pizzaId){
    Order order = orderRepo.findOne(orderId);
    
    //you must remember to remove the children in order for the cascades to work
    Pizza pizzaToDelete = null;
    for (Pizza pizza : order.getPizzas()){
      if(pizza.getPizzaId().equals(pizzaId)){
        pizzaToDelete = pizza;
        
      }
    }
    if(pizzaToDelete != null){
      pizzaToDelete.getToppings().clear();
      order.getPizzas().remove(pizzaToDelete);
      pizzaToDelete.setOrder(null);
    }
    
    orderRepo.save(order);
    
    return "redirect:/orders/" + orderId;
  }
  
}
