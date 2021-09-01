package web.in312019.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.Sala;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.entity.DTO.SalaDTO;
import web.in312019.repository.FitnessCentarRepository;
import web.in312019.repository.SalaRepository;

@Service
public class SalaService {

	private final SalaRepository salaRepository;
	private final FitnessCentarRepository centarRepository;
	
	@Autowired
    public SalaService(SalaRepository salaRepository, FitnessCentarRepository centarRepository) {
        this.salaRepository = salaRepository;
        this.centarRepository = centarRepository;
    }
	
	
	public Sala findOne(Long id) {
		Sala fc = this.salaRepository.getOne(id);
        return fc;
	}

	
	public List<Sala> findAll() {
		List<Sala> fcs = this.salaRepository.findAll();
        return fcs;
	}
	public List<SalaDTO> findAllDTO() {
		List<Sala> sale = this.salaRepository.findAll();
		List<SalaDTO> saleDTO = new ArrayList<>();
		for(Sala sala : sale) {
			if(sala.getuUpotrebi()) {
				saleDTO.add(new SalaDTO(sala.getId(), sala.getKapacitet(), sala.getOznakaSale(), sala.getuUpotrebi(), sala.getFitnessCentar().getId(), sala.getFitnessCentar().getNazivCentra()));
			}
		}
        return saleDTO;
	}
	public ResponseDTO obrisi(Long id) {
		Sala sala = this.salaRepository.getOne(id);
		sala.setuUpotrebi(false);
		this.salaRepository.save(sala);
		return new ResponseDTO(0, "Sala je izbacena iz upotrebe!");
	}
	public Sala save(Sala fc) {
		return this.salaRepository.save(fc);
	}
	public Sala create(Sala fc) throws Exception {
		if (fc.getId() != null) {
            throw new Exception("ID must be null!");
        }
		Sala newFc = this.salaRepository.save(fc);
        return newFc;
	}
	
	public ResponseDTO nova(SalaDTO salaDTO) {
		List<Sala> sale = this.findAll();
		for(Sala sala : sale) {
			if(sala.getOznakaSale().equalsIgnoreCase(salaDTO.getOznakaSale())) {
				return new ResponseDTO(2, "Oznaka sale je zauzeta!");
			}
		}
		this.salaRepository.save(new Sala(salaDTO.getKapacitet(), salaDTO.getOznakaSale(), true, this.centarRepository.getOne(salaDTO.getIdFc())));
		return new ResponseDTO(0, "Sala je uspesno kreirana!");
	}
	
	public ResponseDTO izmeni(SalaDTO salaDTO) {
		List<Sala> sale = this.findAll();
		for(Sala sala : sale) {
			if(sala.getOznakaSale().equalsIgnoreCase(salaDTO.getOznakaSale())) {
				return new ResponseDTO(2, "Oznaka sale je zauzeta!");
			}
		}
		Sala updatedSala = this.salaRepository.getOne(salaDTO.getId());
		if(salaDTO.getIdFc() != 0) {
			updatedSala.setFitnessCentar(this.centarRepository.getOne(salaDTO.getIdFc()));
		}
		if(salaDTO.getKapacitet() != -1) {
			updatedSala.setKapacitet(salaDTO.getKapacitet());
		}
		if(!salaDTO.getOznakaSale().isBlank()) {
			updatedSala.setOznakaSale(salaDTO.getOznakaSale());
		}
		this.salaRepository.save(updatedSala);
		return new ResponseDTO(0, "Sala je uspesno azurirana!");
	}

	
	public void delete(Long id) {
		this.salaRepository.deleteById(id);

	}
}
