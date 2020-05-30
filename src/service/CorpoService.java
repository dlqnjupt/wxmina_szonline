package service;

import java.sql.SQLException;
import java.util.List;

import dao.CorpoDao;
import domain.CorpoHlht;
import domain.Corpogsl;
import domain.Corpoyejian;
import domain.Corpoysl;

public class CorpoService {
	
	//前日供水量
	public Corpogsl getCorpogsl() throws SQLException {
		CorpoDao dao = new CorpoDao();
		Corpogsl lastDaygsl = dao.getLastDaygsl();
		return lastDaygsl;
	}
	
	//当月累计供水量
	public List<Corpogsl> getMonthgsl(String yearMonth) throws SQLException {
		CorpoDao dao = new CorpoDao();
		List<Corpogsl> monthgsl = dao.getMonthgsl(yearMonth);
		return monthgsl;
	}
	
	//前日区域用水量
	public Corpoysl getQyysl() throws SQLException {
		CorpoDao dao = new CorpoDao();
		Corpoysl corpoYsl = dao.getCorpoysl();
		return corpoYsl;
	}
	
	//当月累计区域用水量
	public List<Corpoysl> getMonthQyysl(String yearMonth) throws SQLException {
		CorpoDao dao = new CorpoDao();
		List<Corpoysl> corpoMonthysl = dao.getCorpoMonthysl(yearMonth);

		return corpoMonthysl;
	}
	
	//前日夜间小流量
	public Corpoyejian getCorpoYejian() throws SQLException {
		CorpoDao dao = new CorpoDao();
		Corpoyejian corpoYejian = dao.getCorpoYejian();
		return corpoYejian;
	}
	//前十天夜间小流量
	public List<Corpoyejian> getLastTenData() throws SQLException {
		CorpoDao dao = new CorpoDao();
		List<Corpoyejian> lastTenYejian = dao.getLastTenYejian();
		return lastTenYejian;
	}
	
	//echarts所需前十天夜间小流
	public List<Corpoyejian> getLastTenDataOfEcharts() throws SQLException {
		CorpoDao dao = new CorpoDao();
		List<Corpoyejian> lastTenYejian = dao.getLastTenYejianOfEcharts();
		return lastTenYejian;
	}

	//互联互通数据
	public CorpoHlht getCorpoHlht() throws SQLException {
		CorpoDao dao = new CorpoDao();
		CorpoHlht hlht = dao.getCorpoHlht();
		return hlht;
		
		
	}





	

}
