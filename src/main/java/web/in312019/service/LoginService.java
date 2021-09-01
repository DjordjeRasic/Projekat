package web.in312019.service;

import org.springframework.stereotype.Service;

import java.util.*;

import web.in312019.entity.Administrator;
import web.in312019.entity.ClanCentra;
import web.in312019.entity.Trener;
import web.in312019.entity.DTO.LoginDTO;
import web.in312019.repository.AdministratorRepository;
import web.in312019.repository.ClanCentraRepository;
import web.in312019.repository.TrenerRepository;

@Service
public class LoginService {

	private final ClanCentraRepository clanRepository;
	private final TrenerRepository trenerRepository;
	private final AdministratorRepository adminRepository;
	
	
	public LoginService(ClanCentraRepository clanRepository, TrenerRepository trenerRepository,
			AdministratorRepository adminRepository) {
		super();
		this.clanRepository = clanRepository;
		this.trenerRepository = trenerRepository;
		this.adminRepository = adminRepository;
	}
	
	public LoginDTO login(LoginDTO loginDTO) {
		List<ClanCentra> clanovi = this.clanRepository.findAll();
		List<Administrator> administratori = this.adminRepository.findAll();
		List<Trener> treneri = this.trenerRepository.findAll();
	
	for(Administrator admin : administratori) {
    	if(admin.getAktivan()) {
    		if(admin.getUsername().equalsIgnoreCase(loginDTO.getUsername())) {
    			if(admin.getLozinka().equals(loginDTO.getLozinka())) {
    				LoginDTO loginDTO2 = new LoginDTO(admin.getId(), admin.getUsername(), "****", "admin", 0, "");
    				return loginDTO2;
    			} else {
    				LoginDTO loginDTO2 = new LoginDTO(Long.valueOf(0), "", "", "admin", 1, "Sifra se ne poklapa sa korisnickim imenom!");
    				return loginDTO2;
    			}
    		}
    	}
    }
	
	for(ClanCentra clan : clanovi) {
    	if(clan.getAktivan()) {
    		
    		if(clan.getUsername().equalsIgnoreCase(loginDTO.getUsername())) {
    			
    			
    			if(clan.getLozinka().equals(loginDTO.getLozinka())) {
    				
    				LoginDTO loginDTO2 = new LoginDTO(clan.getId(), clan.getUsername(), "****", "clan", 0, "");
    				return loginDTO2;
    			} else {
    				LoginDTO loginDTO2 = new LoginDTO(Long.valueOf(0), "", "", "clan", 1, "Sifra se ne poklapa sa korisnickim imenom!");
    				return loginDTO2;
    			}
    		}
    	}
    }
	
    for(Trener trener : treneri) {
        	if(trener.isAktivan()) {
        		//System.out.println(trener.getKorisnickoIme() + ", da li je obrisan? " + trener.getObrisan());
        		if(!trener.isObrisan()) {
	        		if(trener.getUsername().equalsIgnoreCase(loginDTO.getUsername())) {
	        			if(trener.getLozinka().equals(loginDTO.getLozinka())) {
	        				LoginDTO loginDTO2 = new LoginDTO(trener.getId(), trener.getUsername(), "****", "trener", 0, "");
	        				return loginDTO2;
	        			} else {
	        				LoginDTO loginDTO2 = new LoginDTO(Long.valueOf(0), "", "", "trener", 1, "Sifra se ne poklapa sa korisnickim imenom!");
	        				return loginDTO2;
	        			}
	        		}
        		} else {
        			if(trener.getUsername().equalsIgnoreCase(loginDTO.getUsername())) {
        				LoginDTO loginDTO2 = new LoginDTO(Long.valueOf(0), "", "", "trener", 1, "Korisnik je obrisan!");
        				return loginDTO2;
        			}
            	}
        	} else {
        		if(trener.getUsername().equalsIgnoreCase(loginDTO.getUsername())) {
        			LoginDTO loginDTO2 = new LoginDTO(Long.valueOf(0), "", "", "trener", 1, "Korisnik nije prihvacen jos uvek!");
    				return loginDTO2;
        		}        		
        	}     		
    }
    
    
    LoginDTO loginDTO2 = new LoginDTO(Long.valueOf(0), "", "", "", 1, "Korisnicko ime ne postoji!");
	return loginDTO2;
	}
	
}
