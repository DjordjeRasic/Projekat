package web.in312019.entity;

import javax.persistence.*;
import java.util.*;
import java.io.Serializable;

@Entity
@Table(name = "FITNESS_CENTAR")
public class FitnessCentar implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv_centra", nullable = false)
    private String nazivCentra;

    @Column(nullable = false)
    private String adresa;

    @Column(name = "broj_telefona_centra", nullable = false, unique = true)
    private String brojTelefonaCentra;

    @Column(name = "email_centra", nullable = false, unique = true)
    private String emailCentra;
    
    @Column
    private Boolean obrisan;
    
    @OneToMany(mappedBy = "fitnessCentar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sala> sale = new HashSet<>();
    
    @OneToMany(mappedBy = "fitnessCentar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Trener> treneri = new HashSet<>();
    

	public FitnessCentar() {
		super();
	}
	

	public FitnessCentar(String nazivCentra, String adresa, String brojTelefonaCentra, String emailCentra, Boolean obrisan) {
		super();
		this.nazivCentra = nazivCentra;
		this.adresa = adresa;
		this.brojTelefonaCentra = brojTelefonaCentra;
		this.emailCentra = emailCentra;
		this.obrisan = obrisan;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivCentra() {
		return nazivCentra;
	}

	public void setNazivCentra(String nazivCentra) {
		this.nazivCentra = nazivCentra;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefonaCentra() {
		return brojTelefonaCentra;
	}

	public void setBrojTelefonaCentra(String brojTelefonaCentra) {
		this.brojTelefonaCentra = brojTelefonaCentra;
	}

	public String getEmailCentra() {
		return emailCentra;
	}

	public void setEmailCentra(String emailCentra) {
		this.emailCentra = emailCentra;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public Set<Trener> getTreneri() {
		return treneri;
	}

	public void setTreneri(Set<Trener> treneri) {
		this.treneri = treneri;
	}


	public Boolean getObrisan() {
		return obrisan;
	}


	public void setObrisan(Boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
	
	
    
    
}
