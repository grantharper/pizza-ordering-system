package org.grantharper.pos.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long orderId;
  
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , mappedBy = "order")
  private Set<Pizza> pizzas = new TreeSet<>();
  
  @Column(name = "final_price")
  private Double finalPrice;
  
  private Boolean completed = false;

  public Long getOrderId()
  {
    return orderId;
  }

  public void setOrderId(Long orderId)
  {
    this.orderId = orderId;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  public Set<Pizza> getPizzas()
  {
    return pizzas;
  }

  public void setPizzas(Set<Pizza> pizzas)
  {
    this.pizzas = pizzas;
  }

  public Double getFinalPrice()
  {
    return finalPrice;
  }

  public void setFinalPrice(Double finalPrice)
  {
    this.finalPrice = finalPrice;
  }

  public Boolean getCompleted()
  {
    return completed;
  }

  public void setCompleted(Boolean completed)
  {
    this.completed = completed;
  }


  
  
}
