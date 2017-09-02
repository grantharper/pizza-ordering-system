package org.grantharper.pos.repository;

import javax.transaction.Transactional;

import org.grantharper.pos.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PizzaRepository extends JpaRepository<Pizza , Long>
{

  
}
