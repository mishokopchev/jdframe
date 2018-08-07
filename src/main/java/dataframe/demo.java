package dataframe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.expr.journey.Block;
import com.expr.journey.DataFrame;

public class demo {

	public static void main(String[] args) {
		List<List<? extends Object>> data = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			List<Integer> lists = new ArrayList<>();
			for (int j = 0; j < 5; j++) {
				lists.add(j);
			}
			data.add((List<Integer>) lists);
		}
		List<String> co	ls = Arrays.asList("Name", "Age", "Salary", "Vag", "EGN");
		DataFrame dataframe = new DataFrame(data, cols);
		dataframe.add("Vafla", null);
		dataframe.add("dobre li e", (List<?>) data.get(0));
		System.out.println(dataframe);

	}
}
