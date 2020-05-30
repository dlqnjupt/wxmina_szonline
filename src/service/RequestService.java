package service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.RequestDao;
import domain.BywConsume;
import domain.BywData;
import domain.DataEntity;
import domain.PlanData;

public class RequestService {

	//获取实时数据
	public DataEntity getRealTimeData() throws SQLException {
		RequestDao dao = new RequestDao();
		DataEntity bywRealTimeData = dao.requestbywRealTimeData();
		
		Date date_S = bywRealTimeData.getDate_s();
		Time time_S = bywRealTimeData.getTime_s();

		return bywRealTimeData;
		
	}
	
	//获取当月累计数据
    public List<BywConsume> getBywConsumeData(String yearMonth) throws SQLException{
    	RequestDao dao = new RequestDao();
    	return dao.requestBywMonthConsume(yearMonth);
    }

    //获得对比数据(改为获取同环比)
	public float[] getDuibiData(DataEntity bywRealTimeData) {
		RequestDao dao = new RequestDao();
		List<DataEntity> duibiList = dao.requestDuibiData(bywRealTimeData);
		
		//获取对比数据
		float[] duibiData = new float[18];
		
		//水量
		duibiData[0] = duibiList.get(0).getSl();//去年
		duibiData[1] = duibiList.get(1).getSl();//上个月
		
		//电耗
		duibiData[2] = duibiList.get(0).getDh();
		duibiData[3] = duibiList.get(1).getDh();
		
		//矾耗
		duibiData[4] = duibiList.get(0).getFh();
		duibiData[5] = duibiList.get(1).getFh();
		
		//氯耗
		duibiData[6] = duibiList.get(0).getLh();
		duibiData[7] = duibiList.get(1).getLh();
		
		//氧耗
		duibiData[8] = duibiList.get(0).getYh();
		duibiData[9] = duibiList.get(1).getYh();
		
		//成本
		duibiData[10] = duibiList.get(0).getCb();
		duibiData[11] = duibiList.get(1).getCb();
		
		//获得RealTime的水量、电耗、矾耗、氯耗、氧耗、成本
		
		//水量
		duibiData[12] = bywRealTimeData.getSl();
		//电耗
		duibiData[13] = bywRealTimeData.getDh();
		//矾耗
		duibiData[14] = bywRealTimeData.getFh();
		//氯耗
		duibiData[15] = bywRealTimeData.getLh();
		//氧耗
		duibiData[16] = bywRealTimeData.getYh();
		//成本
		duibiData[17] = bywRealTimeData.getCb();
		
		return duibiData;
	}

	//获得计划数据
	public PlanData getPlanData() throws SQLException {
		RequestDao dao = new RequestDao();
		PlanData planData = dao.getPlanData();
		return planData;
	}





}
