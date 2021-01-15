package sprintovi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sprintovi.model.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long>{

}
