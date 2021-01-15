package sprintovi.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintovi.model.Racun;
import sprintovi.web.dto.RacunDto;

@Component
public class RacunToRacunDto implements Converter<Racun, RacunDto>{

	@Override
	public RacunDto convert(Racun source) {
		// TODO Auto-generated method stub
		RacunDto racunDto = new RacunDto();
		racunDto.setId(source.getId());
		racunDto.setBroj(source.getBroj());
		racunDto.setDatum(source.getDatum().toString());
		racunDto.setNarudzba(source.getNarudzba());
		racunDto.setUkupno(source.getUkupno());
		return racunDto;
	}
	
	public List<RacunDto> convert(List<Racun> racuni){
		List<RacunDto>racuniDto = new ArrayList<>();
		for(Racun r : racuni) {
			racuniDto.add(convert(r));
		}
		return racuniDto;
	}

}
