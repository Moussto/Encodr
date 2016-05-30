package Module;

import javafx.beans.NamedArg;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;

import Model.VoltageState;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by falcon on 5/30/16.
 */
public class VoltageChart extends LineChart<Integer, Integer> {

    private ArrayList<ArrayList<VoltageState>> stateSeries;

    public VoltageChart(@NamedArg("xAxis") Axis<Integer> integerAxis, @NamedArg("yAxis") Axis<Integer> integerAxis2) {
        super(integerAxis, integerAxis2);
        stateSeries = new ArrayList<>();
    }

    private XYChart.Series getSerieFromStates(ArrayList<VoltageState> states) {
        XYChart.Series serie = new XYChart.Series<>();

        return serie;
    }

    private void updateChart() {
        ObservableList<Series<Integer, Integer>> data = getData();
        data.clear();

        for(ArrayList<VoltageState> list : stateSeries) {

        }
    }

    public void addSerie(ArrayList<VoltageState> list) {
        stateSeries.add(list);
    }

    public ArrayList<ArrayList<VoltageState>> getVoltageState() {
        return stateSeries;
    }

    public void clearSeries() {
        stateSeries.clear();
    }
}
