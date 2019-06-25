package mercadolivre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mercadolivre.entities.Dna;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Long>{

	public List<Dna> findByRegistro(byte[] registro);
	
	@Query("select d.isSimio from Dna d")
	public List<Boolean> findAllDnaTypes();
}
