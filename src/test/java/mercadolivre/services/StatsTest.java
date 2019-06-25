package mercadolivre.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mercadolivre.messages.errors.ErrorMessages;
import mercadolivre.repositories.DnaRepository;
import mercadolivre.responses.StatsResponse;

public class StatsTest {

	@InjectMocks
	private Stats service;
	
	@Mock
	private DnaRepository repository;
	
	@Before
	public void setup(){
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void retrieveSuccess() {
		
		List<Boolean> dnaTypes = new ArrayList<>();
		dnaTypes.add(true);
		dnaTypes.add(false);
		dnaTypes.add(true);
		dnaTypes.add(false);
		
		when(repository.findAllDnaTypes()).thenReturn(dnaTypes);
	
		StatsResponse response = service.retrieveStats();
		
		assertEquals(ErrorMessages.ERRO_GENERICO, 2.0, response.getCountHumanDna(), 0);
		assertEquals(ErrorMessages.ERRO_GENERICO, 2.0, response.getCountMutantDna(), 0);
		assertEquals(ErrorMessages.ERRO_GENERICO, 1.0, response.getRatio(), 0);
	}
	
	@Test
	public void retrieveSuccess2() {
		
		List<Boolean> dnaTypes = new ArrayList<>();
		dnaTypes.add(true);
		dnaTypes.add(false);
		dnaTypes.add(true);
		dnaTypes.add(false);
		dnaTypes.add(true);
		
		when(repository.findAllDnaTypes()).thenReturn(dnaTypes);
	
		StatsResponse response = service.retrieveStats();
		
		assertEquals(ErrorMessages.ERRO_GENERICO, 2.0, response.getCountHumanDna(), 0);
		assertEquals(ErrorMessages.ERRO_GENERICO, 3.0, response.getCountMutantDna(), 0);
		assertEquals(ErrorMessages.ERRO_GENERICO, 1.5, response.getRatio(), 0);
	}

	@Test
	public void retrieveEmpty() {
		
		List<Boolean> emptyTypes = new ArrayList<>();
		
		when(repository.findAllDnaTypes()).thenReturn(emptyTypes);
	
		StatsResponse response = service.retrieveStats();
		
		assertEquals(ErrorMessages.ERRO_GENERICO, 0.0, response.getCountHumanDna(), 0);
		assertEquals(ErrorMessages.ERRO_GENERICO, 0.0, response.getCountMutantDna(), 0);
		assertEquals(ErrorMessages.ERRO_GENERICO, Double.NaN, response.getRatio(), 0);
	}
}
