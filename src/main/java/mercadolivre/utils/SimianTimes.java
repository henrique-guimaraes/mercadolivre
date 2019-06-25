package mercadolivre.utils;

public class SimianTimes {
	
	private static long vezes;

	public static synchronized long getVezes() {
		return vezes;
	}

	public static synchronized void setVezes(long vezes) {
		SimianTimes.vezes = vezes;
	}
	
	public static boolean isSimioByTimes() {
		return SimianTimes.getVezes() > 1;
	}
}
