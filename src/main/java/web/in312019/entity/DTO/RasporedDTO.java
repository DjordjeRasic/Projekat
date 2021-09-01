package web.in312019.entity.DTO;

import java.util.*;

import web.in312019.entity.Trening;

public class RasporedDTO {
	
	private Long id;
	private Date pocetakTermina;
    private Date krajTermina;
    private Integer trajanjeTermina;
    private Integer cenaTermina;
    private String nazivTreninga;
    private String opisTreninga;
    private String tipTreninga;
    private String nazivSale;
    private String nazivCentra;
    private int zastita;
    private Long terminID;
    private Long clanID;
    private double ocena;
    private String nazivTrenera;
    private double prosecnaOcena;
    private List<String> clanovi;
    
	public RasporedDTO() {
		super();
	}

	public RasporedDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String opisTreninga, String tipTreninga) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
	}

	public RasporedDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String nazivSale) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.nazivSale = nazivSale;
	}
	public RasporedDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
            String nazivTreninga, String opisTreninga, String tipTreninga, Long terminID, String nazivTrenera,
            double prosecnaOcena, List<String> clanovi) {
        super();
        this.id = id;
        this.pocetakTermina = pocetakTermina;
        this.krajTermina = krajTermina;
        this.trajanjeTermina = trajanjeTermina;
        this.cenaTermina = cenaTermina;
        this.nazivTreninga = nazivTreninga;
        this.opisTreninga = opisTreninga;
        this.tipTreninga = tipTreninga;
        this.terminID = terminID;
        this.nazivTrenera = nazivTrenera;
        this.prosecnaOcena = prosecnaOcena;
        this.clanovi = clanovi;
    }
	
	public RasporedDTO(Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, Integer cenaTermina,
			String nazivTreninga, String opisTreninga, String tipTreninga, String nazivSale, int zastita) {
		super();
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.cenaTermina = cenaTermina;
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
		this.nazivSale = nazivSale;
		this.zastita = zastita;
	}

	public RasporedDTO(String nazivTreninga, String opisTreninga, String tipTreninga, String nazivSale) {
		super();
		this.nazivTreninga = nazivTreninga;
		this.opisTreninga = opisTreninga;
		this.tipTreninga = tipTreninga;
		this.nazivSale = nazivSale;
	}

	public RasporedDTO(Long id, Integer trajanjeTermina, String nazivTreninga, String nazivSale, String nazivCentra,
			double ocena, String nazivTrenera) {
		super();
		this.id = id;
		this.trajanjeTermina = trajanjeTermina;
		this.nazivTreninga = nazivTreninga;
		this.nazivSale = nazivSale;
		this.nazivCentra = nazivCentra;
		this.ocena = ocena;
		this.nazivTrenera = nazivTrenera;
	}
	
	public RasporedDTO(Long id, Date pocetakTermina, Date krajTermina, Integer trajanjeTermina, String nazivTreninga,
			String nazivSale, String nazivCentra, String nazivTrenera) {
		super();
		this.id = id;
		this.pocetakTermina = pocetakTermina;
		this.krajTermina = krajTermina;
		this.trajanjeTermina = trajanjeTermina;
		this.nazivTreninga = nazivTreninga;
		this.nazivSale = nazivSale;
		this.nazivCentra = nazivCentra;
		this.nazivTrenera = nazivTrenera;
	}

	public String getNazivCentra() {
		return nazivCentra;
	}

	public void setNazivCentra(String nazivCentra) {
		this.nazivCentra = nazivCentra;
	}

	public Long getClanID() {
		return clanID;
	}

	public void setClanID(Long clanID) {
		this.clanID = clanID;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public int getZastita() {
		return zastita;
	}

	public void setZastita(int zastita) {
		this.zastita = zastita;
	}

	public String getNazivSale() {
		return nazivSale;
	}
	public void setNazivSale(String nazivSale) {
		this.nazivSale = nazivSale;
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
	public String getNazivTreninga() {
		return nazivTreninga;
	}
	public void setNazivTreninga(String nazivTreninga) {
		this.nazivTreninga = nazivTreninga;
	}
	public String getOpisTreninga() {
		return opisTreninga;
	}
	public void setOpisTreninga(String opisTreninga) {
		this.opisTreninga = opisTreninga;
	}
	public String getTipTreninga() {
		return tipTreninga;
	}
	public void setTipTreninga(String tipTreninga) {
		this.tipTreninga = tipTreninga;
	}

	public Long getTerminID() {
		return terminID;
	}

	public void setTerminID(Long terminID) {
		this.terminID = terminID;
	}

	public String getNazivTrenera() {
		return nazivTrenera;
	}

	public void setNazivTrenera(String nazivTrenera) {
		this.nazivTrenera = nazivTrenera;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public List<String> getClanovi() {
		return clanovi;
	}

	public void setClanovi(List<String> clanovi) {
		this.clanovi = clanovi;
	}
	
}
