package web.in312019.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SALA")
public class Sala implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer kapacitet;

    @Column(name = "oznaka_sale", nullable = false, unique = true)
    private String oznakaSale;
    
    @Column(name = "u_upotrebi")
    private Boolean uUpotrebi;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private FitnessCentar fitnessCentar;
    
    @OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Termin> termini = new HashSet<>();

	public Sala() {
		super();
	}
	

	public Sala(Integer kapacitet, String oznakaSale, Boolean uUpotrebi, FitnessCentar fitnessCentar) {
		super();
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.uUpotrebi = uUpotrebi;
		this.fitnessCentar = fitnessCentar;
	}


	public Sala(Long id, Integer kapacitet, String oznakaSale, Boolean uUpotrebi, FitnessCentar fitnessCentar) {
		super();
		this.id = id;
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.uUpotrebi = uUpotrebi;
		this.fitnessCentar = fitnessCentar;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getuUpotrebi() {
		return uUpotrebi;
	}

	public void setuUpotrebi(Boolean uUpotrebi) {
		this.uUpotrebi = uUpotrebi;
	}

	public FitnessCentar getFitnessCentar() {
		return fitnessCentar;
	}

	public void setFitnessCentar(FitnessCentar fitnessCentar) {
		this.fitnessCentar = fitnessCentar;
	}

	public Integer getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(Integer kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznakaSale() {
		return oznakaSale;
	}

	public void setOznakaSale(String oznakaSale) {
		this.oznakaSale = oznakaSale;
	}

	public Set<Termin> getTermini() {
		return termini;
	}

	public void setTermini(Set<Termin> termini) {
		this.termini = termini;
	}	
    
	
    
}
