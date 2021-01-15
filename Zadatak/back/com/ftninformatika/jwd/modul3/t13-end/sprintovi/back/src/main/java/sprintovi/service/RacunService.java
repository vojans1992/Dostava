package sprintovi.service;

import java.util.List;
import java.util.Optional;

import sprintovi.model.Narudzba;
import sprintovi.model.Racun;

public interface RacunService {
	Optional<Racun> one(Long id);
	List<Racun> all();
	Racun save(Racun racun);
	Narudzba create(Long narudzbaId);
}
