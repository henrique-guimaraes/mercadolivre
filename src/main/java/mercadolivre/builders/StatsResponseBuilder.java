package mercadolivre.builders;

import mercadolivre.responses.StatsResponse;

public class StatsResponseBuilder {

	private double countMutantDna;
	private double countHumanDna;
	private double ratio;
	
	public static StatsResponseBuilder create() {
		return new StatsResponseBuilder();
	}

	public StatsResponseBuilder countMutantDna(double countMutantDna) {
		this.countMutantDna = countMutantDna;
		return this;
	}
	
	public StatsResponseBuilder countHumanDna(double countHumanDna) {
		this.countHumanDna = countHumanDna;
		return this;
	}
	
	public StatsResponseBuilder ratio(double ratio) {
		this.ratio = ratio;
		return this;
	}
	
	public StatsResponse build() {
		StatsResponse stats = new StatsResponse();
		stats.setCountMutantDna(countMutantDna);
		stats.setCountHumanDna(countHumanDna);
		stats.setRatio(ratio);
		return stats;
	}
}
