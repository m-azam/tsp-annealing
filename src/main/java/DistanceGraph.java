
import entities.Route;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.util.ArrayList;

public class DistanceGraph extends ApplicationFrame {

    public DistanceGraph(ArrayList<Route> routes) {
        super("Graph");
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Distance graph",
                "Route", "Distance",
                createDataset(routes),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(ArrayList<Route> routes) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < routes.size(); i++) {
            dataset.addValue(routes.get(i).getTotalDistance(), "distance", (i + 1) + "");
        }

        return dataset;
    }
}