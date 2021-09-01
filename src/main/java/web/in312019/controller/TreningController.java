package web.in312019.controller;

import web.in312019.entity.Termin;
import web.in312019.entity.Trening;
import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.service.TreningService;
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
@RequestMapping(value = "/api/trening")
public class TreningController {
	
	private final TreningService treningService;

	public TreningController(TreningService treningService) {
		super();
		this.treningService = treningService;
	}
	
	/*@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/treningInfo")
    public ResponseEntity<List<RasporedDTO>> findAllDTO() {
    	List<Trening> treninzi = this.treningService.findAll();
        return new ResponseEntity<>(this.treningService.getInfo(treninzi), HttpStatus.OK);
    }*/

}
