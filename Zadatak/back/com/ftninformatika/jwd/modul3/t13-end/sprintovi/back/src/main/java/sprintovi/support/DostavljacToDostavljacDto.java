package sprintovi.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintovi.model.Dostavljac;
import sprintovi.web.dto.DostavljacDto;

@Component
public class DostavljacToDostavljacDto implements Converter<Dostavljac, DostavljacDto>{

	@Override
	public DostavljacDto convert(Dostavljac source) {
		// TODO Auto-generated method stub
		DostavljacDto dto = new DostavljacDto();
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setJMBG(source.getJMBG());
		dto.setLicna(source.getLicna());
		return dto;
	}
	
	public List<DostavljacDto> convert(List<Dostavljac> dostavljaci){
		List<DostavljacDto> dostavljaciDto = new ArrayList<>();
		for(Dostavljac d : dostavljaci) {
			dostavljaciDto.add(convert(d));
		}
		return dostavljaciDto;
	}

}
