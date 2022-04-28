package intelligence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearRegressionCalculatorShould {

    private LinearRegressionCalculator calculator;

    @BeforeEach
    void setUp() {

        List<Integer> xList = asList(2, 3, 5, 7, 9, 11, 14);
        List<Integer> yList = asList(4, 5, 7, 10, 15, 20, 40);
        calculator = new LinearRegressionCalculator(xList, yList);
    }

    @Test
    void predictValue() {

        //execute
        double result = calculator.predictForValue(13);

        //verify
        assertEquals(29, (int)result);
    }
}