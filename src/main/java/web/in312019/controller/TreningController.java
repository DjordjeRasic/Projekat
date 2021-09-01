package web.in312019.controller;

import web.in312019.entity.DTO.RasporedDTO;
import web.in312019.entity.DTO.TreningDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.in312019.service.TreningService;


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

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/svi")
	public ResponseEntity<List<TreningDTO>> findAllDTO(@RequestBody RasporedDTO rasporedDTO) {
		if(rasporedDTO.getZastita() != 2) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
		}
		return new ResponseEntity<>(this.treningService.findAllDTO(), HttpStatus.OK);
	}

}