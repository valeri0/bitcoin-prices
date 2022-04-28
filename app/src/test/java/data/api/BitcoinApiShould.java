package data.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BitcoinApiShould {

    private BitcoinApi api;

    @BeforeEach
    void setUp() {
        api = new BitcoinApi();
    }

    @Test
    void getRatesForPastWeek() {

        //execute
        List<Pair<Integer, Integer>> result = api.getRatesForPastWeek();

        //verify
        assertEquals(7, result.size());
    }
}