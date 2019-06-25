package mercadolivre.services.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mercadolivre.exceptions.InvalidDnaException;
import mercadolivre.messages.errors.ErrorMessages;
import mercadolivre.repositories.DnaRepository;

@Component
public abstract class SimiansValidations {

	@Autowired
	protected DnaRepository repository;
	private String patternString = "[ACTG]+";
    
	protected void checkIfDnaIsAValidBidimensionalArray(List<String> dna) {

	dna.parallelStream().forEach(registro -> {
		if(registro.length() != dna.size() || checkIfMatches(registro)) {
			throw new InvalidDnaException();}
		});
	}
	
	protected void checkIfDnaIsUnique(byte[] registro) {
		
		repository.findByRegistro(registro)
				  .stream()
		    	  .findAny()
		    	  .ifPresent(s -> {throw new InvalidDnaException
		    		  		(ErrorMessages.DNA_JA_EXISTE_NA_BASE);});
		
	}
	
	private boolean checkIfMatches(String register) {	
        return !register.matches(patternString);
	}
}
