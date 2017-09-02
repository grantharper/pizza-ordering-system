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
import javax.persistence.ManyToMany;

@Entity
public class Topping implements Comparable<Topping>
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "topping_id")
  private Long toppingId;

  private String description;

  private Double price;

  @ManyToMany(cascade = CascadeType.ALL, mappedBy = "toppings")
  private Set<Pizza> pizzas = new TreeSet<>();

  public Set<Pizza> getPizzas()
  {
    return pizzas;
  }

  public void setPizzas(Set<Pizza> pizzas)
  {
    this.pizzas = pizzas;
  }

  public Long getToppingId()
  {
    return toppingId;
  }

  public void setToppingId(Long toppingId)
  {
    this.toppingId = toppingId;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
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
  public int compareTo(Topping otherTopping)
  {
    if (otherTopping.getToppingId() == null)
    {
      return -1;
    }
    if (this.getToppingId() == null)
    {
      return 1;
    }
    return this.getToppingId().compareTo(otherTopping.getToppingId());

  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((toppingId == null) ? 0 : toppingId.hashCode());
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
    Topping other = (Topping) obj;
    if (toppingId == null)
    {
      if (other.toppingId != null)
        return false;
    } else if (!toppingId.equals(other.toppingId))
      return false;
    return true;
  }

}
