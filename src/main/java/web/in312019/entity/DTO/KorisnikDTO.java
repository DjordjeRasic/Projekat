package web.in312019.entity.DTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class KorisnikDTO {
	private Long id;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String lozinka;
    private String email;
    private Date datumRodjenja;
    private String telefon;
    private String uloga;
    private boolean aktivan; 
    private int zastita;
    private Long centarID;
    private List<Double> ocene;
    private List<Long> odradjeniTermini;
    private List<Long> prijavljeniTermini;
    
	public KorisnikDTO(Long id, String korisnickoIme, String ime, String prezime, String lozinka, String email,
			Date datumRodjenja, String telefon, String uloga, boolean aktivan, int zastita, Long centarID) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.uloga = uloga;
		this.aktivan = aktivan;
		this.zastita = zastita;
		this.centarID = centarID;
	}
	
	public KorisnikDTO(Long id, String korisnickoIme, String ime, String prezime, String email, Date datumRodjenja,
			String telefon, List<Double> ocene, List<Long> odradjeniTermini, List<Long> prijavljeniTermini) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.ocene = ocene;
		this.odradjeniTermini = odradjeniTermini;
		this.prijavljeniTermini = prijavljeniTermini;
	}



	public KorisnikDTO() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	public int getZastita() {
		return zastita;
	}
	public void setZastita(int zastita) {
		this.zastita = zastita;
	}
	public Long getCentarID() {
		return centarID;
	}
	public void setCentarID(Long centarID) {
		this.centarID = centarID;
	}



	public List<Double> getOcene() {
		return ocene;
	}



	public void setOcene(List<Double> ocene) {
		this.ocene = ocene;
	}



	public List<Long> getOdradjeniTermini() {
		return odradjeniTermini;
	}



	public void setOdradjeniTermini(List<Long> odradjeniTermini) {
		this.odradjeniTermini = odradjeniTermini;
	}



	public List<Long> getPrijavljeniTermini() {
		return prijavljeniTermini;
	}



	public void setPrijavljeniTermini(List<Long> prijavljeniTermini) {
		this.prijavljeniTermini = prijavljeniTermini;
	}
    
    
    
}
