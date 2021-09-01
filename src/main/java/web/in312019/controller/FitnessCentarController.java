package web.in312019.controller;

import web.in312019.entity.FitnessCentar;
import web.in312019.entity.Sala;
import web.in312019.entity.DTO.FitnessCentarDTO;
import web.in312019.entity.DTO.KorisnikDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.service.FitnessCentarService;
import web.in312019.service.SalaService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping(value = "/api/fitnessCentar")
public class FitnessCentarController {
	
	private final FitnessCentarService fcService;
	private final SalaService salaService;
	
	
	@Autowired
	public FitnessCentarController(FitnessCentarService fcService, SalaService salaService) {
		super();
		this.fcService = fcService;
		this.salaService = salaService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/regList")
    public ResponseEntity<List<FitnessCentarDTO>> getCentreDTO() {    		
		
    		return new ResponseEntity<>(this.fcService.findAllDTO(), HttpStatus.OK);        
    }
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/fcAdmin/{role}")
    public ResponseEntity<List<FitnessCentarDTO>> getCentreDTO(@PathVariable int role) {    		
		if(role != 1) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
    		return new ResponseEntity<>(this.fcService.findAllDTO(), HttpStatus.OK);        
    }
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "novi/{role}")
    public ResponseEntity<ResponseDTO> NoviTrener(@RequestBody FitnessCentarDTO fcDTO, @PathVariable int role) throws Exception {
    	if(role != 1) {
    		return new ResponseEntity<>(new ResponseDTO(1, "Nemate prava na ovu komandu!"), HttpStatus.OK);
    	}
    	
        return new ResponseEntity<>(this.fcService.noviFc(fcDTO), HttpStatus.CREATED);
    }
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "obrisi/{id}")
    public ResponseEntity<ResponseDTO> NoviTrener(@PathVariable Long id) throws Exception {
    	
        return new ResponseEntity<>(this.fcService.obrisiFc(id), HttpStatus.CREATED);
    }
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "izmeni/{role}")
    public ResponseEntity<ResponseDTO> IzmenjenTrener(@RequestBody FitnessCentarDTO fcDTO, @PathVariable int role) throws Exception {
    	if(role != 1) {
    		return new ResponseEntity<>(new ResponseDTO(1, "Nemate prava na ovu komandu!"), HttpStatus.OK);
    	}
    	
        return new ResponseEntity<>(this.fcService.izmeniFc(fcDTO), HttpStatus.CREATED);
    }
	
}
