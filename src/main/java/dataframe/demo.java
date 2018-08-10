package dataframe;

import com.expr.journey.DataFrame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<String> cols = Arrays.asList("Name", "Age", "Salary", "Vag", "EGN");
        DataFrame dataframe = new DataFrame(data, cols);
        dataframe.addColumn("Vafla", null);
        dataframe.addColumn("dobre li e", (List<?>) data.get(0));
        System.out.println(dataframe);

    }
}
