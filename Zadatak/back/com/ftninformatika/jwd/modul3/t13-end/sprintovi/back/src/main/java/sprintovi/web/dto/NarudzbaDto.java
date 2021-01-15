package sprintovi.web.dto;

import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class NarudzbaDto {

	private Long id;
	
	@Positive
	private int broj;
	
	private String datum;
	
	@Length(max = 50)
	private String mesto;
	private Double cena;
	private String opis;
	private Long dostavljacId;
	private String dostavljacIme;
	public NarudzbaDto() {
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
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Long getDostavljacId() {
		return dostavljacId;
	}
	public void setDostavljacId(Long dostavljacId) {
		this.dostavljacId = dostavljacId;
	}
	public String getDostavljacIme() {
		return dostavljacIme;
	}
	public void setDostavljacIme(String dostavljacIme) {
		this.dostavljacIme = dostavljacIme;
	}
	
	
}
