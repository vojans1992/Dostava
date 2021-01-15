package sprintovi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sprintovi.model.Dostavljac;
import sprintovi.repository.DostavljacRepository;
import sprintovi.service.DostavljacService;

@Service
public class JpaDostavljacService implements DostavljacService{

	@Autowired DostavljacRepository dostavljacRepository;
	
	@Override
	public List<Dostavljac> all() {
		// TODO Auto-generated method stub
		return dostavljacRepository.findAll();
	}

	@Override
	public Optional<Dostavljac> one(Long id) {
		// TODO Auto-generated method stub
		return dostavljacRepository.findById(id);
	}

	@Override
	public Dostavljac save(Dostavljac dostavljac) {
		// TODO Auto-generated method stub
		return dostavljacRepository.save(dostavljac);
	}

}
