package mercadolivre.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import mercadolivre.builders.DnaBuilder;
import mercadolivre.services.validations.SimiansValidations;
import mercadolivre.utils.Serialization;
import mercadolivre.utils.SimianDnaSequences;
import mercadolivre.utils.SimianUtils;

@Service
public class Simians extends SimiansValidations {

	public synchronized boolean isSimio(String[] dna) throws IOException, ClassNotFoundException {

		List<String> dnaList = new ArrayList<>();
		Collections.addAll(dnaList, dna);

		this.checkIfDnaIsAValidBidimensionalArray(dnaList);
		byte[] serialized = Serialization.serialize(dna);
		this.checkIfDnaIsUnique(serialized);
		this.checkIfSimianByDnaAnalysis(dna);
		this.storeInDatabase(serialized, SimianDnaSequences.isSimianByFoundDnaBasesAmount());
		return SimianDnaSequences.isSimianByFoundDnaBasesAmount();
	}
	
	private void checkIfSimianByDnaAnalysis(String[] matrix) {
		
		SimianDnaSequences.initializeDnaBasesAmount();
		
		IntStream.iterate(0, i -> i + 1).limit(matrix.length).parallel().forEach(i -> {
			StringBuilder horizontalStr = new StringBuilder();
			StringBuilder verticalStr = new StringBuilder();
			StringBuilder diagonalCimaEsquerdaStr = new StringBuilder();
			StringBuilder diagonalCimaDireitaStr = new StringBuilder();
			StringBuilder diagonalBaixoEsquerdaStr = new StringBuilder();
			StringBuilder diagonalBaixoDireitaStr = new StringBuilder();

			int z = i;
			int a = matrix.length;
			for (int x = 0; x < matrix.length; x++) {
				horizontalStr = SimianUtils.appendHorizontal(horizontalStr, matrix, x, i);
				verticalStr = SimianUtils.appendVertical(verticalStr, matrix, x, i);

				if (i < matrix.length && x - z >= 0) {
					diagonalCimaEsquerdaStr = SimianUtils.appendDiagonalCimaEsquerda(diagonalCimaEsquerdaStr, matrix, x, i, z);
					diagonalCimaDireitaStr = SimianUtils.appendDiagonalCimaDireita(diagonalCimaDireitaStr, matrix, x, i, z);

					if (i > 0) {
						diagonalBaixoEsquerdaStr = SimianUtils.appendDiagonalBaixoEsquerda(diagonalBaixoEsquerdaStr,matrix, x, a);
						diagonalBaixoDireitaStr = SimianUtils.appendDiagonalBaixoDireita(diagonalBaixoDireitaStr,matrix, x, z, a);
					}
					z++;
					a--;
				}
				if (SimianUtils.simianDetected()) {
					i = matrix.length;
					break;
				}
			}
			SimianDnaSequences.addIfSimianDnaBase(horizontalStr.toString());
			SimianDnaSequences.addIfSimianDnaBase(verticalStr.toString());
			SimianDnaSequences.addIfSimianDnaBase(diagonalCimaEsquerdaStr.toString());
			SimianDnaSequences.addIfSimianDnaBase(diagonalCimaDireitaStr.toString());
			SimianDnaSequences.addIfSimianDnaBase(diagonalBaixoEsquerdaStr.toString());
			SimianDnaSequences.addIfSimianDnaBase(diagonalBaixoDireitaStr.toString());
		});
	}

	private void storeInDatabase(byte[] serialized, boolean isSimio) {
		repository.save(DnaBuilder.create().registro(serialized).isSimio(isSimio).build());
	}
}
