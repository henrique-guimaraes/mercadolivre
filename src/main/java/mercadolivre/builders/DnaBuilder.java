package mercadolivre.builders;

import mercadolivre.entities.Dna;

public class DnaBuilder {
	
	private Long id;
	private byte[] registro;
	private boolean isSimio;
	
	public static DnaBuilder create() {
		return new DnaBuilder();
	}

	public DnaBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public DnaBuilder registro(byte[] registro) {
		this.registro = registro;
		return this;
	}
	
	public DnaBuilder isSimio(boolean isSimio) {
		this.isSimio = isSimio;
		return this;
	}
	
	public Dna build() {
		Dna dna = new Dna();
		dna.setRegistro(registro);
		dna.setId(id);
		dna.setSimio(isSimio);
		return dna;
	}
}
