package web.in312019.controller;

import web.in312019.entity.Ocena;
import web.in312019.entity.Sala;
import web.in312019.entity.Termin;
import web.in312019.entity.Trener;
import web.in312019.entity.Trening;
import web.in312019.entity.DTO.LoginDTO;
import web.in312019.entity.DTO.OcenaDTO;
import web.in312019.entity.DTO.ResponseDTO;
import web.in312019.service.ClanCentraService;
import web.in312019.service.OcenaService;
import web.in312019.service.TerminService;
import web.in312019.service.TrenerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ocena")
public class OcenaController {
	
	private final OcenaService ocenaService;
	private final TerminService terminService;
	private final ClanCentraService clanService;
	private final TrenerService trenerService;
	
	
	
	
	
	public OcenaController(OcenaService ocenaService, TerminService terminService, ClanCentraService clanService,
			TrenerService trenerService) {
		super();
		this.ocenaService = ocenaService;
		this.terminService = terminService;
		this.clanService = clanService;
		this.trenerService = trenerService;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "oceni")
    public ResponseEntity<ResponseDTO> login(@RequestBody OcenaDTO ocenaDTO) throws Exception {
		this.ocenaService.novaOcena(ocenaDTO);
		this.trenerService.izracunajOcenu(this.terminService.findOne(ocenaDTO.getTerminID()).getTrener());
		return new ResponseEntity<>(new ResponseDTO(0, "Termin ID" + ocenaDTO.getTerminID() + " ocenjen!"), HttpStatus.OK);
	}





	
	
	
	
	



	
}
