package mercadolivre.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mercadolivre.entities.Dna;
import mercadolivre.exceptions.InvalidDnaException;
import mercadolivre.repositories.DnaRepository;

public class SimiansTest {

	@InjectMocks
	private Simians service;
	
	@Mock
	private DnaRepository repository;
	
	@Before
	public void setup(){
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void successSimio() throws ClassNotFoundException, IOException {
		
		String[] dnaSimio = new String[8];
		dnaSimio[0] = "AACTACTG";
		dnaSimio[1] = "GATCACGT";
		dnaSimio[2] = "CGACCATG";
		dnaSimio[3] = "CGAACATT";
		dnaSimio[4] = "CGATCCCC";
		dnaSimio[5] = "TCGAAATG";
		dnaSimio[6] = "CGATCCGC";
		dnaSimio[7] = "CGATCCGT";
		
		List<Dna> emptyList = new ArrayList<>();
		
		when(repository.findByRegistro(Mockito.any())).thenReturn(emptyList);
		when(repository.save(Mockito.any())).thenReturn(null);

		assertTrue(service.isSimio(dnaSimio));
	}
	
	@Test
	public void successHumano() throws ClassNotFoundException, IOException {
		
		String[] dnaHumano = new String[8];
		dnaHumano[0] = "AACTACTG";
		dnaHumano[1] = "GATCACGT";
		dnaHumano[2] = "CGACCATG";
		dnaHumano[3] = "CGAACAGT";
		dnaHumano[4] = "CGATCGCC";
		dnaHumano[5] = "TCGAAATT";
		dnaHumano[6] = "CGATCCGC";
		dnaHumano[7] = "CGATCCGT";
		
		List<Dna> emptyList = new ArrayList<>();
		
		when(repository.findByRegistro(Mockito.any())).thenReturn(emptyList);
		when(repository.save(Mockito.any())).thenReturn(null);

		assertFalse(service.isSimio(dnaHumano));
	}
	
	@Test(expected=InvalidDnaException.class)
	public void invalidDna() throws ClassNotFoundException, IOException {
		
		String[] dnaInvalido = new String[8];
		dnaInvalido[0] = "AACTACTG";
		dnaInvalido[1] = "GATCACGT";
		dnaInvalido[2] = "CGACCATG";
		dnaInvalido[3] = "CGAA";
		dnaInvalido[4] = "CGATCGCC";
		dnaInvalido[5] = "TCGAAATT";
		dnaInvalido[6] = "CGATCCGC";
		dnaInvalido[7] = "CGATCCGT";
		
		service.isSimio(dnaInvalido);
	}
	
	@Test(expected=InvalidDnaException.class)
	public void dnaJaExistente() throws ClassNotFoundException, IOException {
		
		String[] dnaInvalido = new String[8];
		dnaInvalido[0] = "AACTACTG";
		dnaInvalido[1] = "GATCACGT";
		dnaInvalido[2] = "CGACCATG";
		dnaInvalido[3] = "CGAACAGT";
		dnaInvalido[4] = "CGATCGCC";
		dnaInvalido[5] = "TCGAAATG";
		dnaInvalido[6] = "CGATCCGC";
		dnaInvalido[7] = "CGATCCGT";
		
		List<Dna> notEmptyList = new ArrayList<>();
		Dna dna = new Dna();
		dna.setId(0L);
		dna.setRegistro(dnaInvalido[0].getBytes());
		dna.setSimio(false);
		
		notEmptyList.add(dna);
		when(repository.findByRegistro(Mockito.any())).thenReturn(notEmptyList);
		
		service.isSimio(dnaInvalido);
	}
	
	@Test(expected=InvalidDnaException.class)
	public void dnaCaracterInvalido() throws ClassNotFoundException, IOException {
		
		String[] dnaInvalido = new String[8];
		dnaInvalido[0] = "AACTACTG";
		dnaInvalido[1] = "GATCACGT";
		dnaInvalido[2] = "CGACCATG";
		dnaInvalido[3] = "CGAACAGT";
		dnaInvalido[4] = "CGATCGCC";
		dnaInvalido[5] = "TCGAAATG";
		dnaInvalido[6] = "CGATCCGC";
		dnaInvalido[7] = "CGATCBGT";
		
		List<Dna> notEmptyList = new ArrayList<>();
		Dna dna = new Dna();
		dna.setId(0L);
		dna.setRegistro(dnaInvalido[0].getBytes());
		dna.setSimio(false);
		
		notEmptyList.add(dna);
		when(repository.findByRegistro(Mockito.any())).thenReturn(notEmptyList);
		
		service.isSimio(dnaInvalido);
	}
}
