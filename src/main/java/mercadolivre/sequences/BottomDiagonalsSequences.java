package mercadolivre.sequences;

import java.util.List;

import mercadolivre.utils.SimianTimes;
import mercadolivre.utils.SimianUtils;

public class BottomDiagonalsSequences implements SimianCommand{

	@Override
	public void countSimianSequences(List<String> dna) {
		
		this.fromRightToLeft(dna);
	}
	
	private void fromRightToLeft(List<String> dna) {
		
		int xAux = dna.size() - 1;
		for (int x = dna.size() - 1; x > 2; x--) {
			xAux = x;
			String nova = "";
			for (int i = (dna.size() - 1); i >= (dna.size() - 1 - xAux); i--) {
				nova = nova + String.valueOf(dna.get(i).charAt(x));
				x--;
			}
			x = xAux;
			SimianUtils.updateSequencies(nova);
		}
		if(SimianTimes.isSimioByTimes())
			return;
		
		this.fromLeftToRight(dna);
	}
	
	private void fromLeftToRight(List<String> dna) {
		
		int xAux = 0;
		for (int x = 1; x < dna.size() - 3; x++) {
			xAux = x;
			String nova = "";
			for (int i = (dna.size() - 1); i >= xAux; i--) {
				
				nova = nova + String.valueOf(dna.get(i).charAt(x));
				x++;
			}
			x = xAux;
			SimianUtils.updateSequencies(nova);
		}
	}
}
