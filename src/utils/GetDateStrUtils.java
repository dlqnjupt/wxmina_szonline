package utils;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class GetDateStrUtils {

	SimpleDateFormat sf1 = new SimpleDateFormat("YYYY年MM月dd日");
	SimpleDateFormat sf2 = new SimpleDateFormat("HH时");
	

	public String getDateStr(String database){
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql1 = "SELECT Date_s FROM "+database+" WHERE id =(SELECT MAX(id) FROM "+database+")";
		String sql2 = "SELECT Time_s FROM "+database+" WHERE id =(SELECT MAX(id) FROM "+database+")";
		java.sql.Date date_s = null;
		java.sql.Time time_s = null;
		try {
			date_s = (Date) runner.query(sql1, new ScalarHandler());
			time_s = (Time) runner.query(sql2, new ScalarHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String dateStr = sf1.format(date_s);
		String timeStr = sf2.format(time_s);
		String DateStr = dateStr+timeStr;
		
		return DateStr;	
	}
	

	
	
	//获取白洋湾水厂导航界面的数据更新时间
	public HashMap<String,String> getAlllBywDateStr(){
		
		String bywconsume = getDateStr("byw_realtime");
		String bywsltj = getDateStr("bywsltj");
		String bywszsj = getDateStr("bywszsj");
		String bywscsj = getDateStr("bywscsj");
		String bywszjc = getDateStr("bywszjc_in");
		
		HashMap<String,String> BywDateStr = new HashMap<String,String>();
		BywDateStr.put("bywconsume", bywconsume);
		BywDateStr.put("bywsltj", bywsltj);
		BywDateStr.put("bywszsj", bywszsj);
		BywDateStr.put("bywscsj", bywscsj);
		BywDateStr.put("bywszjc", bywszjc);	
		return BywDateStr;
		
	}
	
	//获取公司界面的数据更新时间
	public HashMap<String,String> getAllCorpoDateStr(){
		HashMap<String,String> allCorpoDateStr = new HashMap<String,String>();
		
		String gsl = getDateStr("gongshuiliang");
		String ysl = getDateStr("yongshuiliang");
		String yejian = getDateStr("yejian");
		String hlht=getDateStr("hlht");
		
		allCorpoDateStr.put("gsl", gsl);
		allCorpoDateStr.put("ysl", ysl);
		allCorpoDateStr.put("yejian", yejian);
		allCorpoDateStr.put("hlht", hlht);

	
		return allCorpoDateStr;
		
	}
	


}
