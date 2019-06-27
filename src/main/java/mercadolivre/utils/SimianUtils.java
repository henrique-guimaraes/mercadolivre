package mercadolivre.utils;

public class SimianUtils {

	public static StringBuilder appendHorizontal(StringBuilder valueSb, String[] matrix, int x, int i) {
		return valueSb.append(matrix[i].substring(x, x + 1));
	}

	public static StringBuilder appendVertical(StringBuilder valueSb, String[] matrix, int x, int i) {
		return valueSb.append(Character.toString(matrix[x].charAt(i)));
	}

	public static StringBuilder appendDiagonalCimaEsquerda(StringBuilder valueSb, String[] matrix, int x, int i,
			int z) {
		return valueSb.append(matrix[x - i].substring(z, z + 1));
	}

	public static StringBuilder appendDiagonalCimaDireita(StringBuilder valueSb, String[] matrix, int x, int i, int z) {
		return valueSb.append(matrix[x - i].substring(matrix.length - z - 1, matrix.length - z));
	}

	public static StringBuilder appendDiagonalBaixoEsquerda(StringBuilder valueSb, String[] matrix, int x, int a) {
		return valueSb.append(matrix[a - 1].substring(x, x + 1));
	}

	public static StringBuilder appendDiagonalBaixoDireita(StringBuilder valueSb, String[] matrix, int x, int z,
			int a) {
		return valueSb.append(matrix[a - 1].substring(matrix.length - z - 1, matrix.length - z));
	}

	public static boolean simianDetected() {
		return SimianDnaSequences.getSimianDnaBasesAmount() > 1;
	}
}