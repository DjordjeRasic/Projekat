package web.in312019.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
@Entity
@Table(name = "TRENER")
public class Trener implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String ime;

	@Column
	private String prezime;

	@Column(nullable = false)
	private String lozinka;
	    
	@Column(nullable = false, unique = true)
	private String email;

	@Column(name = "datum_rodjenja")
	private Date datumRodjenja;

	@Column(nullable = false, unique = true)
	private String telefon;

	@Column
	private Boolean aktivan;
	
	@Column 
	private Boolean obrisan;
	
	@Column(name = "prosecna_ocena")
	private Double prosecnaOcena;
	
    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCentar fitnessCentar;
    
    @OneToMany(mappedBy = "trener", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> termini = new HashSet<>();

	public Trener() {
		super();
		prosecnaOcena = 0.0;
	}
	

	public Trener(String username, String ime, String prezime, String lozinka, String email, Date datumRodjenja,
			String telefon, Boolean aktivan, Boolean obrisan, FitnessCentar fitnessCentar) {
		super();
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.aktivan = aktivan;
		this.obrisan = obrisan;
		this.fitnessCentar = fitnessCentar;
		this.prosecnaOcena = 0.0;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public FitnessCentar getFitnessCentar() {
		return fitnessCentar;
	}

	public void setFitnessCentar(FitnessCentar fitnessCentar) {
		this.fitnessCentar = fitnessCentar;
	}

	public Set<Termin> getTermini() {
		return termini;
	}

	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}


	public Boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(Boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	    
	
	
}
