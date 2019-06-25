package mercadolivre.sequences;

import java.util.List;

import mercadolivre.utils.SimianTimes;
import mercadolivre.utils.SimianUtils;

public class UpDiagonalsSequences implements SimianCommand{

	@Override
	public void countSimianSequences(List<String> dna) {
		
		this.fromRightToLeft(dna);
	}
	
	private void fromRightToLeft(List<String> dna) {

		for (int x = 3; x < dna.size(); x++) {
			String nova = "";
			for (int i = 0; x - i > -1; i++) {
				nova = nova + dna.get(i).charAt(x - i);
			}
			SimianUtils.updateSequencies(nova);
		}
		if(SimianTimes.isSimioByTimes())
			return;
		
	this.fromLeftToRight(dna);
	}

	private void fromLeftToRight(List<String> dna) {

		for (int x = 1; x < (dna.size() - 3); x++) {
			String nova = "";
			for (int i = 0; i < dna.size() - x; i++) {
				nova = nova + String.valueOf(dna.get(i).charAt(x + i));
			}
			SimianUtils.updateSequencies(nova);
		}
	}
}
