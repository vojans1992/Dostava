package sprintovi.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintovi.model.Narudzba;
import sprintovi.web.dto.NarudzbaDto;

@Component
public class NarudzbaToNarudzbaDto implements Converter<Narudzba, NarudzbaDto> {

	@Override
	public NarudzbaDto convert(Narudzba source) {
		// TODO Auto-generated method stub
		NarudzbaDto dto = new NarudzbaDto();
		dto.setId(source.getId());
		dto.setBroj(source.getBroj());
		dto.setCena(source.getCena());
		dto.setDatum(source.getDatum().toString());
		dto.setDostavljacId(source.getDostavljac().getId());
		dto.setDostavljacIme(source.getDostavljac().getIme());
		dto.setMesto(source.getMesto());
		dto.setOpis(source.getOpis());
		return dto;
	}
	
	public List<NarudzbaDto> convert(List<Narudzba> narudzbe){
		List<NarudzbaDto> dtoList = new ArrayList<>();
		for(Narudzba n : narudzbe) {
			dtoList.add(convert(n));
		}
		return dtoList;
	}

}
