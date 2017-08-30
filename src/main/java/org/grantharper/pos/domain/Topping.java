package org.grantharper.pos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Topping
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "topping_id")
  private Long toppingId;
  
  private String description;
  
  private Double price;
  
  @ManyToOne
  @JoinColumn(name = "pizza_id")
  private Pizza pizza;

  public Pizza getPizza()
  {
    return pizza;
  }

  public void setPizza(Pizza pizza)
  {
    this.pizza = pizza;
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
  
  
  
}
