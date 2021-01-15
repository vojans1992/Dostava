package sprintovi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import sprintovi.model.Dostavljac;
import sprintovi.model.Narudzba;
import sprintovi.model.Racun;
import sprintovi.model.User;
import sprintovi.model.UserRole;
import sprintovi.repository.DostavljacRepository;
import sprintovi.repository.NarudzbaRepository;
import sprintovi.repository.RacunRepository;
import sprintovi.service.UserService;

@Component
public class TestData {

	@Autowired
	private UserService userService;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired DostavljacRepository dostavljacRepository;
	@Autowired RacunRepository racunRepository;
	@Autowired NarudzbaRepository narudzbaRepository;

	@PostConstruct
	public void init() {
		LocalDateTime datum = LocalDateTime.now();
		
		Dostavljac d1 = new Dostavljac();
		d1.setId(1L);
		d1.setIme("ime1");
		d1.setJMBG("JMBG");
		d1.setLicna("licna");
		
		Dostavljac d2 = new Dostavljac();
		d2.setId(2L);
		d2.setIme("ime2");
		d2.setJMBG("JMBG2");
		d2.setLicna("licna2");
		
		Dostavljac d3 = new Dostavljac();
		d3.setId(3L);
		d3.setIme("ime3");
		d3.setJMBG("JMBG3");
		d3.setLicna("licna3");
		
		dostavljacRepository.save(d1);
		dostavljacRepository.save(d2);
		dostavljacRepository.save(d3);
		
		Racun r1 = new Racun();
		r1.setId(1L);
		r1.setBroj(1);
		r1.setDatum(datum);
		//r1.setNarudzba(null);
		r1.setUkupno(20.00);
		
		Narudzba n1 = new Narudzba();
		n1.setId(1L);
		n1.setBroj(1);
		n1.setCena(21.00);
		n1.setDatum(datum);
		n1.setDostavljac(d1);
		n1.setMesto("MEsto1");
		n1.setOpis("opis1");
		
		Narudzba n2 = new Narudzba();
		n2.setId(2L);
		n2.setBroj(2);
		n2.setCena(22.00);
		n2.setDatum(datum);
		n2.setDostavljac(d2);
		n2.setMesto("MEsto2");
		n2.setOpis("opis2");
		
		Narudzba n3 = new Narudzba();
		n3.setId(3L);
		n3.setBroj(3);
		n3.setCena(23.00);
		n3.setDatum(datum);
		n3.setDostavljac(d2);
		n3.setMesto("MEsto3");
		n3.setOpis("opis3");
		
		Narudzba n4 = new Narudzba();
		n4.setId(4L);
		n4.setBroj(4);
		n4.setCena(24.00);
		n4.setDatum(datum);
		n4.setDostavljac(d1);
		n4.setMesto("MEsto4");
		n4.setOpis("opis4");
		
		narudzbaRepository.save(n1);
		narudzbaRepository.save(n2);
		narudzbaRepository.save(n3);
		narudzbaRepository.save(n4);
		r1.setNarudzba(n1);
		
		racunRepository.save(r1);
		
		
		
		List<User> users = new ArrayList<User>();
		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setUsername("user" + i);
			user.setFirstName("First " + i);
			user.setLastName("Last " + i);
			user.setEmail("user"+i+"@mail.com");
			user.setDateOfBirth(LocalDateTime.now().minusYears(20 + i));

			// ubacen deo koda zbog greske koja se desavala ako kroz ubacivanje podataka dva puta
			// kriptujemo lozinku
			String encodedPass = passwordEncoder.encode("pass"+i);
			user.setPassword(encodedPass);

			List<UserRole> roles = Arrays.asList(UserRole.values());
			Random random = new Random();
			user.setRole(roles.get(random.nextInt(3)));
			
			users.add(user);
			userService.save(user);
			
		}
	}
}
