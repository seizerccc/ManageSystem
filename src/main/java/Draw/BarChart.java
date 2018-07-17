package Draw;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import DAO.DAOFactory;
import DAO.ShopowncarDAO;
import basic.Count;
import model.Car;
import model.Shopowncar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart {
    public JFreeChart BarChart(CategoryDataset dataset){
        JFreeChart chart = ChartFactory.createBarChart3D(
                "统计", // 图表标题
                "车名", // 目录轴的显示标签
                "浏览量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true,           // 是否显示图例(对于简单的柱状图必须是false)
                false,          // 是否生成工具
                false           // 是否生成URL链接
        );

        //从这里开始
        CategoryPlot plot=chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();         //水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.TYPE1_FONT,12));         //水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体",Font.TYPE1_FONT,12));  //垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.TYPE1_FONT,12));
        chart.getLegend().setItemFont(new Font("黑体", Font.TYPE1_FONT, 12));
        chart.getTitle().setFont(new Font("宋体",Font.TYPE1_FONT,20));//设置标题字体

        //到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
        return chart;
    }
    public CategoryDataset setDataSet(List<Count> counts) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if(counts==null||counts.size()==0){
            dataset.addValue(0, "空", "空");
            return dataset;
        }
        else {
            Iterator iterator = counts.iterator();
            while (iterator.hasNext()) {
                Count count = (Count) iterator.next();
                System.out.println(count.getBrandmodel());
                System.out.println(count.getShopuser_id());
                System.out.println(count.getCount());
                dataset.addValue(count.getCount(), count.getShopuser_id(), count.getBrandmodel());
            }
            return dataset;
        }
    }
}