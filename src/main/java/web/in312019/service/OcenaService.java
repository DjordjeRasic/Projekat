package web.in312019.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.Ocena;
import web.in312019.entity.DTO.OcenaDTO;
import web.in312019.repository.ClanCentraRepository;
import web.in312019.repository.OcenaRepository;
import web.in312019.repository.TerminRepository;

@Service
public class OcenaService {

	
	private final OcenaRepository ocenaRepository;
	private final ClanCentraRepository clanRepository;
	private final TerminRepository terminRepository;
	
	@Autowired
    public OcenaService(OcenaRepository ocenaRepository, ClanCentraRepository clanRepository, TerminRepository terminRepository) {
        this.ocenaRepository = ocenaRepository;
        this.clanRepository = clanRepository;
        this.terminRepository = terminRepository;
    }
	
	
	public Ocena findOne(Long id) {
		Ocena ocena = this.ocenaRepository.getOne(id);
        return ocena;
	}

	
	public List<Ocena> findAll() {
		List<Ocena> ocena = this.ocenaRepository.findAll();
        return ocena;
	}

	public Ocena save(Ocena ocena) {
		return this.ocenaRepository.save(ocena);
	}
	public Ocena create(Ocena ocena) throws Exception {
		if (ocena.getId() != null) {
            throw new Exception("ID must be null!");
        }
		Ocena newOcena = this.ocenaRepository.save(ocena);
        return newOcena;
	}
	public void novaOcena(OcenaDTO ocenaDTO) {
		this.ocenaRepository.save(new Ocena(ocenaDTO.getOcena(), this.terminRepository.getOne(ocenaDTO.getTerminID()),
									this.clanRepository.getOne(ocenaDTO.getClanID())));
	}

	
	public void delete(Long id) {
		this.ocenaRepository.deleteById(id);

	}
}
