package mercadolivre.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mercadolivre.builders.StatsResponseBuilder;
import mercadolivre.repositories.DnaRepository;
import mercadolivre.responses.StatsResponse;

@Service
public class Stats {

	@Autowired
	protected DnaRepository repository;
	
	public StatsResponse retrieveStats() {
		
		List<Boolean> allDnaTypes = repository.findAllDnaTypes();
		
		double countMutants = allDnaTypes.parallelStream().filter(r -> r).count();
		double countHumans = allDnaTypes.parallelStream().filter(r -> !r).count();
		double ratio = countMutants / countHumans;
		
		return StatsResponseBuilder.create()
								   .countHumanDna(countHumans)
								   .countMutantDna(countMutants)
								   .ratio(ratio)
								   .build();
	}
}
