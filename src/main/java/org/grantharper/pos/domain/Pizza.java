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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Pizza implements Comparable<Pizza>
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pizza_id")
  private Long pizzaId;
  
  private String size;
  
  @Column(name = "crust_type")
  private String crustType;
  
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "pizza_topping", joinColumns=@JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "topping_id"))
  private Set<Topping> toppings = new TreeSet<>();
  
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
