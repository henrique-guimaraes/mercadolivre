package mercadolivre.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mercadolivre.exceptions.InvalidDnaException;
import mercadolivre.messages.Responses;
import mercadolivre.messages.errors.ErrorMessages;
import mercadolivre.requests.SimianRequest;
import mercadolivre.services.Simians;

@RestController
public class SimianController {

	@Autowired
	private Simians service;
	
	@PostMapping(value="/simian")
	public Object isSimian(@RequestBody SimianRequest request) throws ClassNotFoundException, IOException {
		
		try {
			return service.isSimio(request.getDna()) 
					? new ResponseEntity<>(Responses.SIMIO, HttpStatus.OK)
					: new ResponseEntity<>(Responses.HUMANO, HttpStatus.FORBIDDEN);
		}catch(InvalidDnaException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}catch(Exception e) {
			return new ResponseEntity<>(ErrorMessages.ERRO_GENERICO, HttpStatus.FORBIDDEN);
		}
	}
}
