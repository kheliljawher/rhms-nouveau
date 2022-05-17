package io.saslab.spring.rhms.repository;

import io.saslab.spring.rhms.entity.ResponsableRessourceHumaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHRepository extends JpaRepository<ResponsableRessourceHumaine,Long> {
}
