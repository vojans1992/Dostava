package sprintovi.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sprintovi.model.Racun;
import sprintovi.service.RacunService;
import sprintovi.support.RacunToRacunDto;
import sprintovi.web.dto.RacunDto;

@RestController
@RequestMapping(value = "/api/racuni")
public class ApiRacunController {
	
	@Autowired RacunService racunService;
	@Autowired RacunToRacunDto toDto;
	
	@GetMapping("/{id}")
	public ResponseEntity<RacunDto> getOne(@PathVariable Long id){
		Optional<Racun> racun = racunService.one(id);
		if(!racun.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(racun.get()), HttpStatus.OK);
	}
}
