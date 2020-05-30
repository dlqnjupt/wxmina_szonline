package service;

import java.sql.SQLException;
import java.text.ParseException;

import dao.BywszjcDao;
import domain.Bywszjc;

public class BywszjcService {

	//获得原水水质监测数据
	public Bywszjc getYuanshui(String date) throws ParseException, SQLException {
		
		BywszjcDao dao = new BywszjcDao();
		Bywszjc yuanshuiData = dao.getYuanshuiData(date);
		return yuanshuiData;
	}
	
	//获得出厂水水质监测数据
	public Bywszjc getChuchang(String date) {
		return null;
		
		
	}
	
	

}
