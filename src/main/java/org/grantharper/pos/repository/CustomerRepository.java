package org.grantharper.pos.repository;

import javax.transaction.Transactional;

import org.grantharper.pos.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long>
{

  Customer findByEmailAddress(String username);

}
