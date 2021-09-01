package web.in312019.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.ClanCentra;
import web.in312019.entity.Ocena;
import web.in312019.entity.Termin;
import web.in312019.entity.Trener;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.repository.TerminRepository;
import web.in312019.repository.TrenerRepository;
import web.in312019.repository.ClanCentraRepository;

@Service
public class TerminService {
	
	private final TerminRepository terminRepository;
	private final TrenerRepository trenerRepository;
	private final ClanCentraRepository clanRepository;
	@Autowired
    public TerminService(TerminRepository terminRepository, TrenerRepository trenerRepository, ClanCentraRepository clanRepository) {
        this.terminRepository = terminRepository;
        this.trenerRepository = trenerRepository;
        this.clanRepository = clanRepository;
    }
	
	
	public Termin findOne(Long id) {
		Termin termin = this.terminRepository.getOne(id);
        return termin;
	}

	
	public List<Termin> findAll() {
		List<Termin> termins = this.terminRepository.findAll();
        return termins;
	}

	public Termin save(Termin termin) {
		return this.terminRepository.save(termin);
	}
	public Termin create(Termin termin) throws Exception {
		if (termin.getId() != null) {
            throw new Exception("ID must be null!");
        }
		Termin newTermin = this.terminRepository.save(termin);
        return newTermin;
	}

	
	public void delete(Long id) {
		this.terminRepository.deleteById(id);

	}
	
	public List<RasporedDTO> prikaziTermine(List<Termin> termini) {
    	
		List<RasporedDTO> raspored = new ArrayList<>();
		for (Termin termin : termini) {
	    	 Trener trener = this.trenerRepository.getOne(termin.getTrener().getId());
	    	 if(trener.isObrisan() != null && trener.isAktivan() != null) {
		    	 if(!trener.isObrisan() && trener.isAktivan()) {
			    	 RasporedDTO terminZaDodavanje = new RasporedDTO(termin.getId(), termin.getPocetakTermina(),
			    		termin.getKrajTermina(), termin.getTrajanjeTermina(), termin.getCenaTermina(), termin.getTrening().getNazivTreninga(),
			    		termin.getTrening().getOpis(), termin.getTrening().getTip());
			    	 raspored.add(terminZaDodavanje);
		    	 }
	    	 }
	    }
		return raspored;
    }
	
	public List<RasporedDTO> rezultatPretrage(RasporedDTO raspored) {
		List<Termin> termini = this.terminRepository.findAll();
		List<RasporedDTO> rezultat = new ArrayList<>();
		
		for(Termin termin : termini) {
			if((termin.getTrening().getNazivTreninga().equalsIgnoreCase(raspored.getNazivTreninga()) || raspored.getNazivTreninga().equals("sviNazivi")) && 
			(termin.getTrening().getOpis().equalsIgnoreCase(raspored.getOpisTreninga()) || raspored.getOpisTreninga().equals("sviOpisi")) &&
			(termin.getTrening().getTip().equalsIgnoreCase(raspored.getTipTreninga()) || raspored.getTipTreninga().equals("sviTipovi")) &&
			termin.getCenaTermina() <= raspored.getCenaTermina() && termin.getPocetakTermina().before(raspored.getPocetakTermina())) {
				RasporedDTO zaDodati = new RasporedDTO(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(), termin.getTrajanjeTermina(),
						termin.getCenaTermina(), termin.getTrening().getNazivTreninga(), termin.getTrening().getOpis(), termin.getTrening().getTip());
				rezultat.add(zaDodati);
			}
		}
		return rezultat;
	}
}
