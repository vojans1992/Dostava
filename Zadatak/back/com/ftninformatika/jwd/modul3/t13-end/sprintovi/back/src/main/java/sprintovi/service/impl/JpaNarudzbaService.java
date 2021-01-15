package sprintovi.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sprintovi.model.Narudzba;
import sprintovi.repository.NarudzbaRepository;
import sprintovi.service.NarudzbaService;
import sprintovi.support.NarudzbaDtoToNarudzba;
import sprintovi.web.dto.NarudzbaDto;

@Service
@Transactional
public class JpaNarudzbaService implements NarudzbaService{

	@Autowired NarudzbaRepository narudzbaRepository;
	@Autowired NarudzbaDtoToNarudzba toNarudzba;
	
	@Override
	public Page<Narudzba> search(String mesto, Long dostavljacId, int pageNum) {
		// TODO Auto-generated method stub
		if(mesto != null) {
			mesto = "%" + mesto + "%";
		}
		return narudzbaRepository.search(mesto, dostavljacId, PageRequest.of(pageNum, 5));
	}

	@Override
	public Page<Narudzba> all(int page) {
		// TODO Auto-generated method stub
		return narudzbaRepository.findAll(PageRequest.of(page, 5));
	}

	@Override
	public Optional<Narudzba> one(Long id) {
		// TODO Auto-generated method stub
		return narudzbaRepository.findById(id);
	}

	@Override
	public Narudzba save(NarudzbaDto narudzbaDto) {
		// TODO Auto-generated method stub
		Narudzba narudzba = toNarudzba.convert(narudzbaDto);
		Narudzba sacuvana = narudzbaRepository.save(narudzba);
		return sacuvana;
	}

	@Override
	public Narudzba delete(Long id) {
		// TODO Auto-generated method stub
		Optional<Narudzba> opt = narudzbaRepository.findById(id);
		if(opt.isPresent()) {
			Narudzba narudzba = opt.get();
			narudzbaRepository.deleteById(id);
			return narudzba;
		}
		return null;
	}

}
