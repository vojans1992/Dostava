package sprintovi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sprintovi.model.Dostavljac;
@Repository
public interface DostavljacRepository extends JpaRepository<Dostavljac, Long>{

}
