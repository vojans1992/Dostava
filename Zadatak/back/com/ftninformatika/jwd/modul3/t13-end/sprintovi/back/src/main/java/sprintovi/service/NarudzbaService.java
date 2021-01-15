package sprintovi.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import sprintovi.model.Narudzba;
import sprintovi.web.dto.NarudzbaDto;

public interface NarudzbaService {

	Page<Narudzba> search(String mesto, Long dostavljacId, int pageNum);
	Page<Narudzba> all(int page);
	Optional<Narudzba> one(Long id);
	Narudzba save(NarudzbaDto narudzbaDto);
	Narudzba delete(Long id);
}
