package service;

import java.sql.SQLException;

import dao.BywsltjDao;
import domain.Bywscsj;
import domain.Bywsltj;
import domain.Bywszsj;

public class BywsltjService {

	// 白洋湾水厂水量统计
	public Bywsltj getsltjData() throws SQLException {

		BywsltjDao dao = new BywsltjDao();
		return dao.getbywsltjData();
	}

	// 白洋湾水厂水质数据
	public Bywszsj getBywszsjData() throws SQLException {
		BywsltjDao dao = new BywsltjDao();
		return dao.getBywszsjData();
	}

	// 白洋湾水厂生产数据
	public Bywscsj getBywscsjData() throws SQLException {
		BywsltjDao dao = new BywsltjDao();
		return dao.getBywscsjData();
	}

}
