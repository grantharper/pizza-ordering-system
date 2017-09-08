package org.grantharper.pos.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.grantharper.pos.domain.Customer;
import org.grantharper.pos.domain.Order;
import org.grantharper.pos.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/orders")
public class OrderController
{
  
  @Autowired
  OrderRepository orderRepository;
  
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String orderGet(ModelMap model, HttpServletRequest request){
   
    List<Order> orders = orderRepository.findAll();
    
    model.put("orders", orders);
    
    return "orders";
  }
  
  @RequestMapping(value = "", method = RequestMethod.POST)
  public String orderPost(ModelMap model, HttpServletRequest request, @ModelAttribute Order order){
    
    Customer customer = (Customer) request.getSession().getAttribute("customer");
    order.setCustomer(customer);
    
    orderRepository.save(order);
    
    return "redirect:/orders/" + order.getOrderId() + "/pizzas";
  }
  
  @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
  public String orderGetWithId(ModelMap model, @PathVariable Long orderId){
    Order order = orderRepository.findOne(orderId);

    model.put("order", order);
    
    return "orders";
  }
  
  @RequestMapping(value = "/{orderId}", method = RequestMethod.POST)
  public String orderPostWithId(ModelMap model, HttpServletRequest request, @ModelAttribute Order order, @PathVariable Long orderId){
    
    return "redirect:/orders/" + order.getOrderId() + "/pizzas";
  }
  
  
  @RequestMapping(value = "/{orderId}/finalize", method = RequestMethod.POST)
  public String orderPostFinalize(ModelMap model, HttpServletRequest request, @PathVariable Long orderId){
    Order order = orderRepository.findOne(orderId);
    order.setCompleted(true);
    orderRepository.save(order);
    
    //could finalize the price and send email (more order finalization tasks)
    
    return "redirect:/orders";
  }
  
}
