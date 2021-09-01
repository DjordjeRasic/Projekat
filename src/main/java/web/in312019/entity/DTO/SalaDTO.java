package web.in312019.entity.DTO;




public class SalaDTO {
	
    
    private Long id;

    
    private Integer kapacitet;

    
    private String oznakaSale;
    
    
    private Boolean uUpotrebi;
    
    private Long idFc;
    
    private String fitnessCentar;
    
    

	public SalaDTO(Long id, Integer kapacitet, String oznakaSale, Boolean uUpotrebi, Long idFc, String fitnessCentar) {
		super();
		this.id = id;
		this.kapacitet = kapacitet;
		this.oznakaSale = oznakaSale;
		this.uUpotrebi = uUpotrebi;
		this.idFc = idFc;
		this.fitnessCentar = fitnessCentar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getuUpotrebi() {
		return uUpotrebi;
	}

	public void setuUpotrebi(Boolean uUpotrebi) {
		this.uUpotrebi = uUpotrebi;
	}

	public Long getIdFc() {
		return idFc;
	}

	public void setIdFc(Long idFc) {
		this.idFc = idFc;
	}

	public String getFitnessCentar() {
		return fitnessCentar;
	}

	public void setFitnessCentar(String fitnessCentar) {
		this.fitnessCentar = fitnessCentar;
	}
    
    
}
