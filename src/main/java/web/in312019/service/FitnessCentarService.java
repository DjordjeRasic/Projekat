package web.in312019.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.FitnessCentar;
import web.in312019.entity.DTO.FitnessCentarDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.repository.FitnessCentarRepository;

@Service
public class FitnessCentarService {

	private final FitnessCentarRepository centarRepository;
	
	@Autowired
    public FitnessCentarService(FitnessCentarRepository centarRepository) {
        this.centarRepository = centarRepository;
    }
	
	
	public FitnessCentar findOne(Long id) {
		FitnessCentar fc = this.centarRepository.getOne(id);
        return fc;
	}

	
	public List<FitnessCentar> findAll() {
		List<FitnessCentar> fcs = this.centarRepository.findAll();
        return fcs;
	}
	public List<FitnessCentarDTO> findAllDTO() {
		List<FitnessCentar> fcs = this.centarRepository.findAll();
		List<FitnessCentarDTO> fcsDTO = new ArrayList<>();
		for(FitnessCentar fc : fcs) {
			if(!fc.getObrisan()) {
				FitnessCentarDTO fcDTO = new FitnessCentarDTO(fc.getId(), fc.getNazivCentra(), fc.getAdresa(), fc.getBrojTelefonaCentra(), fc.getEmailCentra());
				fcsDTO.add(fcDTO);
			}
		}
        return fcsDTO;
	}
	public ResponseDTO noviFc(FitnessCentarDTO fcDTO) {
		List<FitnessCentar> centri = this.centarRepository.findAll();
		for(FitnessCentar fc : centri) {
			if(fc.getBrojTelefonaCentra().equals(fcDTO.getBrojTelefonaCentra())) {
				return new ResponseDTO(2, "Broj telefona je vec zauzet!");
			}
			if(fc.getEmailCentra().equals(fcDTO.getEmailCentra())) {
				return new ResponseDTO(2, "E-Mail je vec zauzet!");
			}
		}
		FitnessCentar newFc = new FitnessCentar(fcDTO.getNazivCentra(), fcDTO.getAdresa(), fcDTO.getBrojTelefonaCentra(), fcDTO.getEmailCentra(), false);
		this.centarRepository.save(newFc);
		return new ResponseDTO(0, "Fitness centar je uspesno kreiran");
	}
	public ResponseDTO izmeniFc(FitnessCentarDTO fcDTO) {
		List<FitnessCentar> centri = this.centarRepository.findAll();
		for(FitnessCentar fc : centri) {
			if(fc.getBrojTelefonaCentra().equals(fcDTO.getBrojTelefonaCentra())) {
				return new ResponseDTO(2, "Broj telefona je vec zauzet!");
			}
			if(fc.getEmailCentra().equals(fcDTO.getEmailCentra())) {
				return new ResponseDTO(2, "E-Mail je vec zauzet!");
			}
		}
		FitnessCentar fc = this.centarRepository.getOne(fcDTO.getId());
		if(!fcDTO.getAdresa().isBlank()) {
			fc.setAdresa(fcDTO.getAdresa());
		}
		if(!fcDTO.getBrojTelefonaCentra().isBlank()) {
			fc.setBrojTelefonaCentra(fcDTO.getBrojTelefonaCentra());
		}
		if(!fcDTO.getNazivCentra().isBlank()) {
			fc.setNazivCentra(fcDTO.getNazivCentra());
		}
		if(!fcDTO.getEmailCentra().isBlank()) {
			fc.setEmailCentra(fcDTO.getEmailCentra());
		}
		this.centarRepository.save(fc);
		return new ResponseDTO(0, "Fitness centar je uspesno izmenjen!");
	}

	public FitnessCentar save(FitnessCentar fc) {
		return this.centarRepository.save(fc);
	}
	public FitnessCentar create(FitnessCentar fc) throws Exception {
		if (fc.getId() != null) {
            throw new Exception("ID must be null!");
        }
		FitnessCentar newFc = this.centarRepository.save(fc);
        return newFc;
	}
	public ResponseDTO obrisiFc(Long id) {
		FitnessCentar fc = this.centarRepository.getOne(id);
		fc.setObrisan(true);
		this.centarRepository.save(fc);
		return new ResponseDTO(0, "Fitness centar je obrisan!");
	}

	
	public void delete(Long id) {
		this.centarRepository.deleteById(id);

	}
	
}
