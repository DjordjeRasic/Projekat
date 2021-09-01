package web.in312019.entity.DTO;

public class ResponseDTO {

	private int retVal;
	
	private String poruka;

	public ResponseDTO(int retVal, String poruka) {
		super();
		this.retVal = retVal;
		this.poruka = poruka;
	}

	public int getRetVal() {
		return retVal;
	}

	public void setRetVal(int retVal) {
		this.retVal = retVal;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}
	
	
}
