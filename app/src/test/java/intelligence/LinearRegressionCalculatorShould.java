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

        List<Double> xList = asList(2.0, 3.0, 5.0, 7.0, 9.0, 11.0, 14.0);
        List<Double> yList = asList(4.0, 5.0, 7.0, 10.0, 15.0, 20.0, 40.0);
        calculator = new LinearRegressionCalculator(xList, yList);
    }

    @Test
    void predictValue() {
        double result = calculator.predictForValue(13);
        assertEquals(29, (int)result);
    }
}