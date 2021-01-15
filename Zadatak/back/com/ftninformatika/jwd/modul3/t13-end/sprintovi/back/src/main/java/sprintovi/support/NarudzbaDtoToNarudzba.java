package sprintovi.support;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintovi.model.Dostavljac;
import sprintovi.model.Narudzba;
import sprintovi.service.DostavljacService;
import sprintovi.service.NarudzbaService;
import sprintovi.web.dto.NarudzbaDto;

@Component
public class NarudzbaDtoToNarudzba implements Converter<NarudzbaDto, Narudzba>{

	@Autowired DostavljacService dostavljacService;
	@Autowired NarudzbaService narudzbaService;
	
	@Override
	public Narudzba convert(NarudzbaDto source) {
		// TODO Auto-generated method stub
		Dostavljac dostavljac = null;
		if(source.getDostavljacId() != null) {
			dostavljac=dostavljacService.one(source.getDostavljacId()).get();
		}
		
		if(dostavljac != null) {
			Long id = source.getId();
			Narudzba narudzba = id == null ? new Narudzba() : narudzbaService.one(id).get();
			if(narudzba != null) {
				narudzba.setId(source.getId());
				narudzba.setBroj(source.getBroj());
				narudzba.setCena(source.getCena());
				narudzba.setDatum(getLocalDateTime(source.getDatum()));
				narudzba.setDostavljac(dostavljac);
				narudzba.setMesto(source.getMesto());
				narudzba.setOpis(source.getOpis());
			}
			return narudzba;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
		
		
		
	}
	
	
	   private LocalDateTime getLocalDateTime(String datumIVreme) throws DateTimeParseException {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate datum = LocalDate.parse(datumIVreme.substring(0, 10), formatter);
	        LocalTime vreme = LocalTime.parse(datumIVreme.substring(11), DateTimeFormatter.ofPattern("HH:mm"));
	        return LocalDateTime.of(datum, vreme);
	    }

}
