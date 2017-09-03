package org.grantharper.pos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Authorities implements GrantedAuthority
{

  private static final long serialVersionUID = -7161503748223646498L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "authority_id")
  private Long authorityId;
  
  @Column(name = "authority_name")
  private String authorityName;
  
  @ManyToOne
  private Customer customer;

  public Long getAuthorityId()
  {
    return authorityId;
  }

  public void setAuthorityId(Long authorityId)
  {
    this.authorityId = authorityId;
  }

  public String getAuthorityName()
  {
    return authorityName;
  }

  public void setAuthorityName(String authorityName)
  {
    this.authorityName = authorityName;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  @Override
  public String getAuthority()
  {
    return getAuthorityName();
  }
  
  
  
}
