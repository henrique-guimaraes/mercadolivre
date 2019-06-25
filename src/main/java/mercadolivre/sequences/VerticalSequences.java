package mercadolivre.sequences;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import mercadolivre.utils.SimianUtils;

public class VerticalSequences implements SimianCommand{

	@Override
	public void countSimianSequences(List<String> dna) {
	
		List<String> transposed = new ArrayList<>();
		IntStream.range(0, dna.size())
		  .forEach(idx -> {
			  String bbb = String.valueOf(dna.parallelStream().flatMap(str -> Stream.of(String.valueOf(str.charAt(idx)))).collect(Collectors.toList())).replace(", ", "");
			  transposed.add(bbb);
		  });	
		transposed.parallelStream().forEach(n -> SimianUtils.updateSequencies(n));
	}
}
