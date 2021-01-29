package sprintovi.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sprintovi.model.Narudzba;
import sprintovi.service.NarudzbaService;
import sprintovi.service.RacunService;
import sprintovi.support.NarudzbaToNarudzbaDto;
import sprintovi.web.dto.NarudzbaDto;

@RestController
@RequestMapping("api/narudzbe")
public class ApiNarudzbaController {
	
	@Autowired NarudzbaService narudzbaService;
	@Autowired NarudzbaToNarudzbaDto toDto;
	@Autowired RacunService racunService;
	
	@GetMapping
	public ResponseEntity<List<NarudzbaDto>> get(@RequestParam(value="mesto", required = false) String mesto,
			@RequestParam(value="dostavljacId", required = false) Long dostavljacId,
			@RequestParam(value="pageNum", defaultValue="0")int pageNum){
		Page<Narudzba> page = null;
		
		if(mesto != null || dostavljacId != null) {
			page = narudzbaService.search(mesto, dostavljacId, pageNum);
		}else {
			page = narudzbaService.all(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

		return new ResponseEntity<>(toDto.convert(page.getContent()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NarudzbaDto> getOne(@PathVariable Long id){
		Optional<Narudzba> narudzba = narudzbaService.one(id);
		if(!narudzba.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(narudzba.get()), HttpStatus.OK);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<NarudzbaDto> add(@Validated @RequestBody NarudzbaDto dto){
		Narudzba saved = narudzbaService.save(dto);
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<NarudzbaDto> edit(@Validated @RequestBody NarudzbaDto dto, @PathVariable Long id){
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Narudzba persisted = narudzbaService.save(dto);
		
		return new ResponseEntity<>(toDto.convert(persisted), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<NarudzbaDto> delete (@PathVariable Long id){
		Narudzba deleted = narudzbaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toDto.convert(deleted),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/kreiraj")
	public ResponseEntity<NarudzbaDto> kreiraj(@PathVariable Long id){
		Optional<Narudzba> narudzba = narudzbaService.one(id);
		if(!narudzba.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(narudzba.get().getRacun() == null ) {
			racunService.create(id);
			return new ResponseEntity<>(toDto.convert(narudzba.get()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
