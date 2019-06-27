package mercadolivre.utils;

import java.util.ArrayList;
import java.util.List;

public class SimianDnaSequences {
	
	private static long simianDnaBasesAmount;
	
	private static final List<String> getSimianDnaSequencesList(){
		List<String> simianDnaBasesSequences = new ArrayList<>();
		simianDnaBasesSequences.add("AAAA");
		simianDnaBasesSequences.add("TTTT");
		simianDnaBasesSequences.add("CCCC");
		simianDnaBasesSequences.add("GGGG");
		return simianDnaBasesSequences;
	}

	public static synchronized long getSimianDnaBasesAmount() {
		return simianDnaBasesAmount;
	}
	
	public static void initializeDnaBasesAmount() {
		SimianDnaSequences.simianDnaBasesAmount = 0;
	}
	
	public static boolean isSimianByFoundDnaBasesAmount() {
		return SimianDnaSequences.getSimianDnaBasesAmount() > 1;
	}
	
	private static void incrementFoundSimianDnaBasesAmount() {
		SimianDnaSequences.simianDnaBasesAmount++;
	}
	
	public static synchronized void addIfSimianDnaBase(String dnaNitrogenousBases) {

		SimianDnaSequences.getSimianDnaSequencesList().parallelStream().forEach(s -> {
			if (dnaNitrogenousBases.contains(s)) {
				SimianDnaSequences.incrementFoundSimianDnaBasesAmount();
			}
		});
	}
}
