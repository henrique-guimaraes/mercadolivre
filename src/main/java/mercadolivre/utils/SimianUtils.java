package mercadolivre.utils;

import java.util.ArrayList;
import java.util.List;

import mercadolivre.sequences.BottomDiagonalsSequences;
import mercadolivre.sequences.HorizontalSequences;
import mercadolivre.sequences.SimianCommand;
import mercadolivre.sequences.UpDiagonalsSequences;
import mercadolivre.sequences.VerticalSequences;

public class SimianUtils {

	public static List<String> SimioSequences() {
		List<String> sequenciasValidas = new ArrayList<>();
		sequenciasValidas.add("AAAA");
		sequenciasValidas.add("TTTT");
		sequenciasValidas.add("GGGG");
		sequenciasValidas.add("CCCC");
		
		return sequenciasValidas;
	}
	
	public static List<SimianCommand> getCommands() {
		List<SimianCommand> commands = new ArrayList<>();
		commands.add(new HorizontalSequences());
		commands.add(new VerticalSequences());
		commands.add(new UpDiagonalsSequences());
		commands.add(new BottomDiagonalsSequences());
		
		return commands;
	}
	
	public static synchronized void updateSequencies(String n) {
		SimianTimes.setVezes(SimianTimes.getVezes() + SimianUtils.SimioSequences()
		 													  .parallelStream()
		 													  .filter(sequencia -> n.contains(sequencia))
		 													  .count());
	}	
}