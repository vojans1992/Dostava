package sprintovi.service;

import java.util.List;
import java.util.Optional;

import sprintovi.model.Dostavljac;

public interface DostavljacService {

	List<Dostavljac> all();
	Optional<Dostavljac> one(Long id);
	Dostavljac save(Dostavljac dostavljac);
}
