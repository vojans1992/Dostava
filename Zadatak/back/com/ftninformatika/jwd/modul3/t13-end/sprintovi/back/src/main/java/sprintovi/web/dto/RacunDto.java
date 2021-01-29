package sprintovi.web.dto;

import sprintovi.model.Narudzba;

public class RacunDto {

	private Long id;
	private int broj;
	private String datum;
	private Double ukupno;
	private NarudzbaDto narudzbaDto;
	public RacunDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Double getUkupno() {
		return ukupno;
	}
	public void setUkupno(Double ukupno) {
		this.ukupno = ukupno;
	}
	public NarudzbaDto getNarudzbaDto() {
		return narudzbaDto;
	}
	public void setNarudzbaDto(NarudzbaDto narudzbaDto) {
		this.narudzbaDto = narudzbaDto;
	}
	
	
	
}
