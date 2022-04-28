package intelligence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Credits to <a>https://gist.github.com/imjacobclark/2698d0f41835d97c724e4197f3dd9be0</a>
 **/

public class LinearRegressionCalculator {

    private List<Double> xList;
    private List<Double> yList;

    public LinearRegressionCalculator(List<Double> xList, List<Double> yList) {
        this.xList = xList;
        this.yList = yList;
    }

    public Double predictForValue(int predictForDependentVariable) {
        if (xList.size() != yList.size())
            throw new IllegalStateException("Must have equal X and Y data points");

        Integer numberOfDataValues = xList.size();

        List<Double> xSquared = xList
                .stream()
                .map(position -> Math.pow(position, 2))
                .collect(Collectors.toList());

        List<Double> xMultipliedByY = new ArrayList<>();
        for (int i = 0; i < numberOfDataValues; i++) {
            xMultipliedByY.add(xList.get(i)*yList.get(i));
        }

        Double xSummed = xList
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Double ySummed = yList
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Double sumOfXSquared = xSquared
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        Double sumOfXMultipliedByY = xMultipliedByY
                .stream()
                .reduce((prev, next) -> prev + next)
                .get();

        double slopeNominator = numberOfDataValues * sumOfXMultipliedByY - ySummed * xSummed;
        double slopeDenominator = numberOfDataValues * sumOfXSquared - Math.pow(xSummed, 2);
        double slope = slopeNominator / slopeDenominator;

        double interceptNominator = ySummed - slope * xSummed;
        double interceptDenominator = numberOfDataValues;
        double intercept = interceptNominator / interceptDenominator;

        return (slope * predictForDependentVariable) + intercept;
    }
}