package sprintovi.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Racun {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private int broj;
	
	@Column
	private LocalDateTime datum;
	
	@Column
	private Double ukupno;
	
	@OneToOne
	private Narudzba narudzba;

	public Racun() {
		this.datum = LocalDateTime.now();
		// TODO Auto-generated constructor stub
	}

	public Racun(Long id, int broj, Double ukupno, Narudzba narudzba) {
		super();
		this.id = id;
		this.broj = broj;
		this.datum = LocalDateTime.now();
		this.ukupno = ukupno;
		this.narudzba = narudzba;
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

	public Double getUkupno() {
		return ukupno;
	}

	public void setUkupno(Double ukupno) {
		this.ukupno = ukupno;
	}

	public Narudzba getNarudzba() {
		return narudzba;
	}

	public void setNarudzba(Narudzba narudzba) {
		this.narudzba = narudzba;
	}
	
	
}
