package sprintovi.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Narudzba {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private int broj;
	
	@Column(nullable = false)
	private LocalDateTime datum;
	
	@Column(nullable = false)
	private String mesto;
	
	@Column
	private Double  cena;
	
	@Column
	private String opis;
	
	@OneToOne
	private Racun racun;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Dostavljac dostavljac;

	public Narudzba(Long id, int broj, LocalDateTime datum, String mesto, Double cena, String opis,
			Dostavljac dostavljac) {
		super();
		this.id = id;
		this.broj = broj;
		this.datum = datum;
		this.mesto = mesto;
		this.cena = cena;
		this.opis = opis;
		this.dostavljac = dostavljac;
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

	public LocalDateTime getDatum() {
		return datum;
	}

	public void setDatum(LocalDateTime datum) {
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

	public Dostavljac getDostavljac() {
		return dostavljac;
	}

	public void setDostavljac(Dostavljac dostavljac) {
		this.dostavljac = dostavljac;
		if(!dostavljac.getNarudzbe().contains(this)) {
			dostavljac.getNarudzbe().add(this);
		}
	}

	public Narudzba() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Racun getRacun() {
		return racun;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}
	
	
}
