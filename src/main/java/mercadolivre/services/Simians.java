package mercadolivre.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import mercadolivre.builders.DnaBuilder;
import mercadolivre.services.validations.SimiansValidations;
import mercadolivre.utils.Serialization;
import mercadolivre.utils.SimianTimes;
import mercadolivre.utils.SimianUtils;

@Service
public class Simians extends SimiansValidations{
	
	public synchronized boolean isSimio(String[] dnaArray) throws IOException, ClassNotFoundException {
		
		List<String> dna = new ArrayList<>();
		Collections.addAll(dna, dnaArray);

		this.checkIfDnaIsAValidBidimensionalArray(dna);
		byte[] serialized = Serialization.serialize(dna);
		
		this.checkIfDnaIsUnique(serialized);
		
		SimianTimes.setVezes(0);
		SimianUtils.getCommands()
					.parallelStream().forEach(c -> {
						c.countSimianSequences(dna);
			if (SimianTimes.isSimioByTimes()) {
				return;
			}
		}); 

		boolean isSimio = SimianTimes.isSimioByTimes();
		this.store(serialized, isSimio);
		
		return isSimio;
	}
	
	private void store(byte[] serialized, boolean isSimio) {
		
		repository.save(DnaBuilder.create()
				  .registro(serialized)
				  .isSimio(isSimio)
				  .build());
	}
}
