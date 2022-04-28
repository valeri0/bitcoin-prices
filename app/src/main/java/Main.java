import data.api.BitcoinApi;
import utils.Pair;

import java.util.List;

public class Main {

    public static void main(String args[]) {
        BitcoinApi api = new BitcoinApi();
        List<Pair<Integer, Integer>> pairs = api.getRatesForPastWeek();

        for (Pair<Integer, Integer> pair : pairs) {
            System.out.println(pair.getFirst() + " --> " + pair.getSecond());
        }
    }
}
