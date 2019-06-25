package mercadolivre.sequences;

import java.util.List;

import mercadolivre.utils.SimianUtils;

public class HorizontalSequences implements SimianCommand{

	@Override
	public void countSimianSequences(List<String> dna) {
		dna.parallelStream().forEach(n -> SimianUtils.updateSequencies(n));    
	}
}
