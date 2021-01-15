package sprintovi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sprintovi.model.Narudzba;

@Repository
public interface NarudzbaRepository extends JpaRepository<Narudzba, Long>{

	@Query("SELECT n FROM Narudzba n WHERE" +
			"(:mesto = NULL OR n.mesto LIKE :mesto) AND " +
			"(:dostavljacId = NULL OR n.dostavljac.id = :dostavljacId)")
	Page<Narudzba> search(@Param("mesto") String mesto,@Param("dostavljacId") Long id, Pageable pageable);
}
