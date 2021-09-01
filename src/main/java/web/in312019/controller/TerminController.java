package web.in312019.controller;

import web.in312019.entity.Termin;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/api/termini")
public class TerminController {

    private final TerminService terminService;
    private final TrenerService trenerService;
    private final TreningService treningService;
    private final SalaService salaService;
    private final ClanCentraService clanService;
    private final OcenaService ocenaService;
    
	public TerminController(TerminService terminService, TrenerService trenerService, TreningService treningService,
			SalaService salaService, ClanCentraService clanService, OcenaService ocenaService) {
		super();
		this.terminService = terminService;
		this.trenerService = trenerService;
		this.treningService = treningService;
		this.salaService = salaService;
		this.clanService = clanService;
		this.ocenaService = ocenaService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/prikaz")
    public ResponseEntity<List<RasporedDTO>> prikaziTermine() throws Exception {
    	List<Termin> termini = this.terminService.findAll();
        return new ResponseEntity<>(this.terminService.prikaziTermine(termini), HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/pretraga")	
	public ResponseEntity<List<RasporedDTO>> pretrazi(@RequestBody RasporedDTO raspored) throws Exception {
        return new ResponseEntity<>(this.terminService.rezultatPretrage(raspored), HttpStatus.OK);
    }
}
