package web.in312019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import web.in312019.entity.DTO.LoginDTO;
import web.in312019.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	private final LoginService loginService;
	
	@Autowired
	 public LoginController(LoginService loginService) {
	    this.loginService = loginService;
    }
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) throws Exception {
		return new ResponseEntity<>(this.loginService.login(loginDTO), HttpStatus.OK);
	}
}
