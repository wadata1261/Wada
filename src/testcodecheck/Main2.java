package testcodecheck;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Main2 {
    public static void main(String args[]) {
        DefaultPieDataset dpd = new DefaultPieDataset();
        dpd.setValue("10代", 10);
        dpd.setValue("20代", 25);
        dpd.setValue("30代", 30);
        dpd.setValue("40代", 20);
        dpd.setValue("50代", 15);

        JFreeChart chart = ChartFactory.createPieChart3D("イベント参加者", dpd, true, false, false);

        ChartFrame ch = new ChartFrame("サンプル円グラフ", chart);
        ch.setVisible(true);
        ch.setSize(600, 400);
    }


}
