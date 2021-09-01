package web.in312019.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.ClanCentra;
import web.in312019.entity.Ocena;
import web.in312019.entity.Sala;
import web.in312019.entity.Termin;
import web.in312019.entity.Trener;
import web.in312019.entity.DTO.KorisnikDTO;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.repository.ClanCentraRepository;
import web.in312019.repository.TerminRepository;
import web.in312019.repository.TrenerRepository;

@Service
public class ClanCentraService {
	
	private final ClanCentraRepository clanRepository;
	private final TerminRepository terminRepository;
	private final TrenerRepository trenerRepository;
	@Autowired
    public ClanCentraService(ClanCentraRepository clanRepository, TerminRepository terminRepository, TrenerRepository trenerRepository) {
        this.clanRepository = clanRepository;
        this.terminRepository = terminRepository;
        this.trenerRepository = trenerRepository;
    }
	
	
	public ClanCentra findOne(Long id) {
		ClanCentra clan = this.clanRepository.getOne(id);
        return clan;
	}

	
	public List<ClanCentra> findAll() {
		List<ClanCentra> clan = this.clanRepository.findAll();
        return clan;
	}

	public ClanCentra save(ClanCentra clan) {
		return this.clanRepository.save(clan);
	}
	public ClanCentra create(ClanCentra clan) throws Exception {
		if (clan.getId() != null) {
            throw new Exception("ID must be null!");
        }
		ClanCentra newClan = this.clanRepository.save(clan);
        return newClan;
	}
	
	public ResponseDTO napraviClana(KorisnikDTO korisnikDTO) {
		List<ClanCentra> clanovi = this.clanRepository.findAll();
		for(ClanCentra clan : clanovi) {
			if(clan.getUsername().equals(korisnikDTO.getKorisnickoIme())) {
				return new ResponseDTO(1, "Korisnicko ime je zauzeto!");
			}
			if(clan.getEmail().equals(korisnikDTO.getEmail())) {
				return new ResponseDTO(1, "E-Mail je zauzet!");
			}
			if(clan.getTelefon().equals(korisnikDTO.getTelefon())) {
				return new ResponseDTO(1, "Telefon je zauzet!");
			}
		}
		
		ClanCentra newClan = new ClanCentra(korisnikDTO.getKorisnickoIme(), korisnikDTO.getIme(), korisnikDTO.getPrezime(), korisnikDTO.getLozinka(), korisnikDTO.getEmail(),
											korisnikDTO.getDatumRodjenja(), korisnikDTO.getTelefon(), korisnikDTO.isAktivan());
		
		this.clanRepository.save(newClan);
		
		return new ResponseDTO(0, "");
	}

	
	public void delete(Long id) {
		this.clanRepository.deleteById(id);

	}
	
	public List<RasporedDTO> prikaziOdradjene(RasporedDTO info) {
		List<RasporedDTO> retVal = new ArrayList<>();
		if(info.getZastita() != 3) {
			return retVal;
		}
		ClanCentra clan = this.clanRepository.getOne(info.getClanID());
		Set<Termin> odradjeni = clan.getOdradjeniTermini();
		Set<Ocena> ocene = clan.getOcene();
		double ocena = -1;
		for(Termin t : odradjeni) {
			for(Ocena o : ocene) {
				if(t.getId() == o.getTermin().getId()) {
					ocena = o.getOcena();
				}
			}
			String nazivCentra = t.getSala().getFitnessCentar().getNazivCentra();
			RasporedDTO zaDodati = new RasporedDTO(t.getId(), t.getTrajanjeTermina(), t.getTrening().getNazivTreninga(), t.getSala().getOznakaSale(),
					nazivCentra, ocena, t.getTrener().getUsername());
			retVal.add(zaDodati);
			ocena = -1;
		}
		return retVal;
	}
	
	public List<RasporedDTO> prikaziOcenjene(RasporedDTO info) {
		List<RasporedDTO> retVal = new ArrayList<>();
		if(info.getZastita() != 3) {
			return retVal;
		}
		ClanCentra clan = this.clanRepository.getOne(info.getClanID());
		Set<Termin> odradjeni = clan.getOdradjeniTermini();
		Set<Ocena> ocene = clan.getOcene();
		for(Termin t : odradjeni) {
			for(Ocena o : ocene) {
				if(t.getId() == o.getTermin().getId()) {
					String nazivCentra = t.getSala().getFitnessCentar().getNazivCentra();
					RasporedDTO zaDodati = new RasporedDTO(t.getId(), t.getTrajanjeTermina(), t.getTrening().getNazivTreninga(), t.getSala().getOznakaSale(),
							nazivCentra, o.getOcena(), t.getTrener().getUsername());
					retVal.add(zaDodati);
				}
			}		
		}
		return retVal;
	}
	
	public List<RasporedDTO> prikaziPrijavljene(RasporedDTO info) {
		List<RasporedDTO> retVal = new ArrayList<>();
		if(info.getZastita() != 3) {
			return retVal;
		}
		ClanCentra clan = this.clanRepository.getOne(info.getClanID());
		Set<Termin> prijavljeni = clan.getNeodradjeniTermini();
		for(Termin t : prijavljeni) {
			String nazivCentra = t.getSala().getFitnessCentar().getNazivCentra();
			RasporedDTO zaDodati = new RasporedDTO(t.getId(), t.getPocetakTermina(), t.getKrajTermina(), t.getTrajanjeTermina(), 
					t.getTrening().getNazivTreninga(), t.getSala().getOznakaSale(), nazivCentra, t.getTrener().getUsername());
			retVal.add(zaDodati);	
		}
		return retVal;
	}
	
	public List<RasporedDTO> odjavaTermina(RasporedDTO info) {
		
		List<RasporedDTO> retVal = new ArrayList<>();
		if(info.getZastita() != 3) {
			Date d = new Date();
			RasporedDTO zaDodati = new RasporedDTO(d, d, 0, 0, "", "", "", "", 1);
			retVal.add(zaDodati);
			return retVal;
		}
		ClanCentra clan = this.clanRepository.getOne(info.getClanID());
		Set<Termin> prijavljeni = clan.getNeodradjeniTermini();
		Set<Termin> newPrijavljeni = new HashSet<>();
		for(Termin t : prijavljeni) {
			
			if(t.getId() != info.getId()) {
				newPrijavljeni.add(t);
			}
		}
		//System.out.println(prijavljeni.size() + " => " + newPrijavljeni.size());
		for(Termin t: prijavljeni) {
			String nazivCentra = t.getSala().getFitnessCentar().getNazivCentra();
			RasporedDTO zaDodati = new RasporedDTO(t.getId(), t.getPocetakTermina(), t.getKrajTermina(), t.getTrajanjeTermina(), 
					t.getTrening().getNazivTreninga(), t.getSala().getOznakaSale(), nazivCentra, t.getTrener().getUsername());
			retVal.add(zaDodati);
		}
		//System.out.println("PROLAZ2");
		clan.setNeodradjeniTermini(newPrijavljeni);
		this.clanRepository.save(clan);
		return retVal;
	}
	
	public List<RasporedDTO> prikaziTermine(RasporedDTO info) {
		ClanCentra oldClan = this.clanRepository.getOne(info.getClanID());
		Set<Termin> oldNeodradjeni = oldClan.getNeodradjeniTermini();
		Set<Termin> oldOdradjeni = oldClan.getOdradjeniTermini();
		Set<Termin> newNeodradjeni = new HashSet<>();
		Set<Termin> newOdradjeni = new HashSet<>();
		Date d = new Date();
		for(Termin termin : oldNeodradjeni) {
			if(termin.getPocetakTermina().after(d)) {
				newNeodradjeni.add(termin);
			} else {
				newOdradjeni.add(termin);
			}
			
		}
		for(Termin termin : oldOdradjeni) {
			if(termin.getPocetakTermina().after(d)) {
				newNeodradjeni.add(termin);
			} else {
				newOdradjeni.add(termin);
			}
			
		}
		oldClan.setNeodradjeniTermini(newNeodradjeni);
		oldClan.setOdradjeniTermini(newOdradjeni);
		ClanCentra clan = this.clanRepository.save(oldClan);
		
		Set<Termin> neodradjeni = clan.getNeodradjeniTermini();
		
		List<RasporedDTO> raspored = new ArrayList<>();
		if(info.getZastita() != 3) {
			return raspored;
		}
		List<Termin> termini = this.terminRepository.findAll();
		for (Termin termin : termini) {
			if(termin.getKrajTermina().after(d)) {
		    	 Trener trener = this.trenerRepository.getOne(termin.getTrener().getId());
		    	 if(trener.isObrisan() != null && trener.isAktivan() != null) {
			    	 if(!trener.isObrisan() && trener.isAktivan()) {
			    		 boolean provera = false;
			    		 for(Termin t : neodradjeni) {
			    			 if(t.getId() == termin.getId()) {
			    				 provera = true;
			    			 }
			    		 }
			    		 if(!provera) {
					    	 RasporedDTO terminZaDodavanje = new RasporedDTO(termin.getId(), termin.getPocetakTermina(),
					    		termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
					    		termin.getTrening().getOpis(), termin.getTrening().getTip());
					    	 raspored.add(terminZaDodavanje);
			    		 }
			    	 }
		    	 }
			}
	    }
		return raspored;
	}
	
	public List<RasporedDTO> pretraziTermine(RasporedDTO raspored) {
		List<Termin> termini = this.terminRepository.findAll();
		List<RasporedDTO> rezultat = new ArrayList<>();
		
		if(raspored.getZastita() != 3) {
			return rezultat;
		}
		Set<Termin> neodradjeni = this.clanRepository.getOne(raspored.getClanID()).getNeodradjeniTermini();
		Date d = new Date();
		for (Termin termin : termini) {
			if(termin.getKrajTermina().after(d)) {
		    	 Trener trener = this.trenerRepository.getOne(termin.getTrener().getId());
		    	 if(trener.isObrisan() != null && trener.isAktivan() != null) {
			    	 if(!trener.isObrisan() && trener.isAktivan()) {
			    		 boolean provera = false;
			    		 for(Termin t : neodradjeni) {
			    			 if(t.getId() == termin.getId()) {
			    				 provera = true;
			    			 }
			    		 }
			    		 if(!provera) {
			    			 if((termin.getTrening().getNazivTreninga().equalsIgnoreCase(raspored.getNazivTreninga()) || raspored.getNazivTreninga().equals("sviNazivi")) && 
	    						(termin.getTrening().getOpis().equalsIgnoreCase(raspored.getOpisTreninga()) || raspored.getOpisTreninga().equals("sviOpisi")) &&
	    						(termin.getTrening().getTip().equalsIgnoreCase(raspored.getTipTreninga()) || raspored.getTipTreninga().equals("sviTipovi")) &&
	    						termin.getCenaTermina() <= raspored.getCenaTermina() && termin.getPocetakTermina().before(raspored.getPocetakTermina())) {
	    							RasporedDTO zaDodati = new RasporedDTO(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(), termin.getTrajanjeTermina(),
	    									termin.getCenaTermina(), termin.getTrening().getNazivTreninga(), termin.getTrening().getOpis(), termin.getTrening().getTip());
	    							rezultat.add(zaDodati);
	    						}
			    		 }
			    	 }
		    	 }
			}
	    }
		return rezultat;
	}
	
	public RasporedDTO prijavaTermina(RasporedDTO info) {
		Termin termin = this.terminRepository.getOne(info.getTerminID());
		Sala sala = termin.getSala();
		if(info.getZastita() != 3) {
			Date d = new Date();
			RasporedDTO retVal = new RasporedDTO(d, d, 0, 0, "", "", "", "", 1);
			return retVal;
		}
		ClanCentra clan = this.clanRepository.getOne(info.getClanID());
		Set<Termin> prijavljeni = clan.getNeodradjeniTermini();
		Set<Termin> odradjeni = clan.getOdradjeniTermini();
		for(Termin t : prijavljeni) {
			if(t.getId() == termin.getId()) {
				Date d = new Date();
				RasporedDTO retVal = new RasporedDTO(d, d, 0, 0, "", "", "", "", 2);
				return retVal;	
			}
		}
		for(Termin t : odradjeni) {
			if(t.getId() == termin.getId()) {
				Date d = new Date();
				RasporedDTO retVal = new RasporedDTO(d, d, 0, 0, "", "", "", "", 3);
				return retVal;	
			}
		}
		if(termin.getClanoviNO().size() >= sala.getKapacitet()) {
			Date d = new Date();
			RasporedDTO retVal = new RasporedDTO(d, d, 0, 0, "", "", "", "", 4);
			return retVal;	
		}
		prijavljeni.add(termin);
		clan.setNeodradjeniTermini(prijavljeni);
		this.clanRepository.save(clan);
		Date d = new Date();
		RasporedDTO retVal = new RasporedDTO(d, d, 0, 0, "", "", "", "", 0);
		return retVal;
	}
	
	public KorisnikDTO prikaziProfil(KorisnikDTO info) {
		if(info.getZastita() != 3) {
			KorisnikDTO retVal = new KorisnikDTO();
			return retVal;
		}
		ClanCentra clan = this.clanRepository.getOne(info.getId());
		Set<Ocena> ocene = clan.getOcene();
		List<Double> retOcene = new ArrayList<>();
		for(Ocena o : ocene) {
			retOcene.add(o.getOcena());
		}
		Set<Termin> odradjeni = clan.getOdradjeniTermini();
		List<Long> retOdradjeni = new ArrayList<>();
		for(Termin t : odradjeni) {
			Date d = new Date();
			if(t.getKrajTermina().before(d)) {
				retOdradjeni.add(t.getId());
			}
		}
		Set<Termin> prijavljeni = clan.getNeodradjeniTermini();
		List<Long> retPrijavljeni = new ArrayList<>();
		for(Termin t : prijavljeni) {
			Date d = new Date();
			if(t.getKrajTermina().after(d)) {
				retPrijavljeni.add(t.getId());
			}
		}
		KorisnikDTO profil = new KorisnikDTO(clan.getId(), clan.getUsername(), clan.getIme(), clan.getPrezime(), clan.getEmail(),
				clan.getDatumRodjenja(), clan.getTelefon(), retOcene, retOdradjeni, retPrijavljeni);
		
		return profil;
	}
	
	public RasporedDTO terminDetaljno(RasporedDTO info) {
		if(info.getZastita() != 3) {
			RasporedDTO retVal = new RasporedDTO();
			return retVal;
		}
		Termin termin = this.terminRepository.getOne(info.getTerminID());
		List<Double> retOcene = new ArrayList<>();
		List<String> retClanovi = new ArrayList<>();
		Set<Ocena>  ocene = termin.getOcene();
		Set<ClanCentra> clanoviO = termin.getClanoviO();
		Set<ClanCentra> clanoviNO = termin.getClanoviNO();
		for(ClanCentra c : clanoviNO) {
			clanoviO.add(c);
		}
		for(ClanCentra c : clanoviO) {
			retClanovi.add(c.getUsername());
		}
		RasporedDTO profil = new RasporedDTO(termin.getTrening().getId(), termin.getPocetakTermina(), termin.getKrajTermina(), termin.getTrajanjeTermina(),
				termin.getCenaTermina(), termin.getTrening().getNazivTreninga(), termin.getTrening().getOpis(), termin.getTrening().getTip(),
				termin.getId(), termin.getTrener().getUsername(), termin.getTrener().getProsecnaOcena(), retClanovi);
		return profil;
	}
}
