package sprintovi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sprintovi.model.Dostavljac;
import sprintovi.service.DostavljacService;
import sprintovi.support.DostavljacToDostavljacDto;
import sprintovi.web.dto.DostavljacDto;

@RestController
@RequestMapping(value = "api/dostavljaci")
public class ApiDostavljacController {

	@Autowired
	private DostavljacService dostavljacService;
	
	@Autowired
	private DostavljacToDostavljacDto toDto;
	
	@GetMapping
	public ResponseEntity<List<DostavljacDto>> getAll(){
		List<Dostavljac> dostavljaci = dostavljacService.all();
		return new ResponseEntity<>(toDto.convert(dostavljaci), HttpStatus.OK);
	}
}
