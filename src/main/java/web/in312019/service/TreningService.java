package web.in312019.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.Trening;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.entity.DTO.TreningDTO;
import web.in312019.repository.TreningRepository;
@Service
public class TreningService {
	
	private final TreningRepository treningRepository;
	@Autowired
    public TreningService(TreningRepository treningRepository) {
        this.treningRepository = treningRepository;
    }
	
	
	public Trening findOne(Long id) {
		Trening trening = this.treningRepository.getOne(id);
        return trening;
	}

	
	public List<Trening> findAll() {
		List<Trening> trenings = this.treningRepository.findAll();
        return trenings;
	}
	
	public List<TreningDTO> findAllDTO() {
		List<Trening> trenings = this.treningRepository.findAll();
		List<TreningDTO> retVal = new ArrayList<>();
		for(Trening trening : trenings) {
			retVal.add(new TreningDTO(trening.getId(), trening.getNazivTreninga(), trening.getOpis(), trening.getTip()));
		}
        return retVal;
	}

	public Trening save(Trening trening) {
		return this.treningRepository.save(trening);
	}
	public Trening create(Trening trening) throws Exception {
		if (trening.getId() != null) {
            throw new Exception("ID must be null!");
        }
		Trening newTrening = this.treningRepository.save(trening);
        return newTrening;
	}

	
	public void delete(Long id) {
		this.treningRepository.deleteById(id);
	}
	
	/*public List<RasporedDTO> getInfo(List<Trening> treninzi) {
		List<RasporedDTO> info = new ArrayList<>();
		for(Trening trening : treninzi) {
			RasporedDTO zaDodati = new RasporedDTO(trening.getId(), trening.getNazivTreninga(), trening.getOpis(), trening.getTip());
			info.add(zaDodati);
		}
		return info;
	}*/
}
