package org.grantharper.pos.domain;

import java.util.Set;

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

@Entity
public class Pizza
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pizza_id")
  private Long pizzaId;
  
  private String size;
  
  @Column(name = "crust_type")
  private String crustType;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pizza")
  private Set<Topping> toppings;
  
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  public Order getOrder()
  {
    return order;
  }

  public void setOrder(Order order)
  {
    this.order = order;
  }

  public Long getPizzaId()
  {
    return pizzaId;
  }

  public void setPizzaId(Long pizzaId)
  {
    this.pizzaId = pizzaId;
  }

  public String getSize()
  {
    return size;
  }

  public void setSize(String size)
  {
    this.size = size;
  }

  public String getCrustType()
  {
    return crustType;
  }

  public void setCrustType(String crustType)
  {
    this.crustType = crustType;
  }

  public Set<Topping> getToppings()
  {
    return toppings;
  }

  public void setToppings(Set<Topping> toppings)
  {
    this.toppings = toppings;
  }
  
  
  
  
  
}
