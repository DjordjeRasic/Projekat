package web.in312019.entity;

import javax.persistence.*;


import java.util.*;
import java.io.Serializable;


@Entity
@Table(name = "TERMIN")
public class Termin implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "pocetak_termina", nullable = false)
    private Date pocetakTermina;
	
    @Column(name = "kraj_termina")
    private Date krajTermina;
    
    @Column(name = "trajanje_termina")
    private Integer trajanjeTermina;

    @Column(name = "cena_termina", nullable = false)
    private Integer cenaTermina;
    @Column
    private Boolean otkazan;

    @ManyToOne(fetch = FetchType.EAGER)
    private Trening trening;    
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Trener trener; 
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Sala sala; 
    
    @OneToMany(mappedBy = "termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocena> ocene = new HashSet<>();
    
    @ManyToMany(mappedBy = "odradjeniTermini")
    private Set<ClanCentra> clanoviO = new HashSet<>();
    
    @ManyToMany(mappedBy = "neodradjeniTermini")
    private Set<ClanCentra> clanoviNO = new HashSet<>();
    
    
	public Termin() {
		super();
	}
	
	

	public Termin(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			Boolean otkazan, Trening trening, Trener trener, Sala sala) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.otkazan = otkazan;
		this.trening = trening;
		this.trener = trener;
		this.sala = sala;
	}



	public Termin(Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina, Boolean otkazan,
			Trening trening, Trener trener, Sala sala) {
		super();
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.otkazan = otkazan;
		this.trening = trening;
		this.trener = trener;
		this.sala = sala;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPocetakTermina() {
		return pocetakTermina;
	}

	public void setPocetakTermina(Date pocetakTermina) {
		this.pocetakTermina = pocetakTermina;
	}

	public Date getKrajTermina() {
		return krajTermina;
	}

	public void setKrajTermina(Date krajTermina) {
		this.krajTermina = krajTermina;
	}

	public Integer getTrajanjeTermina() {
		return trajanjeTermina;
	}

	public void setTrajanjeTermina(Integer trajanjeTermina) {
		this.trajanjeTermina = trajanjeTermina;
	}

	public Integer getCenaTermina() {
		return cenaTermina;
	}

	public void setCenaTermina(Integer cenaTermina) {
		this.cenaTermina = cenaTermina;
	}

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public Trener getTrener() {
		return trener;
	}

	public void setTrener(Trener trener) {
		this.trener = trener;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Set<ClanCentra> getClanoviO() {
		return clanoviO;
	}

	public void setClanoviO(Set<ClanCentra> clanoviO) {
		this.clanoviO = clanoviO;
	}

	public Set<ClanCentra> getClanoviNO() {
		return clanoviNO;
	}

	public void setClanoviNO(Set<ClanCentra> clanoviNO) {
		this.clanoviNO = clanoviNO;
	
	}	
	public Boolean getOtkazan() {
		return otkazan;
	}

	public void setOtkazan(Boolean otkazan) {
		this.otkazan = otkazan;
	}

	
	

    
    

}

	