import data.api.BitcoinApi;
import intelligence.LinearRegressionCalculator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import utils.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChartApp extends Application {

    @Override
    public void start(Stage stage) {

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setForceZeroInRange(true);
        xAxis.setLabel("Date in Epoch Time");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Prediction for the BTC to USD rate for today");

        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("BTC to USD rate");


        //populating the series with data
        populateWithData(series);

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private static void populateWithData(XYChart.Series series) {
        List<Pair<Integer, Integer>> pairs = new BitcoinApi().getRatesForPastWeek();

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        for (Pair<Integer, Integer> pair : pairs) {
            int epochTime = pair.getFirst();
            int rate = pair.getSecond();

            // populate the xList and yList for the regression
            xList.add(epochTime);
            yList.add(rate);

            // add the same values to the line chart
            series.getData().add(new XYChart.Data<>(epochTime, rate));
        }

        // calculate the predicted rate and add it to the chart
        LinearRegressionCalculator calculator = new LinearRegressionCalculator(xList, yList);
        int currentDateInEpochTime = (int) LocalDate.now().toEpochDay();
        series.getData().add(new XYChart.Data(currentDateInEpochTime, calculator.predictForValue(currentDateInEpochTime)));
    }
}