package web.in312019.controller;

import web.in312019.entity.FitnessCentar;
import web.in312019.entity.Sala;
import web.in312019.entity.Termin;
import web.in312019.entity.DTO.FitnessCentarDTO;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.entity.DTO.SalaDTO;
import web.in312019.service.FitnessCentarService;
import web.in312019.service.SalaService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sala")
public class SalaController {

	private final SalaService salaService;
	private final FitnessCentarService fcService;




	public SalaController(SalaService salaService, FitnessCentarService fcService) {
		super();
		this.salaService = salaService;
		this.fcService = fcService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/salaAdmin/{role}")
	public ResponseEntity<List<SalaDTO>> getCentreDTO(@PathVariable int role) {
		if(role != 1) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
		return new ResponseEntity<>(this.salaService.findAllDTO(), HttpStatus.OK);
	}
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "obrisi/{id}")
	public ResponseEntity<ResponseDTO> obrisiSalu(@PathVariable Long id){
		return new ResponseEntity<>(this.salaService.obrisi(id), HttpStatus.OK);
	}
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "nova/{role}")
	public ResponseEntity<ResponseDTO> obrisiSalu(@RequestBody SalaDTO salaDTO, @PathVariable int role){
		if(role != 1) {
			return new ResponseEntity<>(new ResponseDTO(1, "Nemate prava na ovu komandu!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(this.salaService.nova(salaDTO), HttpStatus.OK);
	}
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "azuriraj/{role}")
	public ResponseEntity<ResponseDTO> azurirajSalu(@RequestBody SalaDTO salaDTO, @PathVariable int role){
		if(role != 1) {
			return new ResponseEntity<>(new ResponseDTO(1, "Nemate prava na ovu komandu!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(this.salaService.izmeni(salaDTO), HttpStatus.OK);
	}
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, value = "sve")
	public ResponseEntity<List<SalaDTO>> obrisiSalu(@RequestBody RasporedDTO rasporedDTO){
		if(rasporedDTO.getZastita() != 2) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
		return new ResponseEntity<>(this.salaService.sve(rasporedDTO.getId()), HttpStatus.OK);
	}

}