package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import domain.CorpoHlht;
import domain.Corpogsl;
import domain.Corpoyejian;
import domain.Corpoysl;
import utils.DataSourceUtils;

public class CorpoDao {

	public Corpogsl getLastDaygsl() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from gongshuiliang where id = (select MAX(id) from gongshuiliang)";
		Corpogsl lastDaygsl = runner.query(sql, new BeanHandler<Corpogsl>(Corpogsl.class));	
		return lastDaygsl;
	}

	public List<Corpogsl> getMonthgsl(String yearMonth) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());	
		String sql = "select * from gongshuiliang where Date_s like '"+yearMonth+"%"+"'";
		List<Corpogsl> Monthgsl = runner.query(sql, new BeanListHandler<Corpogsl>(Corpogsl.class));
		return Monthgsl;
	}

	public Corpoysl getCorpoysl() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from yongshuiliang where id = (select MAX(id) from yongshuiliang)";
		Corpoysl corpoYsl = runner.query(sql, new BeanHandler<Corpoysl>(Corpoysl.class));	
		return corpoYsl;
	}
	
	public List<Corpoysl> getCorpoMonthysl(String yearMonth) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());	
		String sql = "select * from yongshuiliang where Date_s like '"+yearMonth+"%"+"'";
		List<Corpoysl> Monthysl = runner.query(sql, new BeanListHandler<Corpoysl>(Corpoysl.class));
		return Monthysl;
	}

	public Corpoyejian getCorpoYejian() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from yejian where id = (select MAX(id) from yejian)";
		Corpoyejian corpoYejian = runner.query(sql, new BeanHandler<Corpoyejian>(Corpoyejian.class));
		return corpoYejian;
	}

	public List<Corpoyejian> getLastTenYejian() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from yejian where id < (select MAX(id) from yejian) ORDER BY id DESC limit 10";
		List<Corpoyejian> lastTenYejian = runner.query(sql, new BeanListHandler<Corpoyejian>(Corpoyejian.class));
		return lastTenYejian;
	}

	public List<Corpoyejian> getLastTenYejianOfEcharts() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from yejian where id < (select MAX(id) from yejian) ORDER BY id DESC limit 10";
		List<Corpoyejian> lastTenYejian = runner.query(sql, new BeanListHandler<Corpoyejian>(Corpoyejian.class));
		return lastTenYejian;
	}

	public CorpoHlht getCorpoHlht() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from hlht where id = (select MAX(id) from hlht)";
		CorpoHlht hlht = runner.query(sql, new BeanHandler<CorpoHlht>(CorpoHlht.class));	
		return hlht;
	}



}
