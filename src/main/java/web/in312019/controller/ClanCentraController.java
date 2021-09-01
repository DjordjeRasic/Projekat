package web.in312019.controller;

import web.in312019.entity.ClanCentra;
import web.in312019.entity.Trener;
import web.in312019.entity.DTO.KorisnikDTO;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.service.ClanCentraService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(value = "/api/clan")
public class ClanCentraController {
	
    private final ClanCentraService clanService;

    
    @Autowired
    public ClanCentraController(ClanCentraService clanService) {
        this.clanService = clanService;
    }
    
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "registracija")
    public ResponseEntity<ResponseDTO> NoviClan(@RequestBody KorisnikDTO korisnikDTO) throws Exception {   	
        return new ResponseEntity<>(this.clanService.napraviClana(korisnikDTO), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/odradjeni")
    public ResponseEntity<List<RasporedDTO>> prikaziOdradjene(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.prikaziOdradjene(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/ocenjeni")
    public ResponseEntity<List<RasporedDTO>> prikaziOcenjene(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.prikaziOcenjene(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/prijavljeni")
    public ResponseEntity<List<RasporedDTO>> prikaziPrijavljene(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.prikaziPrijavljene(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/odjava")
    public ResponseEntity<List<RasporedDTO>> odjaviTermin(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.odjavaTermina(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/termini")
    public ResponseEntity<List<RasporedDTO>> prikazTermina(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.prikaziTermine(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/pretraga")
    public ResponseEntity<List<RasporedDTO>> pretragaTermina(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.pretraziTermine(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/prijava")
    public ResponseEntity<RasporedDTO> prijavaTermina(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.prijavaTermina(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/profil")
    public ResponseEntity<KorisnikDTO> prikaziProfil(@RequestBody KorisnikDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.prikaziProfil(info), HttpStatus.CREATED);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/detalji")
    public ResponseEntity<RasporedDTO> terminDetaljno(@RequestBody RasporedDTO info) throws Exception {
        return new ResponseEntity<>(this.clanService.terminDetaljno(info), HttpStatus.CREATED);
    }
}
