package web.in312019.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
@Entity
@Table(name="CLAN")
public class ClanCentra implements Serializable {
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

    @Column(unique = true)
    private String telefon;

    @Column
    private Boolean aktivan;
    
    @OneToMany(mappedBy = "clan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "attended",
            joinColumns = @JoinColumn(name = "clan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "termin_id", referencedColumnName = "id"))
    private Set<Termin> odradjeniTermini = new HashSet<>();
    @ManyToMany
    @JoinTable(name = "unattended",
            joinColumns = @JoinColumn(name = "clan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "termin_id", referencedColumnName = "id"))
    private Set<Termin> neodradjeniTermini = new HashSet<>();
    
    
    
	public ClanCentra(String username, String ime, String prezime, String lozinka, String email,
			Date datumRodjenja, String telefon, Boolean aktivan) {
		super();
		this.username = username;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.telefon = telefon;
		this.aktivan = aktivan;
	}

	public ClanCentra() {
		super();
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

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Set<Termin> getOdradjeniTermini() {
		return odradjeniTermini;
	}

	public void setOdradjeniTermini(Set<Termin> odradjeniTermini) {
		this.odradjeniTermini = odradjeniTermini;
	}

	public Set<Termin> getNeodradjeniTermini() {
		return neodradjeniTermini;
	}

	public void setNeodradjeniTermini(Set<Termin> neodradjeniTermini) {
		this.neodradjeniTermini = neodradjeniTermini;
	}
	
	

    

    
    
}
