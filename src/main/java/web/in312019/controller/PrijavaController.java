package web.in312019.controller;

import web.in312019.entity.ClanCentra;
import web.in312019.entity.Termin;
import web.in312019.service.ClanCentraService;
import web.in312019.entity.Administrator;
import web.in312019.service.AdministratorService;
import web.in312019.entity.Trener;
import web.in312019.service.TrenerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(value = "/api/login")
public class PrijavaController {

	private final ClanCentraService clanService;
	private final AdministratorService adminService;
	private final TrenerService trenerService;
	
	public PrijavaController(ClanCentraService clanService, AdministratorService adminService,
			TrenerService trenerService) {
		super();
		this.clanService = clanService;
		this.adminService = adminService;
		this.trenerService = trenerService;
	}
	

}
   