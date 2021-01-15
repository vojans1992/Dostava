package sprintovi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sprintovi.model.Narudzba;
import sprintovi.model.Racun;
import sprintovi.repository.NarudzbaRepository;
import sprintovi.repository.RacunRepository;
import sprintovi.service.NarudzbaService;
import sprintovi.service.RacunService;

@Service
public class JpaRacunService implements RacunService{

	@Autowired RacunRepository racunRepository;
	@Autowired NarudzbaService narudzbaService;
	@Autowired NarudzbaRepository narudzbaRepository;
	
	@Override
	public Optional<Racun> one(Long id) {
		// TODO Auto-generated method stub
		return racunRepository.findById(id);
	}

	@Override
	public List<Racun> all() {
		// TODO Auto-generated method stub
		return racunRepository.findAll();
	}

	@Override
	public Racun save(Racun racun) {
		// TODO Auto-generated method stub
		return racunRepository.save(racun);
	}

	@Override
	public Narudzba create(Long narudzbaId) {
		// TODO Auto-generated method stub
		Optional<Narudzba> n = narudzbaService.one(narudzbaId);
		Racun r = new Racun();
		r.setBroj(n.get().getBroj());
		r.setNarudzba(n.get());
		r.setUkupno(n.get().getCena());
		n.get().setRacun(r);
		
		racunRepository.save(r);
		narudzbaRepository.save(n.get());
		
		return n.get();
	}

}
