package sprintovi.web.dto;

public class DostavljacDto {

	private Long id;
	private String JMBG;
	private String licna;
	private String ime;
	public DostavljacDto() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
}
