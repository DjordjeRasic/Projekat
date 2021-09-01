package web.in312019.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.ClanCentra;
import web.in312019.entity.FitnessCentar;
import web.in312019.entity.Ocena;
import web.in312019.entity.Sala;
import web.in312019.entity.Termin;
import web.in312019.entity.Trener;
import web.in312019.entity.Trening;
import web.in312019.entity.DTO.KorisnikDTO;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.repository.ClanCentraRepository;
import web.in312019.repository.FitnessCentarRepository;
import web.in312019.repository.SalaRepository;
import web.in312019.repository.TerminRepository;
import web.in312019.repository.TrenerRepository;
import web.in312019.repository.TreningRepository;

@Service
public class TrenerService {
	
	private final TrenerRepository trenerRepository;
	private final FitnessCentarRepository fitnessCentarRepository;
	private final TerminRepository terminRepository;
	private final SalaRepository salaRepository;
	private final TreningRepository treningRepository;
	
	
	@Autowired
	public TrenerService(TrenerRepository trenerRepository, FitnessCentarRepository fitnessCentarRepository,
			TerminRepository terminRepository, SalaRepository salaRepository, TreningRepository treningRepository) {
		super();
		this.trenerRepository = trenerRepository;
		this.fitnessCentarRepository = fitnessCentarRepository;
		this.terminRepository = terminRepository;
		this.salaRepository = salaRepository;
		this.treningRepository = treningRepository;
	}


	public Trener findOne(Long id) {
		Trener trener = this.trenerRepository.getOne(id);
        return trener;
	}

	
	public List<Trener> findAll() {
		List<Trener> treners = this.trenerRepository.findAll();
        return treners;
	}

	public Trener save(Trener trener) {
		return this.trenerRepository.save(trener);
	}
	public ResponseDTO napraviTrenera(KorisnikDTO korisnikDTO) {
		List<Trener> treneri = this.trenerRepository.findAll();
		for(Trener trener : treneri) {
			if(trener.getUsername().equals(korisnikDTO.getKorisnickoIme())) {
				return new ResponseDTO(1, "Korisnicko ime je zauzeto!");
			}
			if(trener.getEmail().equals(korisnikDTO.getEmail())) {
				return new ResponseDTO(1, "E-Mail je zauzet!");
			}
			if(trener.getTelefon().equals(korisnikDTO.getTelefon())) {
				return new ResponseDTO(1, "Telefon je zauzet!");
			}
		}
		Trener newTrener = new Trener(korisnikDTO.getKorisnickoIme(), korisnikDTO.getIme(), korisnikDTO.getPrezime(), korisnikDTO.getLozinka(), korisnikDTO.getEmail(),
											korisnikDTO.getDatumRodjenja(), korisnikDTO.getTelefon(), korisnikDTO.isAktivan(), false, this.fitnessCentarRepository.getOne(korisnikDTO.getCentarID()));
		this.trenerRepository.save(newTrener);
		
		return new ResponseDTO(0, "");
	}
	public List<KorisnikDTO> zahtevi() {
		List<Trener> treneri = this.trenerRepository.findAll();
		List<KorisnikDTO> retVal = new ArrayList<>();
		for(Trener trener : treneri) {
			if(!trener.isAktivan() && !trener.isObrisan()) {
				KorisnikDTO trenerDTO = new KorisnikDTO(trener.getId(), trener.getUsername(), trener.getIme(), trener.getPrezime(), trener.getLozinka(), trener.getEmail(),
														trener.getDatumRodjenja(), trener.getTelefon(), "trener", trener.isAktivan(), 2, trener.getFitnessCentar().getId());
				retVal.add(trenerDTO);
			}
		}
			
		return retVal;
	}
	public List<KorisnikDTO> aktivniTreneri(){
		List<Trener> treneri = this.trenerRepository.findAll();
		List<KorisnikDTO> retVal = new ArrayList<>();
		for(Trener trener : treneri) {
			if(trener.isAktivan() && !trener.isObrisan()) {
				KorisnikDTO trenerDTO = new KorisnikDTO(trener.getId(), trener.getUsername(), trener.getIme(), trener.getPrezime(), trener.getLozinka(), trener.getEmail(),
														trener.getDatumRodjenja(), trener.getTelefon(), "trener", trener.isAktivan(), 2, trener.getFitnessCentar().getId());
				retVal.add(trenerDTO);
			}
		}
		return retVal;
	}
	public void izracunajOcenu(Trener trener) {
		Set<Termin> termini = trener.getTermini();
		double zbirOcena = 0;
		int brojOcena = 0;
		for(Termin termin : termini) {
			Set<Ocena> ocene = termin.getOcene();
			for(Ocena ocena : ocene) {
				zbirOcena += ocena.getOcena();
				brojOcena++;
			}
			
		}
		trener.setProsecnaOcena(zbirOcena/brojOcena);
		this.trenerRepository.save(trener);
	}
	public void aktiviraj(Long id) {
		Trener trener = this.trenerRepository.getOne(id);
		trener.setAktivan(true);
		this.trenerRepository.save(trener);
		
	}
	public void deaktiviraj(Long id) {
		Trener trener = this.trenerRepository.getOne(id);
		trener.setObrisan(true);
		this.trenerRepository.save(trener);
		
	}
	public Trener create(Trener trener) throws Exception {
		if (trener.getId() != null) {
            throw new Exception("ID must be null!");
        }
		Trener newTrener = this.trenerRepository.save(trener);
        return newTrener;
	}

	
	public void delete(Long id) {
		this.trenerRepository.deleteById(id);

	}
	public List<RasporedDTO> prikazTermina(Long id) {
		Trener trener = this.trenerRepository.getOne(id);
		List<Termin> termini = this.terminRepository.findAll();
		List<RasporedDTO> prikaz = new ArrayList<>();
		
		for(Termin termin : termini) {
			if(termin.getTrener().getId() == trener.getId()) {
				RasporedDTO zaDodavanje = new RasporedDTO(termin.getId(), termin.getPocetakTermina(), termin.getKrajTermina(), termin.getTrajanjeTermina(),
						termin.getCenaTermina(), termin.getTrening().getNazivTreninga(), termin.getSala().getOznakaSale());
				prikaz.add(zaDodavanje);
			}
		}
		return prikaz;
	}
	
	public List<RasporedDTO> prikazSpiskova(Long id) {
		Trener trener = this.trenerRepository.getOne(id);
		List<Termin> termini = this.terminRepository.findAll();
		List<RasporedDTO> prikaz = new ArrayList<>();
		
		for(Termin termin : termini) {
			if(termin.getTrener().getId() == trener.getId()) {
				RasporedDTO zaDodavanje = new RasporedDTO(termin.getTrening().getNazivTreninga(), termin.getTrening().getOpis(), termin.getTrening().getTip(),
						termin.getSala().getOznakaSale());
				prikaz.add(zaDodavanje);
			}
		}
		return prikaz;
	}
	
	
	public RasporedDTO dodajTermin(RasporedDTO novi) {
		Trener trener = this.trenerRepository.getOne(novi.getId());
		Sala sala = null;
		Trening trening = null;
		List<Trening> treninzi = this.treningRepository.findAll();
		for(Trening t : treninzi) {
			if(t.getNazivTreninga().equalsIgnoreCase(novi.getNazivTreninga())) {
				trening = t;
			}
		}
		List<Sala> sale = this.salaRepository.findAll();
		for(Sala s : sale) {
			if(s.getOznakaSale().equalsIgnoreCase(novi.getNazivSale())) {
				sala = s;
			}
		}
		Termin termin = new Termin(novi.getPocetakTermina(), novi.getKrajTermina(), novi.getTrajanjeTermina(), novi.getCenaTermina(), false, 
				trening, trener, sala);
		if(novi.getZastita() != 2) {
			RasporedDTO retVal = new RasporedDTO(novi.getPocetakTermina(), novi.getKrajTermina(), 0, 0, "", "", "", "", 4);
			return retVal;
		}
		
		Set<Termin> termini = sala.getTermini();
		for(Termin t : termini) {
			if(termin.getPocetakTermina().equals(t.getPocetakTermina()) || 
					(termin.getPocetakTermina().after(t.getPocetakTermina()) && termin.getPocetakTermina().before(t.getKrajTermina()))) {
				RasporedDTO retVal = new RasporedDTO(novi.getPocetakTermina(), novi.getKrajTermina(), 0, 0, "", "", "", "", 1);
				return retVal;
			}
			if(termin.getKrajTermina().equals(t.getKrajTermina()) ||
					(termin.getKrajTermina().after(t.getPocetakTermina()) && termin.getKrajTermina().before(t.getKrajTermina()))) {
				RasporedDTO retVal = new RasporedDTO(novi.getPocetakTermina(), novi.getKrajTermina(), 0, 0, "", "", "", "", 2);
				return retVal;
			}
			if(termin.getPocetakTermina().before(t.getPocetakTermina()) && termin.getKrajTermina().after(t.getKrajTermina())) {
				RasporedDTO retVal = new RasporedDTO(novi.getPocetakTermina(), novi.getKrajTermina(), 0, 0, "", "", "", "", 3);
				return retVal;
			}
		}
		this.terminRepository.save(termin);
		RasporedDTO noviTermin = new RasporedDTO(termin.getPocetakTermina(), termin.getKrajTermina(), termin.getTrajanjeTermina(), 
				termin.getCenaTermina(), termin.getTrening().getNazivTreninga(), termin.getTrening().getOpis(), termin.getTrening().getTip(), 
				termin.getSala().getOznakaSale(), 0);
		return noviTermin;
	}
	
	public RasporedDTO izmeniTermin(RasporedDTO info) {
		Trener trener = this.trenerRepository.getOne(info.getId());
		Sala sala = null;
		List<Sala> sale = this.salaRepository.findAll();
		for(Sala s : sale) {
			if(s.getOznakaSale().equalsIgnoreCase(info.getNazivSale())) {
				sala = s;
			}
		}
		Trening trening = null;
		System.out.println(info.getNazivTreninga());
		List<Trening> treninzi = this.treningRepository.findAll();
		for(Trening t : treninzi) {
			if(t.getNazivTreninga().equalsIgnoreCase(info.getNazivTreninga())) {
				trening = t;
			}
		}
		Termin termin = new Termin(info.getTerminID(), info.getPocetakTermina(), info.getKrajTermina(), info.getTrajanjeTermina(),
				info.getCenaTermina(), false, trening, trener, sala);
		
		Set<Termin> terminiTrenera = trener.getTermini();
		Termin terminToUpdate = null;
		for(Termin t : terminiTrenera) {
			if(t.getId()==termin.getId()) {
				terminToUpdate = t;
			}
		}
		
		if(termin.getTrening() != null) { terminToUpdate.setTrening(termin.getTrening()); }
		if(termin.getSala() != null) terminToUpdate.setSala(termin.getSala());
		
		sala = terminToUpdate.getSala();
		trening = terminToUpdate.getTrening();
		
		if(info.getZastita() != 2) {
			RasporedDTO retVal = new RasporedDTO(info.getPocetakTermina(), info.getKrajTermina(), 0, 0, "", "", "", "", 4);
			return retVal;
		}
		
		Set<Termin> terminiSale = sala.getTermini();
		if(termin.getPocetakTermina() != null && termin.getKrajTermina() != null) {
			for(Termin t : terminiSale) {
				if(termin.getPocetakTermina().equals(t.getPocetakTermina()) || 
						(termin.getPocetakTermina().after(t.getPocetakTermina()) && termin.getPocetakTermina().before(t.getKrajTermina()))) {
					RasporedDTO retVal = new RasporedDTO(info.getPocetakTermina(), info.getKrajTermina(), 0, 0, "", "", "", "", 1);
					return retVal;
				}
				if(termin.getKrajTermina().equals(t.getKrajTermina()) ||
						(termin.getKrajTermina().after(t.getPocetakTermina()) && termin.getKrajTermina().before(t.getKrajTermina()))) {
					RasporedDTO retVal = new RasporedDTO(info.getPocetakTermina(), info.getKrajTermina(), 0, 0, "", "", "", "", 2);
					return retVal;
				}
				if(termin.getPocetakTermina().before(t.getPocetakTermina()) && termin.getKrajTermina().after(t.getKrajTermina())) {
					RasporedDTO retVal = new RasporedDTO(info.getPocetakTermina(), info.getKrajTermina(), 0, 0, "", "", "", "", 3);
					return retVal;
				}		
			}
		}
		
		if(termin.getCenaTermina() != null && termin.getCenaTermina() != 0) { terminToUpdate.setCenaTermina(termin.getCenaTermina()); }
		if(termin.getPocetakTermina() != null) { terminToUpdate.setPocetakTermina(termin.getPocetakTermina()); }
		if(termin.getKrajTermina() != null) { terminToUpdate.setKrajTermina(termin.getKrajTermina()); }
		if(termin.getTrajanjeTermina() != 0) { terminToUpdate.setTrajanjeTermina(termin.getTrajanjeTermina()); }
		
        this.terminRepository.save(terminToUpdate);
        
        RasporedDTO retVal = new RasporedDTO(info.getPocetakTermina(), info.getKrajTermina(), info.getTrajanjeTermina(), info.getCenaTermina(),
        		info.getNazivTreninga(), trening.getOpis(), trening.getTip(), info.getNazivSale(), 0);
		return retVal;
	}
}
