package sprintovi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Dostavljac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String JMBG;
	
	@Column(unique = true, nullable = false)
	private String licna;
	
	@Column(nullable = false)
	private String ime;
	
	@OneToMany(mappedBy="dostavljac", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Narudzba> narudzbe = new ArrayList<>();

	public Dostavljac() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dostavljac(Long id, String jMBG, String licna, String ime, List<Narudzba> narudzbe) {
		super();
		this.id = id;
		this.JMBG = jMBG;
		this.licna = licna;
		this.ime = ime;
		this.narudzbe = narudzbe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getLicna() {
		return licna;
	}

	public void setLicna(String licna) {
		this.licna = licna;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public List<Narudzba> getNarudzbe() {
		return narudzbe;
	}

	public void setNarudzbe(List<Narudzba> narudzbe) {
		this.narudzbe = narudzbe;
	}
	
	public void addNarudzba(Narudzba narudzba) {
		if(narudzba.getDostavljac() != this) {
			narudzba.setDostavljac(this);
		}
		narudzbe.add(narudzba);
	}
	
}
