package web.in312019.entity.DTO;

public class OcenaDTO {
	private Long clanID;
	
	private Long terminID;
	
	private double ocena;

	public OcenaDTO(Long clanID, Long terminID, double ocena) {
		super();
		this.clanID = clanID;
		this.terminID = terminID;
		this.ocena = ocena;
	}

	public Long getClanID() {
		return clanID;
	}

	public void setClanID(Long clanID) {
		this.clanID = clanID;
	}

	public Long getTerminID() {
		return terminID;
	}

	public void setTerminID(Long terminID) {
		this.terminID = terminID;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
	
	
}
