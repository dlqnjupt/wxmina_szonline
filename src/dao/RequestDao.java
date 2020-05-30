package dao;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.BywConsume;
import domain.DataEntity;
import domain.PlanData;
import utils.DataSourceUtils;
import utils.TongHuanBiUtils;

public class RequestDao {

	//获取白洋湾水厂消耗指标的实时数据
	public DataEntity requestbywRealTimeData() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//按照最大id查询(最近一条数据)；并没有按照当前月份查询！！！
		String sql = "select * from byw_realtime where id=(select MAX(id) from byw_realtime)";
		DataEntity bywRealTimeData = runner.query(sql, new BeanHandler<DataEntity>(DataEntity.class));
		return bywRealTimeData;
	}
	
	/*
	 * 获取白洋湾水厂消耗指标当月的累计数据
	 * 参数为String nowMonth;前端传来的当前月份到数据库进行查询
	 */
	public List<BywConsume> requestBywMonthConsume(String yearMonth) throws SQLException{
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="select * from byw_month where Date_s like '"+yearMonth+"%"+"'";
		List<BywConsume> mounthCost = runner.query(sql, new BeanListHandler<BywConsume>(BywConsume.class));
		return mounthCost;
		
	}

	
	public List<DataEntity> requestDuibiData(DataEntity bywRealTimeData) {
		
		TongHuanBiUtils utils = new TongHuanBiUtils();
		String lastYear = utils.getLastYear(bywRealTimeData.getDate_s());
		String lastMonth = utils.getLastMonth(bywRealTimeData.getDate_s());
		String oneHourBefore = utils.getOneHourBefore(bywRealTimeData.getTime_s());

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql1 = "select * from byw_realtime where Date_s >'"+lastYear+"'and Time_s >'"+oneHourBefore+"'limit 1";
		String sql2 = "select * from byw_realtime where Date_s >'"+lastMonth+"'and Time_s>'"+oneHourBefore+"'limit 1";
		DataEntity lastYearData =null;
		DataEntity lastMonthData =null;
		
		ArrayList duibiEntity = new ArrayList<DataEntity>();
		try {
			lastYearData = runner.query(sql1, new BeanHandler<DataEntity>(DataEntity.class));
			lastMonthData = runner.query(sql2, new BeanHandler<DataEntity>(DataEntity.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		duibiEntity.add(0, lastYearData);
		duibiEntity.add(1, lastMonthData);
		duibiEntity.add(2, bywRealTimeData);
		
		System.out.println(lastYear+"/*********lastYearData*********/");
		System.out.println(lastYearData);
		System.out.println(lastMonth+"------lastMonth--------");
		System.out.println(lastMonthData);

		return duibiEntity;		
	}

	public PlanData getPlanData() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from planconsume where id =(select MAX(id) from planconsume)";
		PlanData plan = runner.query(sql, new BeanHandler<PlanData>(PlanData.class));
		return plan;
	}


}
