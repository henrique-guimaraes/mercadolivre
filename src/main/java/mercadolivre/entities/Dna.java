package mercadolivre.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="dna")
public class Dna {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name = "registro", columnDefinition = "blob", length = 16777215)
	private byte[] registro;

	@Column(name="is_simio", columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isSimio;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getRegistro() {
		return registro;
	}
	public void setRegistro(byte[] registro) {
		this.registro = registro;
	}
	public boolean isSimio() {
		return isSimio;
	}
	public void setSimio(boolean isSimio) {
		this.isSimio = isSimio;
	}
}
