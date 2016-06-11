package Vue;

import javafx.beans.NamedArg;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;

import Model.VoltageState;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 * Created by falcon on 5/30/16.
 */
public class VoltageChart extends LineChart<Double, Integer> {

    protected ArrayList<ArrayList<VoltageState>> stateSeries;
    float step;

    public VoltageChart(@NamedArg("xAxis") Axis<Double> floatAxis, @NamedArg("yAxis") Axis<Integer> integerAxis2, float step) {
        super(floatAxis, integerAxis2);
        this.step = step;
        stateSeries = new ArrayList<>();
    }

    protected XYChart.Series getSerieFromStates(ArrayList<VoltageState> states) {
        XYChart.Series serie = new XYChart.Series<>();
        float xValue = step;
        for(int i = 1; i <= states.size(); ++i) {
            if(states.get(i-1) == VoltageState.DOWN) {
                serie.getData().add(new XYChart.Data(xValue - step, -1));
                serie.getData().add(new XYChart.Data(xValue, -1));
            } else if (states.get(i-1) == VoltageState.MIDDLE) {
                serie.getData().add(new XYChart.Data(xValue - step, 0));
                serie.getData().add(new XYChart.Data(xValue, 0));
            }else if (states.get(i-1) == VoltageState.UP) {
                serie.getData().add(new XYChart.Data(xValue - step, 1));
                serie.getData().add(new XYChart.Data(xValue, 1));
            }

            xValue += step;
        }
        return serie;
    }

    public void updateChart() {
        ObservableList<Series<Double, Integer>> data = getData();
        data.clear();

        for(ArrayList<VoltageState> list : stateSeries) {
            data.add(getSerieFromStates(list));
        }
    }

    public void addSerie(ArrayList<VoltageState> list) {
        stateSeries.add((ArrayList<VoltageState>) list.clone());
    }

    public ArrayList<ArrayList<VoltageState>> getVoltageState() {
        return stateSeries;
    }

    public void clearSeries() {
        stateSeries.clear();
    }


}
