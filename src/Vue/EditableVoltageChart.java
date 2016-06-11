package Vue;

import Model.VoltageState;
import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Created by falcon on 6/6/16.
 */
public class EditableVoltageChart extends VoltageChart {

    public final Node chartBackground = this.lookup(".chart-plot-background");

    public EditableVoltageChart(@NamedArg("xAxis") Axis<Double> floatAxis, @NamedArg("yAxis") Axis<Integer> integerAxis2, float step) {
        super(floatAxis, integerAxis2, step);
        setAnimated(false);



        chartBackground.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                double xVal = floatAxis.getValueForDisplay(mouseEvent.getX());
                processMouseClick(xVal);
            }
        });

      //  chartBackground.setOnMouseEntered(event -> getStage().getScene().setCursor(Cursor.HAND));
    }

    @Override
    public void addSerie(ArrayList<VoltageState> list) {
        stateSeries.clear();
        super.addSerie(list);
    }

    protected void processMouseClick(double value) {
        if(stateSeries.isEmpty()) {
            return;
        }
        int index = (int) (value / step);
        if(index < stateSeries.get(0).size()) {
            changeStateAt(index);
        }
    }

    private void changeStateAt(int index) {

        VoltageState state = stateSeries.get(0).get(index);
        switch (state) {
            case DOWN:
                stateSeries.get(0).set(index, VoltageState.MIDDLE);
                break;
            case MIDDLE:
                stateSeries.get(0).set(index, VoltageState.UP);
                break;
            case UP:
                stateSeries.get(0).set(index, VoltageState.DOWN);
                break;
            default:
                break;
        }
        updateChart();
    }

    public ArrayList<VoltageState> getStates() {
        if(stateSeries.isEmpty())
            return new ArrayList<>();
        return (ArrayList<VoltageState>) stateSeries.get(0).clone();
    }

    public Node getChartBackground() {
        return chartBackground;
    }
}
