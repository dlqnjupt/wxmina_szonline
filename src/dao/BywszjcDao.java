package dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.omg.Messaging.SyncScopeHelper;


import domain.Bywszjc;
import utils.DataSourceUtils;
import utils.TongHuanBiUtils;

public class BywszjcDao {
	

	public Bywszjc getYuanshuiData(String dateStr) throws ParseException, SQLException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date_utils = sf.parse(dateStr);
		java.sql.Date dateSql = new java.sql.Date(date_utils.getTime());
		//String sql = "select * from bywszjc_in where Date_s = '"+sqlDate+"'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from bywszjc_in where Date_s = '"+dateSql+"'";

		 
		/***
		 *String sql1 = " select * from bywszjc_in where Date_s =? "; 
		 *该传参方法会发生错误查询，日期day-1;有时间研究一下;
		 */
		
		Bywszjc Bywszjc_in = runner.query(sql, new BeanHandler<Bywszjc>(Bywszjc.class));
		
		
		return Bywszjc_in;
		
	}

}
