package org.grantharper.pos.controller;

import javax.servlet.http.HttpServletRequest;

import org.grantharper.pos.domain.Customer;
import org.grantharper.pos.domain.Order;
import org.grantharper.pos.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/orders")
public class OrderController
{
  
  @Autowired
  OrderRepository orderRepository;
  
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String orderGet(ModelMap model){
    
    Order order = new Order();
    model.put("order", order);
    
    return "orders";
  }
  
  @RequestMapping(value = "", method = RequestMethod.POST)
  public String orderPost(ModelMap model, HttpServletRequest request, @ModelAttribute Order order){
    
    Customer customer = (Customer) request.getSession().getAttribute("customer");
    order.setCustomer(customer);
    
    orderRepository.save(order);
    
    return "redirect:/orders/" + order.getOrderId() + "/pizzas";
  }
  
  
}
