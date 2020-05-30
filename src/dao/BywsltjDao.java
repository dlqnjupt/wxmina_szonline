package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import domain.Bywscsj;
import domain.Bywsltj;
import domain.Bywszsj;
import utils.DataSourceUtils;

public class BywsltjDao {

	public Bywsltj getbywsltjData() throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from bywsltj where id = (select MAX(id) from bywsltj)";
		Bywsltj BywsltjData = runner.query(sql, new BeanHandler<Bywsltj>(Bywsltj.class));
		return BywsltjData;
	}

	public Bywszsj getBywszsjData() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from bywszsj where id = (select MAX(id) from bywszsj)";
		Bywszsj BywszsjData = runner.query(sql, new BeanHandler<Bywszsj>(Bywszsj.class));
		return BywszsjData;
	}

	public Bywscsj getBywscsjData() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from bywscsj where id = (select MAX(id) from bywscsj)";
		Bywscsj BywscsjData = runner.query(sql, new BeanHandler<Bywscsj>(Bywscsj.class));
		return BywscsjData;
	}

}
