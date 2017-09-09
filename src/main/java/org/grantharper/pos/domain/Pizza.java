package org.grantharper.pos.domain;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.grantharper.pos.enums.PizzaCrustEnum;
import org.grantharper.pos.enums.PizzaSizeEnum;

@Entity
public class Pizza implements Comparable<Pizza>
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pizza_id")
  private Long pizzaId;
  
  private PizzaSizeEnum size;
  
  @Column(name = "crust_type")
  private PizzaCrustEnum crustType;
  
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "pizza_topping", joinColumns=@JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "topping_id"))
  private Set<Topping> toppings = new TreeSet<>();
  
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  
  private Double price;

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

  public PizzaSizeEnum getSize()
  {
    return size;
  }

  public void setSize(PizzaSizeEnum size)
  {
    this.size = size;
  }

  public PizzaCrustEnum getCrustType()
  {
    return crustType;
  }

  public void setCrustType(PizzaCrustEnum crustType)
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
  
  

  public Double getPrice()
  {
    return price;
  }

  public void setPrice(Double price)
  {
    this.price = price;
  }

  @Override
  public int compareTo(Pizza otherPizza)
  {
    if(this.getPizzaId() == null){
      return 1;
    }
    if( otherPizza.getPizzaId() == null){
      return -1;
    }
    return this.getPizzaId().compareTo(otherPizza.getPizzaId());
    

  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pizzaId == null) ? 0 : pizzaId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pizza other = (Pizza) obj;
    if (pizzaId == null)
    {
      if (other.pizzaId != null)
        return false;
    } else if (!pizzaId.equals(other.pizzaId))
      return false;
    return true;
  }
  
  
  
  
  
}
