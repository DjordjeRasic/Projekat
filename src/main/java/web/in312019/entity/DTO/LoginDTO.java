package web.in312019.entity.DTO;

public class LoginDTO {
	
	private Long id;
	
	private String username;
	
	private String lozinka;
	
	private String uloga;
	
	private int retVal;
	
	private String poruka;

	public LoginDTO(Long id, String username, String lozinka, String uloga, int retVal, String poruka) {
		super();
		this.id = id;
		this.username = username;
		this.lozinka = lozinka;
		this.uloga = uloga;
		this.retVal = retVal;
		this.poruka = poruka;
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

	

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
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
