package web.in312019.controller;


import web.in312019.entity.FitnessCentar;
import web.in312019.entity.Termin;
import web.in312019.entity.Trener;
import web.in312019.entity.DTO.KorisnikDTO;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.service.FitnessCentarService;
import web.in312019.service.OcenaService;
import web.in312019.service.TrenerService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping(value = "/api/trener")
public class TrenerController {
	
    private final TrenerService trenerService;
    private final FitnessCentarService fcService;
    private final OcenaService ocenaService;
    

   
    public TrenerController(TrenerService trenerService, FitnessCentarService fcService, OcenaService ocenaService) {
		super();
		this.trenerService = trenerService;
		this.fcService = fcService;
		this.ocenaService = ocenaService;
	}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "registracija")
    public ResponseEntity<ResponseDTO> NoviTrener(@RequestBody KorisnikDTO korisnikDTO) throws Exception {
    	

        return new ResponseEntity<>(this.trenerService.napraviTrenera(korisnikDTO), HttpStatus.CREATED);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "registracijaAdmin/{role}")
    public ResponseEntity<ResponseDTO> NoviTrener(@RequestBody KorisnikDTO korisnikDTO, @PathVariable int role) throws Exception {
    	
    	if(role != 1) {
    		return new ResponseEntity<>(new ResponseDTO(2, "Nemate pravo na ovu komandu!"), HttpStatus.CREATED);
    	}
        return new ResponseEntity<>(this.trenerService.napraviTrenera(korisnikDTO), HttpStatus.CREATED);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "zahtevi/{zastita}")
    public ResponseEntity<List<KorisnikDTO>> zahtevi(@PathVariable int zastita){
    	if(zastita != 1) {
    		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    	}
    	return new ResponseEntity<>(this.trenerService.zahtevi(), HttpStatus.OK);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/aktiviraj/{id}")
    public ResponseEntity<ResponseDTO> prihvatiTrenera(@PathVariable Long id){
    	this.trenerService.aktiviraj(id);
    	return new ResponseEntity<>(new ResponseDTO(0, "Zahtev je prihvacen!"), HttpStatus.OK);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/obrisi/{id}")
    public ResponseEntity<ResponseDTO> obrisiTrenera(@PathVariable Long id){
    	this.trenerService.delete(id);
    	return new ResponseEntity<>(new ResponseDTO(0, "Zahtev je odbijen!"), HttpStatus.OK);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "treneri/{zastita}")
    public ResponseEntity<List<KorisnikDTO>> aktivniTreneri(@PathVariable int zastita){
    	if(zastita != 1) {
    		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    	}
    	return new ResponseEntity<>(this.trenerService.aktivniTreneri(), HttpStatus.OK);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/deaktiviraj/{id}")
    public ResponseEntity<ResponseDTO> deaktivirajTrenera(@PathVariable Long id){
    	this.trenerService.deaktiviraj(id);
    	return new ResponseEntity<>(new ResponseDTO(0, "Trener je obrisan!"), HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/prikazTermina")
    public ResponseEntity<List<RasporedDTO>> prikaziSale(@RequestBody RasporedDTO trenerInfo) {
    	if(trenerInfo.getZastita() != 2) {
    		List<RasporedDTO> prazna = new ArrayList<>();
    		return new ResponseEntity<>(prazna, HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<>(this.trenerService.prikazTermina(trenerInfo.getId()), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/prikazSpiskova")
    public ResponseEntity<List<RasporedDTO>> prikaziSpiskove(@RequestBody RasporedDTO trenerInfo) {
    	if(trenerInfo.getZastita() != 2) {
    		List<RasporedDTO> prazna = new ArrayList<>();
    		return new ResponseEntity<>(prazna, HttpStatus.NO_CONTENT);
    	}
    	return new ResponseEntity<>(this.trenerService.prikazSpiskova(trenerInfo.getId()), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/dodajTermin")
    public ResponseEntity<RasporedDTO> dodajTermin(@RequestBody RasporedDTO info) {
    	return new ResponseEntity<>(this.trenerService.dodajTermin(info), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/izmeniTermin")	
	public ResponseEntity<RasporedDTO> izmeni(@RequestBody RasporedDTO info) throws Exception {
		return new ResponseEntity<>(this.trenerService.izmeniTermin(info), HttpStatus.OK);
    }
}
